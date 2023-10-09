package com.stock.stock.RepositoryTest;

import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaRepositoryTest {

    @InjectMocks
    private ContaRepository contaRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindContasByUsuario() {
        User user = new User();
        user.setId(1);

        Conta conta = new Conta();
        conta.setId(1);
        conta.setContaid(12345L);
        conta.setAcess_token("access_token");
        conta.setRefresh_token("refresh_token");
        conta.setExpires(LocalDateTime.now().plusMinutes(300));
        conta.setUsuario(user);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        contaRepository.save(conta);

        List<Conta> foundContas = contaRepository.findContasByUsuario(user);

        assertThat(foundContas).hasSize(1);
        assertThat(foundContas.get(0)).isEqualTo(conta);
    }

}
