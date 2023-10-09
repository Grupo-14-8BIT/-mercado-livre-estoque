package com.stock.stock.Controller;

import com.stock.stock.Service.SkuSimplesService;
import com.stock.stock.entity.Anuncio;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.entity.SkuSimplesDTO;
import com.stock.stock.repository.AnuncioRepository;
import com.stock.stock.repository.SkuSimplesRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import com.stock.stock.user.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/api/v1/admin/sku")

public class SkuController {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkuSimplesService service;

    @Autowired
    private SkuSimplesRepository repository;


    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping("/fetch")
    public ResponseEntity<String> fetch( HttpServletRequest request) {

        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);



      return    service.fetch(user.get());



    };


    @GetMapping("/change")
    public ResponseEntity<String> mudarSkuAnuncio(
            HttpServletRequest request,@RequestParam String mlb, @RequestParam String sku
    ) {

        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);

        Optional<Anuncio> anuncio = anuncioRepository.findAnuncioByMlb(mlb);
       if ( anuncio.get().getConta().getUsuario() == user.get() ) {
           return service.mudarSkuAnuncio(anuncio.get(),sku,anuncio.get().getConta());
       } else {return badRequest().body(" Usuario nao autorizado");}

    };



    @PutMapping("/update")
    public ResponseEntity<String> update(
            HttpServletRequest request, @RequestParam String sku,
            @RequestBody SkuSimplesDTO skuSimplesDTO
            ) {

        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);

        Optional<SkuSimples> skuSimplesOptional = repository.findBySKU(sku);

        if ( skuSimplesOptional.isPresent() && skuSimplesOptional.get().getUser() == user.get()) {
        return service.update(skuSimplesDTO,sku);
        } else {
            return  badRequest().body("Sku nao existe na sua conta");
        }


    };

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace().toString());
    }









}
