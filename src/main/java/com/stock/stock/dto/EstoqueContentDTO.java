package com.stock.stock.dto;

import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.SkuSimples;
import lombok.Data;

@Data
public class EstoqueContentDTO {
    private Estoque estoque;
    private SkuSimples skuSimples;
    private Integer quantidade_real;
    private Integer qantidade_deduzida;

}
