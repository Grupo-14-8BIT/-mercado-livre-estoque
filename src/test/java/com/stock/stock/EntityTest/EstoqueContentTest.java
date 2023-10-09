package com.stock.stock.EntityTest;

import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstoqueContentTest {
    private EstoqueContent estoqueContent;

    @BeforeEach
    public void setUp() {
        estoqueContent = new EstoqueContent();
    }

    @Test
    public void testIdGetterAndSetter() {
        estoqueContent.setId(1);
        assertEquals(1, estoqueContent.getId());
    }

    @Test
    public void testEstoqueGetterAndSetter() {
        Estoque estoque = new Estoque();
        estoque.setId(1);
        estoqueContent.setEstoque(estoque);
        assertEquals(estoque, estoqueContent.getEstoque());
    }

    @Test
    public void testUserGetterAndSetter() {
        User user = new User();
        user.setId(1);
        estoqueContent.setUser(user);
        assertEquals(user, estoqueContent.getUser());
    }

    @Test
    public void testSkuSimplesGetterAndSetter() {
        SkuSimples skuSimples = new SkuSimples();
        skuSimples.setId(1);
        estoqueContent.setSkuSimples(skuSimples);
        assertEquals(skuSimples, estoqueContent.getSkuSimples());
    }

    @Test
    public void testQuantidadeRealGetterAndSetter() {
        estoqueContent.setQuantidade_real(10);
        assertEquals(10, estoqueContent.getQuantidade_real());
    }

    @Test
    public void testQuantidadeDeduzidaGetterAndSetter() {
        estoqueContent.setQantidade_deduzida(5);
        assertEquals(5, estoqueContent.getQantidade_deduzida());
    }
}
