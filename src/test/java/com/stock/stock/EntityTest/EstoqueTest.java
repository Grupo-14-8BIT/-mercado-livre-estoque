package com.stock.stock.EntityTest;

import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import com.stock.stock.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstoqueTest {
    private Estoque estoque;

    @BeforeEach
    public void setUp() {
        estoque = new Estoque();
    }

    @Test
    public void testIdGetterAndSetter() {
        estoque.setId(1);
        assertEquals(1, estoque.getId());
    }

    @Test
    public void testNomeGetterAndSetter() {
        estoque.setNome("TestEstoque");
        assertEquals("TestEstoque", estoque.getNome());
    }

    @Test
    public void testUserGetterAndSetter() {
        User user = new User();
        user.setId(1);
        estoque.setUser(user);
        assertEquals(user, estoque.getUser());
    }

    @Test
    public void testContasGetterAndSetter() {
        List<Conta> contas = new ArrayList<>();
        Conta conta1 = new Conta();
        conta1.setId(1);
        contas.add(conta1);

        Conta conta2 = new Conta();
        conta2.setId(2);
        contas.add(conta2);

        estoque.setContas(contas);

        assertEquals(contas, estoque.getContas());
    }

    @Test
    public void testEstoqueContentsGetterAndSetter() {
        List<EstoqueContent> estoqueContents = new ArrayList<>();
        EstoqueContent content1 = new EstoqueContent();
        content1.setId(1);
        estoqueContents.add(content1);

        EstoqueContent content2 = new EstoqueContent();
        content2.setId(2);
        estoqueContents.add(content2);

        estoque.setEstoqueContents(estoqueContents);

        assertEquals(estoqueContents, estoque.getEstoqueContents());
    }
}
