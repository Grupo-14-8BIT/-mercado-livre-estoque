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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EstoqueContentRepositoryTest {

    @Autowired
    private EstoqueContentRepository estoqueContentRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;
    private Estoque estoque;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        estoque = new Estoque();
        estoque.setNome("Test Estoque");
        estoque.setUser(user);
        estoqueRepository.save(estoque);

        EstoqueContent content1 = new EstoqueContent();
        content1.setEstoque(estoque);
        content1.setUser(user);
        estoqueContentRepository.save(content1);

        EstoqueContent content2 = new EstoqueContent();
        content2.setEstoque(estoque);
        content2.setUser(user);
        estoqueContentRepository.save(content2);
    }

    @Test
    public void testFindAllByEstoque() {
        List<EstoqueContent> foundContents = estoqueContentRepository.findAllByEstoque(estoque);

        assertEquals(2, foundContents.size());
    }
}
