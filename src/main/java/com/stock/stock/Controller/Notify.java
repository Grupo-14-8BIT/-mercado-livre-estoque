package com.stock.stock.Controller;

import com.stock.stock.responses.OrderNotify;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/notify")

public class Notify {



    @PostMapping()
    public ResponseEntity<String>  NOTIFY(
            @RequestBody OrderNotify orderNotify
            ) {
        System.out.println("TESTE NOTIFY TESTE");
        System.out.println(orderNotify.getResource());
        System.out.println(orderNotify.getTopic());
        System.out.println(orderNotify.getApplication_id());

        return ok().build();

    };

}
