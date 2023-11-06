package com.stock.stock.ControllerTest;


import com.stock.stock.Controller.ContaController;
import com.stock.stock.Service.ContaService;
import com.stock.stock.entity.Conta;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import com.stock.stock.user.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContaControllerTest {

    @InjectMocks
    private ContaController contaController;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContaService contaService;

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testGetAll() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        User user = new User();
//        String jwt = "mocked-jwt-token";
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
//        when(jwtService.extractUsername(jwt)).thenReturn(user.getEmail());
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        List<Conta> contas = new ArrayList<>();
//        when(contaService.getAll(Long.valueOf(user.getId()))).thenReturn(contas);
//
//        ResponseEntity<List<Conta>> response = contaController.getAll(request);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(contas, response.getBody());
//    }
//
//    @Test
//    public void testAutoriza() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        User user = new User();
//        user.setId(1);
//        String jwt = "mocked-jwt-token";
//        String expectedRedirectUrl = "https://auth.mercadolivre.com.br/authorization?response_type=code&client_id="+APP_ID+"&redirect_uri="+YOUR_URL+ "&state="+ user.getId();
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
//        when(jwtService.extractUsername(jwt)).thenReturn(user.getEmail());
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        ResponseEntity<String> response = contaController.autoriza(request);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedRedirectUrl, response.getBody());
//    }

    @Test
    public void testDelete() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        User user = new User();
        String jwt = "mocked-jwt-token";
        int contaId = 123;

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtService.extractUsername(jwt)).thenReturn(user.getEmail());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<String> expectedResult = ResponseEntity.ok("Conta desvinculada com sucesso");
        when(contaService.delete(user, contaId)).thenReturn(expectedResult);

        ResponseEntity<String> response = contaController.delete(request, contaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult.getBody(), response.getBody());
    }
    @Test
    public void testRefresh() {
        int contaId = 123;
        Conta expectedConta = new Conta();

        when(contaService.refresh(contaId)).thenReturn(expectedConta);

        ResponseEntity<Conta> expectedResult = ResponseEntity.ok(expectedConta);

        ResponseEntity<Conta> response = contaController.refresh(contaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult.getBody(), response.getBody());
    }
}
