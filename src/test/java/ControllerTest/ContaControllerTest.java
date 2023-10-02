package ControllerTest;

import com.stock.stock.repository.ContaRepository;
import com.stock.stock.user.UserRepository;
import com.stock.stock.user.config.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContaControllerTest {

    @Mock
    ContaRepository repository;

    @Mock
    UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Test
    void ContaContreollerGetall(){

    }



}
