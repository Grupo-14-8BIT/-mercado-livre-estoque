package com.stock.stock.entity;

import com.stock.stock.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sku_simples")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class SkuSimples {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User user;

    private String nome;

    private String SKU;

    private String descricao;

    private String foto;

//    @OneToMany
//    @JoinColumn(referencedColumnName = "SKU")
//    private List<Anuncio> anuncios;






}