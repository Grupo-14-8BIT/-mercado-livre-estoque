package ControllerTest;
import com.stock.stock.Controller.SkuController;
import com.stock.stock.Service.SkuSimplesService;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetch() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        User user = new User();
        String jwt = "mocked-jwt-token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtService.extractUsername(jwt)).thenReturn(user.getEmail());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<String> expectedResult = ResponseEntity.ok("Atualizado com sucesso");
        when(skuSimplesService.fetch(user)).thenReturn(expectedResult);

        ResponseEntity<String> response = skuController.fetch(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult.getBody(), response.getBody());
    }

}
