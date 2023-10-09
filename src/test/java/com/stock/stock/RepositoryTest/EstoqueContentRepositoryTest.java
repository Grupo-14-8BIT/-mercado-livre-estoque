package com.stock.stock.RepositoryTest;

import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import com.stock.stock.repository.EstoqueContentRepository;
import com.stock.stock.repository.EstoqueRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EstoqueContentRepositoryTest {

    @Autowired
    private EstoqueContentRepository estoqueContentRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private Estoque estoque;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user = userRepository.save(user);

        estoque = new Estoque();
        estoque.setNome("Test Estoque");
        estoque.setUser(user);
        estoque = estoqueRepository.save(estoque);
    }

    @Test
    public void testFindAllByEstoque() {
        EstoqueContent content1 = new EstoqueContent();
        content1.setEstoque(estoque);
        estoqueContentRepository.save(content1);

        EstoqueContent content2 = new EstoqueContent();
        content2.setEstoque(estoque);
        estoqueContentRepository.save(content2);

        List<EstoqueContent> foundContents = estoqueContentRepository.findAllByEstoque(estoque);

        assertEquals(2, foundContents.size());
        assertTrue(foundContents.contains(content1));
        assertTrue(foundContents.contains(content2));
    }
}
