package com.stock.stock.entity;

import com.stock.stock.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "conta")
@NoArgsConstructor
@AllArgsConstructor


public class Conta {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Integer id;
    private String nome;
    @Column(unique = true)
    private Long contaid;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String access_token;
    @Column(unique = true)
    private String refresh_token;

    private LocalDateTime expires;


    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    public Long getConta_id() {
        return contaid;
    }

    public void setConta_id(Long conta_id) {
        this.contaid = conta_id;
    }

    public String getExpires_in() {
        return refresh_token;
    }


    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

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
        return access_token;
    }

    public void setAcess_token(String acess_token) {
        this.access_token = acess_token;
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
