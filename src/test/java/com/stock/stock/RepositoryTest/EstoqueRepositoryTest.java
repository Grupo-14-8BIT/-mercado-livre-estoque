package com.stock.stock.RepositoryTest;

import com.stock.stock.entity.Estoque;
import com.stock.stock.user.User;
import com.stock.stock.repository.EstoqueRepository;
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
public class EstoqueRepositoryTest {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user = userRepository.save(user);
    }

//    @Test
//    public void testFindAllByUser() {
//        Estoque estoque1 = new Estoque();
//        estoque1.setNome("Estoque 1");
//        estoque1.setUser(user);
//        estoqueRepository.save(estoque1);
//
//        Estoque estoque2 = new Estoque();
//        estoque2.setNome("Estoque 2");
//        estoque2.setUser(user);
//        estoqueRepository.save(estoque2);
//
//        List<Estoque> foundEstoques = estoqueRepository.findAllByUser(user);
//
//        assertEquals(2, foundEstoques.size());
//        assertTrue(foundEstoques.contains(estoque1));
//        assertTrue(foundEstoques.contains(estoque2));
//    }
}
