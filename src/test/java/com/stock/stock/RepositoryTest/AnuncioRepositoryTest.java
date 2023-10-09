package com.stock.stock.RepositoryTest;

import com.stock.stock.entity.Anuncio;
import com.stock.stock.repository.AnuncioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AnuncioRepositoryTest {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Test
    public void testFindByMlb() {
        Anuncio anuncio = new Anuncio();
        anuncio.setMlb("exampleMlb");
        anuncioRepository.save(anuncio);

        Optional<Anuncio> foundAnuncio = anuncioRepository.findAnuncioByMlb("exampleMlb");

        assertTrue(foundAnuncio.isPresent());
        assertEquals("exampleMlb", foundAnuncio.get().getMlb());
    }
}
