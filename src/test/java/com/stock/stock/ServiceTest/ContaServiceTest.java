package com.stock.stock.ServiceTest;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContaServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

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
        User user = new User();
        user.setId(1);
        userRepository.save(user);

        Conta conta1 = new Conta();
        conta1.setUsuario(user);
        contaRepository.save(conta1);
    }

//    @Test
//    void testGetAllWithValidUserId() {
//        User user = new User();
//        user.setId(1);
//
//        List<Conta> mockContas = new ArrayList<>();
//        when(userRepository.findById(1)).thenReturn(Optional.of(user));
//        when(contaRepository.findContasByUsuario(user)).thenReturn(mockContas);
//
//        List<Conta> result = contaService.getAll(1);
//
//        assertEquals(mockContas, result);
//    }

    @Test
    void testGetAllWithInvalidUserId() {

        when(userRepository.findById(1)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> contaService.getAll(1));
    }

    @Test
    void testCadastra() throws IOException {

        String code = "sampleCode";
        Integer state = 1;


        User user = new User();
        user.setId(1);

        when(userRepository.findById(state)).thenReturn(Optional.of(user));
        when(contaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Conta result = contaService.cadastra(code, state);

        assertNotNull(result);
        assertEquals(user, result.getUsuario_id());
    }

//    @Test
//    void testCadastraWithInvalidUser() {
//        String code = "sampleCode";
//        Integer state = 1;
//
//        when(userRepository.findById(state)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> contaService.cadastra(code, state));
//    }
    //    @Test
//    public void testCadastra_Success() throws IOException {
//        OkHttpClient mockOkHttpClient = Mockito.mock(OkHttpClient.class);
//        Mockito.when(mockOkHttpClient.newCall(Mockito.any(Request.class)).execute()).thenReturn(new Response.Builder()
//                .code(200)
//                .body(Mockito.mock(ResponseBody.class))
//                .build());
//
//        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
//        AuthToken authToken = new AuthToken();
//        authToken.setAccess_token("access_token");
//        authToken.setRefresh_token("refresh_token");
//        authToken.setUser_id("1");
//        Mockito.when(mockObjectMapper.readValue(Mockito.anyString(), Mockito.anyClass())).thenReturn(authToken);
//
//        ContaService contaService = new ContaService(mockOkHttpClient, mockObjectMapper);
//
//        User user = new User();
//        user.setId(1);
//
//        Conta conta = contaService.cadastra("code", user.getId());
//
//        assertEquals(1, contaRepository.count());
//
//        assertEquals("code", conta.getCode());
//        assertEquals("access_token", conta.getAcess_token());
//        assertEquals("refresh_token", conta.getRefresh_token());
//        assertEquals(1L, conta.getConta_id());
//        assertEquals(LocalDateTime.now().plusMinutes(300), conta.getExpires());
//        assertEquals(user, conta.getUsuario_id());
//    }
        @Test
    void testGetAllWithValidUserId() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/contas/1", List.class);

        assertEquals(200, response.getStatusCodeValue());
        List<Conta> contas = response.getBody();

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
