package com.stock.stock.Controller;

import com.stock.stock.Service.ContaService;
import com.stock.stock.entity.Conta;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import com.stock.stock.user.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/conta")

public class ContaController {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContaService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<Conta>> getAll( HttpServletRequest request) {

        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);

      return ResponseEntity.ok(service.getAll(Long.valueOf(user.get().getId())));



    };






    @GetMapping("/autoriza")
    public ResponseEntity<String> autoriza (
            HttpServletRequest request
    ) {
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);
        return ResponseEntity.ok("https://auth.mercadolivre.com.br/authorization?response_type=code&client_id="+APP_ID+"&redirect_uri="+YOUR_URL+ "&state="+ user.get().getId());
    }

    @GetMapping("/refresh")
    public ResponseEntity<Conta> refresh (
           @RequestParam Integer conta_id
    ) {
        System.out.println("refrescado:" + conta_id);
        return ResponseEntity.ok(service.refresh(conta_id));

    }

    @PostMapping ("/Delete")
    public  ResponseEntity<String> delete (
            HttpServletRequest request, @RequestParam Integer conta_id
    ) {
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);

       return service.delete(user.get(), conta_id) ;

    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());

        return errorResponse;



    } }
