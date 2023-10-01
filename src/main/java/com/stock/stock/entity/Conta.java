package com.stock.stock.entity;

import com.stock.stock.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conta")
@NoArgsConstructor
@AllArgsConstructor

public class Conta {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Integer id;
    private String nome;
    private String code;
    private String acess_token;
    private String refresh_token;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAcess_token() {
        return acess_token;
    }

    public void setAcess_token(String acess_token) {
        this.acess_token = acess_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public User getUsuario_id() {
        return usuario;
    }

    public void setUsuario_id(User usuario_id) {
        this.usuario = usuario_id;
    }
}
