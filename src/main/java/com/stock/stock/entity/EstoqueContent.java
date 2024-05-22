package com.stock.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore

    private User user;

    @ManyToOne
    @JoinColumn(name = "sku_simples")
    private SkuSimples skuSimples;

    private Integer quantidade_real;

    private Integer qantidade_deduzida;
    @ManyToOne
    @JsonIgnore
    private Estoque estoque;






}