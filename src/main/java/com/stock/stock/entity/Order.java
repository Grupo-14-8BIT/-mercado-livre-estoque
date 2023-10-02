package com.stock.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Integer id;

    private Long MlbId;

    private SkuSimples sku;

    private Conta conta;

    private Integer quantidade;

    private String comprador;


    private String status;




}
