package com.stock.stock.ServiceTest;

import com.mashape.unirest.http.Unirest;
import com.stock.stock.Service.SkuSimplesService;
import com.stock.stock.entity.Anuncio;
import com.stock.stock.entity.Conta;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.repository.AnuncioRepository;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.repository.SkuSimplesRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class SkuSimplesServiceTest {

    @Mock
    private SkuSimplesRepository skuSimplesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private AnuncioRepository anuncioRepository;

    @InjectMocks
    private SkuSimplesService skuSimplesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testFetch() {
//        // Create a user for testing
//        User user = new User();
//        user.setEmail("test@example.com");
//
//        // Create a list of accounts for the user
//        List<Conta> contas = new ArrayList<>();
//        Conta conta1 = new Conta();
//        conta1.setUsuario(user);
//        // Set other properties for conta1
//        contas.add(conta1);
//
//        // Mock the repository methods to return the expected values
//        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
//        when(contaRepository.findContasByUsuario(user)).thenReturn(contas);
//
//        // Create a mock response for the Unirest call
//        HttpResponse<String> mockResponse = mock(HttpResponse.class);
//        when(mockResponse.body()).thenReturn("sda");
//
//        // Mock the Unirest call to return the mock response
//        when(Unirest.get(anyString()))
//                .thenReturn(Unirest.get("https://api.mercadolibre.com/users/1/items/search?limit=100")
//                        .header("Authorization", "Bearer your_mocked_token_here")
//                );
//
//        // Create a mock SkuSimples object for testing
//        SkuSimples skuSimples = new SkuSimples();
//        skuSimples.setId(1);
//        skuSimples.setSKU("mocked_sku");
//        skuSimples.setUser(user);
//
//        // Mock the repository methods to return the expected values
//        when(skuSimplesRepository.findBySKU("mocked_sku")).thenReturn(Optional.of(skuSimples));
//        when(anuncioRepository.findAnuncioByMlb("mocked_mlb")).thenReturn(Optional.of(new Anuncio()));
//
//        // Call the fetch method
//        skuSimplesService.fetch(user);
//
//        // Add your assertions here to verify the behavior of the service method
//    }
}
