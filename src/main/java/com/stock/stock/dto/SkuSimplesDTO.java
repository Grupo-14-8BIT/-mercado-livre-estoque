package com.stock.stock.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SkuSimplesDTO {
    @NotNull
    private String nome;
    @NotNull
    private String SKU;

    private String descricao;

    private String foto;







}
