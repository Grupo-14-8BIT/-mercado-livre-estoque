package com.stock.stock.EntityTest;

import com.stock.stock.entity.Anuncio;
import com.stock.stock.entity.Conta;
import com.stock.stock.entity.SkuSimples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnuncioTest {
    @Test
    public void testGettersAndSetters() {
        Anuncio anuncio = new Anuncio();

        anuncio.setId(1L);
        anuncio.setMlb("a");
        anuncio.setConta(new Conta());
        anuncio.setSkuSimples(new SkuSimples());


    }

    @Test
    void testGetId() {
        Anuncio anuncio = new Anuncio();

        anuncio.setId(1L);

        assertEquals(1L, anuncio.getId());
    }
}
