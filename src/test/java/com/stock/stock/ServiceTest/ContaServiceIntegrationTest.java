package com.stock.stock.ServiceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stock.Service.ContaService;
import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.responses.AuthToken;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaServiceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1);
        userRepository.save(user);

        Conta conta1 = new Conta();
        conta1.setUsuario(user);
        contaRepository.save(conta1);
    }
//    @Test
//    public void testCadastra_Success() throws IOException {
//        // Mock the OkHttpClient dependency
//        OkHttpClient mockOkHttpClient = Mockito.mock(OkHttpClient.class);
//        Mockito.when(mockOkHttpClient.newCall(Mockito.any(Request.class)).execute()).thenReturn(new Response.Builder()
//                .code(200)
//                .body(Mockito.mock(ResponseBody.class))
//                .build());
//
//        // Mock the ObjectMapper dependency
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
    void testGetAllWithInvalidUserId() {
        int invalidUserId = 2;

        ResponseEntity<String> response = restTemplate.getForEntity("/api/contas/" + invalidUserId, String.class);

        assertEquals(404, response.getStatusCodeValue());

        assertTrue(response.getBody().contains("User not found"));
    }

    @Test
    void testGetAllWithValidUserId() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/contas/1", List.class);

        assertEquals(200, response.getStatusCodeValue());
        List<Conta> contas = response.getBody();

    }
}
