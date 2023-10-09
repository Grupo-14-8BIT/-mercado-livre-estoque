package com.stock.stock.dto;

import lombok.Data;

@Data
public class EstoqueContentDTO {

    private String skuSimples;
    private Integer estoque;
    private Integer quantidade_real;
}
