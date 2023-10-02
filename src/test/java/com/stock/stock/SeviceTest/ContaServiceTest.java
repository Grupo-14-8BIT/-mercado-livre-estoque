package com.stock.stock.SeviceTest;

import com.stock.stock.Service.ContaService;
import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OkHttpClient okHttpClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void testGetAllWithValidUserId() {
//        // Arrange
//        User user = new User();
//        user.setId(1);
//
//        List<Conta> mockContas = new ArrayList<>();
//        when(userRepository.findById(1)).thenReturn(Optional.of(user));
//        when(contaRepository.findContasByUsuario(user)).thenReturn(mockContas);
//
//        // Act
//        List<Conta> result = contaService.getAll(1);
//
//        // Assert
//        assertEquals(mockContas, result);
//    }

    @Test
    void testGetAllWithInvalidUserId() {

        when(userRepository.findById(1)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> contaService.getAll(1));
    }

//    @Test
//    void testCadastra() throws IOException {
//
//        String code = "sampleCode";
//        Integer state = 1;
//
//
//        User user = new User();
//        user.setId(1);
//
//        when(userRepository.findById(state)).thenReturn(Optional.of(user));
//        when(contaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Conta result = contaService.cadastra(code, state);
//
//        assertNotNull(result);
//        assertEquals(user, result.getUsuario_id());
//    }

//    @Test
//    void testCadastraWithInvalidUser() {
//        String code = "sampleCode";
//        Integer state = 1;
//
//        when(userRepository.findById(state)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> contaService.cadastra(code, state));
//    }

}
