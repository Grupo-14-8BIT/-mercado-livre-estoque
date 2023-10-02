package com.stock.stock.SeviceTest.Conta;

import com.stock.stock.Service.ContaService;
import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContaServiceUnitTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testDelete_ContaFound() {
        User user = new User();
        user.setId(1);

        Integer contaId = 1;

        Conta conta = new Conta();
        conta.setId(contaId);
        conta.setUsuario(user);

        contaRepository.save(conta);

        ResponseEntity<String> response = contaService.delete(user, contaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Conta desvinculada com sucesso", response.getBody());

        assertEquals(0, contaRepository.count());
    }


    @Test
    public void testDelete_ContaNotFound() {
        User user = new User();
        user.setId(1);

        Integer contaId = 1;

        when(contaRepository.findById(contaId)).thenReturn(Optional.empty());
        when(contaRepository.findContasByUsuario(user)).thenReturn(Collections.emptyList());

        assertThrows(RuntimeException.class, () -> contaService.delete(user, contaId));

        verify(contaRepository, times(1)).findById(contaId);
        verify(contaRepository, never()).delete(any());
    }
}
