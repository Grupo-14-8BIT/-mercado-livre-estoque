package com.stock.stock.responses;

import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Order;
import com.stock.stock.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderNotify {
    public  String _id;

    private String resource;

    private Long user_id;

    private String topic;

    private Long application_id;

    private Integer attempts;

    private LocalDateTime sent;

    private LocalDateTime recived;








}
