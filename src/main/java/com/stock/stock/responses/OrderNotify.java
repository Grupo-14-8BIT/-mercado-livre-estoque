package com.stock.stock.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
