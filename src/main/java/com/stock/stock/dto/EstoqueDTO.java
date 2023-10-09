package com.stock.stock.dto;

import com.stock.stock.entity.Conta;
import lombok.Data;

import java.util.List;
@Data
public class EstoqueDTO {

    private String nome;
    private List<Conta> contas;
}
