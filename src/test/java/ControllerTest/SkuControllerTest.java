package ControllerTest;
import com.stock.stock.Controller.SkuController;
import com.stock.stock.Service.SkuSimplesService;
import com.stock.stock.dto.SkuSimplesDTO;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.repository.SkuSimplesRepository;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import com.stock.stock.user.config.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SkuControllerTest {

    @InjectMocks
    private SkuController skuController;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SkuSimplesService skuSimplesService;

    @Mock
    private SkuSimplesRepository skuSimplesRepository;

    @Mock
    private HttpServletRequest request;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetch() {
        User user = new User();
        String jwt = "mocked-jwt-token";
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtService.extractUsername(jwt)).thenReturn(user.getEmail());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<String> expectedResult = ResponseEntity.ok("Atualizado com sucesso");
        when(skuSimplesService.fetch(user)).thenReturn(expectedResult);

        ResponseEntity<String> response = skuController.fetch(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult.getBody(), response.getBody());
    }

    @Test
    public void testMudarSkuAnuncio() {
        // Mocking user and service behavior
        User user = new User();
        user.setEmail("user@example.com");

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer your-jwt-token");
        when(jwtService.extractUsername("your-jwt-token")).thenReturn("user@example.com");
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        SkuSimples skuSimples = new SkuSimples();
        skuSimples.setSKU("old-sku");

        when(skuSimplesRepository.findBySKU("old-sku")).thenReturn(Optional.of(skuSimples));

        when(skuSimplesService.mudarSkuAnuncio(any(), any(), any())).thenReturn(ResponseEntity.ok("Success"));

        // Test the controller method
        ResponseEntity<String> response = skuController.mudarSkuAnuncio(request, "mlb123", "new-sku");
        assertEquals(ResponseEntity.ok("Success"), response);

        // Verify that the service method was called with the expected parameters
        verify(skuSimplesService).mudarSkuAnuncio(any(), eq("new-sku"), any());
    }

    @Test
    public void testUpdate() {
        // Mocking user and service behavior
        User user = new User();
        user.setEmail("user@example.com");

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer your-jwt-token");
        when(jwtService.extractUsername("your-jwt-token")).thenReturn("user@example.com");
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        SkuSimples skuSimples = new SkuSimples();
        skuSimples.setSKU("existing-sku");
        skuSimples.setUser(user);

        SkuSimplesDTO skuSimplesDTO = new SkuSimplesDTO();
        skuSimplesDTO.setSKU("new-sku");
        skuSimplesDTO.setNome("New SKU");
        skuSimplesDTO.setDescricao("New SKU Description");
        skuSimplesDTO.setFoto("New SKU Photo");

        when(skuSimplesRepository.findBySKU("existing-sku")).thenReturn(Optional.of(skuSimples));
        when(skuSimplesService.update(eq(skuSimplesDTO), eq("existing-sku"))).thenReturn(ResponseEntity.ok("Success"));

        // Test the controller method
        ResponseEntity<String> response = skuController.update(request, "existing-sku", skuSimplesDTO);
        assertEquals(ResponseEntity.ok("Success"), response);

        // Verify that the service method was called with the expected parameters
        verify(skuSimplesService).update(eq(skuSimplesDTO), eq("existing-sku"));
    }

}
