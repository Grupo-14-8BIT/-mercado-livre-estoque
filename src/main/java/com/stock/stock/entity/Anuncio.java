package com.stock.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anuncio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mlb_id;



//    @ManyToOne
//    @JoinColumn(name = "sku_simples_id")
//    private SkuSimples skuSimples;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
