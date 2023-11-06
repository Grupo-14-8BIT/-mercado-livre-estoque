package com.stock.stock.RepositoryTest;

import com.stock.stock.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AnuncioRepositoryTest {

    @Autowired
    private AnuncioRepository anuncioRepository;

//    @Test
//    public void testFindByMlb() {
//        Anuncio anuncio = new Anuncio();
//        anuncio.setMlb("exampleMlb");
//        anuncioRepository.save(anuncio);
//
//        Optional<Anuncio> foundAnuncio = anuncioRepository.findAnuncioByMlb("exampleMlb");
//
//        assertTrue(foundAnuncio.isPresent());
//        assertEquals("exampleMlb", foundAnuncio.get().getMlb());
//    }
}
