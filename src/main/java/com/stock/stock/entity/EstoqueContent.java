package com.stock.stock.entity;

import com.stock.stock.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estoque_content")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class EstoqueContent {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "usuario")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sku_simples")
    private SkuSimples skuSimples;

    private Integer quantidade_real;

    private Integer qantidade_deduzida;
    @ManyToOne
    private Estoque estoque;






}