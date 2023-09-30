package com.stock.stock.Controller;

import com.stock.stock.Service.ContaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class Redirect {

    @Autowired
    public ContaService service;


@SneakyThrows
@GetMapping("/redirect")
    public void getToken (
            @RequestParam("code") String code,
            @RequestParam("state") Integer state

        ) {

    service.cadastra(code,state);

}

    @GetMapping("teste")
    public void teste (


    ) {

        System.out.println("recebido");

    }
}
