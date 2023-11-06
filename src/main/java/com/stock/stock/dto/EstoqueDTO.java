package com.stock.stock.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class EstoqueDTO {
    @NotNull

    private String nome;
    @NotNull
    private Integer conta;
}
