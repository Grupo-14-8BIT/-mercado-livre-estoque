package com.stock.stock.Controller;

import com.stock.stock.Service.ContaService;
import com.stock.stock.Service.EstoqueService;
import com.stock.stock.dto.EstoqueContentDTO;
import com.stock.stock.dto.EstoqueDTO;
import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import com.stock.stock.repository.EstoqueContentRepository;
import com.stock.stock.repository.EstoqueRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import com.stock.stock.user.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/estoque")
public class EstoqueController {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private EstoqueService service;

    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private EstoqueContentRepository estoqueContentRepository;


    @PostMapping ("/criar")
    public ResponseEntity<Estoque> create(
            HttpServletRequest request, @RequestBody @Valid EstoqueDTO estoqueDTO

            ){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);


            return ResponseEntity.ok().body(service.create(estoqueDTO,user.get()));


    }

    @GetMapping("/findById")
    private ResponseEntity<Estoque> findById(
            @RequestParam Integer id, HttpServletRequest request){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);

        return ResponseEntity.ok().body(service.findById(id, user.get())) ;

    }

    @GetMapping("/getall")
    private ResponseEntity<List<Estoque>> getall(
          HttpServletRequest request){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);

        return ResponseEntity.ok().body(service.getAll(user.get())) ;

    }

    @PutMapping ("/update")
    public ResponseEntity<Estoque> update(
            HttpServletRequest request, @RequestBody @Valid EstoqueDTO estoqueDTO,
            @RequestParam Integer id

    ){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<Estoque> estoqueOptional = repository.findById(id);

        if (estoqueOptional.get().getUser() == user.get()){


                return ResponseEntity.ok().body(service.update(estoqueOptional.get(), estoqueDTO));

        } else throw new RuntimeException("Voce nao tem permissao para alterar o estoque");



    }


    /////////ESTOQUE CONTENT ///////

    @PostMapping("/addContent")
    public ResponseEntity<EstoqueContent> addContent(
            HttpServletRequest request, @RequestBody @Valid EstoqueContentDTO estoqueContentDTO

    ){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);


            return ResponseEntity.ok().body(service.addContent(estoqueContentDTO,user.get()));


    }

    @GetMapping("/show")
    public ResponseEntity<List<EstoqueContent>> getAllByEstoque(
            HttpServletRequest request,@RequestParam Integer id

    ){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);


            return ResponseEntity.ok().body(service.getAllByEstoque(user.get(), id));


    }


    @PostMapping("/updateContent")
    public ResponseEntity<EstoqueContent> updateContent(
            HttpServletRequest request, @RequestBody @Valid EstoqueContentDTO estoqueContentDTO,
            @RequestParam Integer id

    ){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<EstoqueContent> estoqueContent = estoqueContentRepository.findById(id);

            return ResponseEntity.ok().body(service.updateContent(estoqueContentDTO,estoqueContent.get(),user.get()));


    }


    @PostMapping("/deletecontent")
    public ResponseEntity<EstoqueContent> delete(
            HttpServletRequest request,
            @RequestParam Integer id

    ){
        final String userEmail;
        final String jwt;
        final String authHeader = request.getHeader("Authorization");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<EstoqueContent> estoqueContent = estoqueContentRepository.findById(id);

            return ResponseEntity.ok().body(service.delete(estoqueContent.get(),user.get()));


    }

















}
