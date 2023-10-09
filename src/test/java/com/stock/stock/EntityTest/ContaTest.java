package com.stock.stock.EntityTest;

import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Estoque;
import com.stock.stock.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaTest {
    private Conta conta;
    @BeforeEach
    public void setUp() {
        conta = new Conta();
    }

    @Test
    public void testIdGetterAndSetter() {
        conta.setId(1);
        assertEquals(1, conta.getId());
    }

    @Test
    public void testNomeGetterAndSetter() {
        conta.setNome("TestConta");
        assertEquals("TestConta", conta.getNome());
    }

    @Test
    public void testCodeGetterAndSetter() {
        conta.setCode("TestCode");
        assertEquals("TestCode", conta.getCode());
    }

    @Test
    public void testAccessTokenGetterAndSetter() {
        conta.setAcess_token("TestAccessToken");
        assertEquals("TestAccessToken", conta.getAcess_token());
    }

    @Test
    public void testRefreshTokenGetterAndSetter() {
        conta.setRefresh_token("TestRefreshToken");
        assertEquals("TestRefreshToken", conta.getRefresh_token());
    }

    @Test
    public void testExpiresGetterAndSetter() {
        LocalDateTime now = LocalDateTime.now();
        conta.setExpires(now);
        assertEquals(now, conta.getExpires());
    }

    @Test
    public void testUsuarioGetterAndSetter() {
        User user = new User();
        user.setId(1);
        conta.setUsuario(user);
        assertEquals(user, conta.getUsuario());
    }

    @Test
    public void testEstoqueGetterAndSetter() {
        Estoque estoque = new Estoque();
        estoque.setId(1);
        conta.setEstoque(estoque);
        assertEquals(estoque, conta.getEstoque());
    }
}
