package com.stock.stock.RepositoryTest;

import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Estoque;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.user.User;
import com.stock.stock.repository.EstoqueRepository;
import com.stock.stock.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstoqueRepositoryTest {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Test
    public void testSaveEstoque() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        Estoque estoque = new Estoque();
        estoque.setNome("Test Estoque");
        estoque.setUser(user);
        estoqueRepository.save(estoque);

        Optional<Estoque> savedEstoque = estoqueRepository.findById(estoque.getId());
        assertTrue(savedEstoque.isPresent());
        assertEquals("Test Estoque", savedEstoque.get().getNome());
        assertEquals(user, savedEstoque.get().getUser());
    }

    @Test
    public void testFindAllByUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        Estoque estoque1 = new Estoque();
        estoque1.setNome("Estoque 1");
        estoque1.setUser(user);
        estoqueRepository.save(estoque1);

        Estoque estoque2 = new Estoque();
        estoque2.setNome("Estoque 2");
        estoque2.setUser(user);
        estoqueRepository.save(estoque2);

        User anotherUser = new User();
        anotherUser.setEmail("another@example.com");
        anotherUser.setPassword("password");
        userRepository.save(anotherUser);

        Estoque anotherUserEstoque = new Estoque();
        anotherUserEstoque.setNome("Another User's Estoque");
        anotherUserEstoque.setUser(anotherUser);
        estoqueRepository.save(anotherUserEstoque);

        List<Estoque> userEstoques = estoqueRepository.findAllByUser(user);
        assertEquals(2, userEstoques.size());
    }

    @Test
    public void testFindByContasContains() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        Estoque estoque = new Estoque();
        estoque.setNome("Test Estoque");
        estoque.setUser(user);
        estoqueRepository.save(estoque);

        Conta conta = new Conta();
        conta.setUsuario(user);
        conta.setEstoque(estoque);
        contaRepository.save(conta);

        Optional<Estoque> foundEstoque = estoqueRepository.findByContasContains(conta);
        assertTrue(foundEstoque.isPresent());
        assertEquals("Test Estoque", foundEstoque.get().getNome());
        assertEquals(user, foundEstoque.get().getUser());
    }
}