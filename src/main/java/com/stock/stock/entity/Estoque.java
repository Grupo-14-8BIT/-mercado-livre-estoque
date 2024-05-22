package com.stock.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User user;


    @ManyToOne

    @JoinColumn(name = "conta_id")
    private Conta conta;

    @OneToMany(mappedBy = "estoque")
    private List<EstoqueContent> estoqueContents;








}