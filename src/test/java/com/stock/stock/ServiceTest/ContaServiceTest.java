package com.stock.stock.ServiceTest;

import com.stock.stock.Service.ContaService;
import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.responses.AuthToken;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private OkHttpClient okHttpClient;

    @Mock
    private Call call;

    @Mock
    private ResponseBody responseBody;

    @Test
    public void testRefresh_SuccessScenario() throws IOException {
        Conta conta = new Conta();
        conta.setRefresh_token("mockRefreshToken");
        Integer contaId = 123;

        AuthToken authToken = new AuthToken();
        authToken.setAccess_token("newAccessToken");
        authToken.setRefresh_token("newRefreshToken");

        when(contaRepository.findById(contaId)).thenReturn(Optional.of(conta));
        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(mock(Response.class));
        when(responseBody.string()).thenReturn("{\"access_token\":\"newAccessToken\", \"refresh_token\":\"newRefreshToken\"}");

        Conta refreshedConta = contaService.refresh(contaId);

        assertNotNull(refreshedConta);
        assertEquals("newAccessToken", refreshedConta.getAcess_token());
        assertEquals("newRefreshToken", refreshedConta.getRefresh_token());
        assertTrue(refreshedConta.getExpires().isAfter(LocalDateTime.now().minusMinutes(301)));
        assertTrue(refreshedConta.getExpires().isBefore(LocalDateTime.now().plusMinutes(301)));
    }

//    @Test
//    public void testRefresh_FailureScenario() throws IOException {
//        Conta conta = new Conta();
//        conta.setRefresh_token("mockRefreshToken");
//        Integer contaId = 123;
//
//        when(contaRepository.findById(contaId)).thenReturn(Optional.of(conta));
//        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);
//        when(call.execute()).thenThrow(IOException.class);
//
//        assertThrows(RuntimeException.class, () -> contaService.refresh(contaId));
//    }
}
