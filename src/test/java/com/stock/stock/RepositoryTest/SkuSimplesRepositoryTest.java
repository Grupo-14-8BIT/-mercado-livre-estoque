package com.stock.stock.RepositoryTest;

import com.stock.stock.entity.SkuSimples;
import com.stock.stock.user.User;
import com.stock.stock.repository.SkuSimplesRepository;
import com.stock.stock.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SkuSimplesRepositoryTest {

    @Autowired
    private SkuSimplesRepository skuSimplesRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user = userRepository.save(user);
    }

    @Test
    public void testFindBySKU() {
        SkuSimples skuSimples = new SkuSimples();
        skuSimples.setUser(user);
        skuSimples.setNome("Test SKU");
        skuSimples.setSKU("ABC123");
        skuSimples.setDescricao("Test Description");
        skuSimples.setFoto("test.jpg");
        skuSimplesRepository.save(skuSimples);

        Optional<SkuSimples> foundSku = skuSimplesRepository.findBySKU("ABC123");

        assertTrue(foundSku.isPresent());

        assertEquals("ABC123", foundSku.get().getSKU());
    }
}
