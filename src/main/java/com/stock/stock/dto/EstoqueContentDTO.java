package com.stock.stock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueContentDTO {
    @NotNull
    private String skuSimples;
    @NotNull
    private Integer estoque;
    @NotNull
    @Min(1)
    private Integer quantidade_real;
}
