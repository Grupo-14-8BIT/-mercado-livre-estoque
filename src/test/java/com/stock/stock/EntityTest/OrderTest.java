package com.stock.stock.EntityTest;

import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Order;
import com.stock.stock.entity.SkuSimples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testIdGetterAndSetter() {
        order.setId(1);
        assertEquals(1, order.getId());
    }

    @Test
    public void testMlbidGetterAndSetter() {
        order.setMlbid(123456L);
        assertEquals(123456L, order.getMlbid());
    }

    @Test
    public void testSkuGetterAndSetter() {
        SkuSimples skuSimples = new SkuSimples();
        skuSimples.setId(1);
        order.setSku(skuSimples);
        assertEquals(skuSimples, order.getSku());
    }

    @Test
    public void testContaGetterAndSetter() {
        Conta conta = new Conta();
        conta.setId(1);
        order.setConta(conta);
        assertEquals(conta, order.getConta());
    }

    @Test
    public void testQuantidadeGetterAndSetter() {
        order.setQuantidade(5);
        assertEquals(5, order.getQuantidade());
    }

    @Test
    public void testCompradorGetterAndSetter() {
        order.setComprador("John Doe");
        assertEquals("John Doe", order.getComprador());
    }

    @Test
    public void testStatusGetterAndSetter() {
        order.setStatus("Pending");
        assertEquals("Pending", order.getStatus());
    }
}
