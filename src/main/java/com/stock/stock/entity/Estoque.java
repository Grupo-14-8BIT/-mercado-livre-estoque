package com.stock.stock.entity;

import com.stock.stock.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "estoque")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class Estoque  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Integer id;
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    private User user;

    @OneToOne
    private Conta conta;

    @OneToMany(mappedBy = "estoque")
    private List<EstoqueContent> estoqueContents;








}