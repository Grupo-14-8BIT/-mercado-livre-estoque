package com.stock.stock.EntityTest;

import com.stock.stock.entity.SkuSimples;
import com.stock.stock.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkuSimplesTest {
    private SkuSimples skuSimples;

    @BeforeEach
    public void setUp() {
        skuSimples = new SkuSimples();
    }

    @Test
    public void testIdGetterAndSetter() {
        skuSimples.setId(1);
        assertEquals(1, skuSimples.getId());
    }

    @Test
    public void testUserGetterAndSetter() {
        User user = new User();
        user.setId(1);
        skuSimples.setUser(user);
        assertEquals(user, skuSimples.getUser());
    }

    @Test
    public void testNomeGetterAndSetter() {
        skuSimples.setNome("TestNome");
        assertEquals("TestNome", skuSimples.getNome());
    }

    @Test
    public void testSKUGetterAndSetter() {
        skuSimples.setSKU("TestSKU");
        assertEquals("TestSKU", skuSimples.getSKU());
    }

    @Test
    public void testDescricaoGetterAndSetter() {
        skuSimples.setDescricao("TestDescricao");
        assertEquals("TestDescricao", skuSimples.getDescricao());
    }

    @Test
    public void testFotoGetterAndSetter() {
        skuSimples.setFoto("TestFoto.jpg");
        assertEquals("TestFoto.jpg", skuSimples.getFoto());
    }
}
