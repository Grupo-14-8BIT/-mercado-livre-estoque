package com.stock.stock.dto;

import com.stock.stock.entity.Conta;
import com.stock.stock.user.User;
import lombok.Data;

import java.util.List;
@Data
public class EstoqueDTO {

    private String nome;
    private User user;
    private List<Conta> contas;
}
