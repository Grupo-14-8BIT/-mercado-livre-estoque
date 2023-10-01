package com.stock.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sku_simples")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class SkuSimples {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer id_user;

    private Long contaid;

    private String nome;

    private String SKU;

    private String descricao;

    private String foto;

    @OneToMany
    @JoinColumn(referencedColumnName = "SKU")
    private List<Anuncio> anuncios;






}
