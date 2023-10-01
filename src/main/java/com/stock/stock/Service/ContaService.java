package com.stock.stock.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.responses.AuthToken;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import jakarta.transaction.Transactional;
import okhttp3.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Value("${application.CLIENT_SECRET}")
    private String CLIENT_SECRET;



    @Autowired
    private ContaRepository repository;


    @Autowired
    private UserRepository userRepository;

    public List<Conta> getAll(Integer id) {

        List<Conta> contas = repository.findContasByUsuario(userRepository.findById(id).get());

        if ( contas !=  null && !contas.isEmpty()){
            try {
                return  repository.findContasByUsuario(userRepository.findById(id).get());

            } catch (Exception e) {
                throw new RuntimeException( "Nao foi possivel encontrar contas nesse usuarui" +e.getMessage() + e.getCause());
            }
        } else {
            throw new RuntimeException("O usuario nao tem nenhuma conta associada");
        }

    }

    

    @Transactional
    public Conta cadastra ( String code, Integer state) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=authorization_code&client_id="+APP_ID+"&client_secret="+CLIENT_SECRET+"&code="+code+"&redirect_uri="+YOUR_URL);
        Request request = new Request.Builder()
                .url("https://api.mercadolibre.com/oauth/token")
                .method("POST", body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();

        ObjectMapper mapper = new ObjectMapper();
        AuthToken authToken = mapper.readValue(response.body().string(), AuthToken.class);

        System.out.println(response);
        System.out.println(authToken.getAccess_token());
        System.out.println(authToken.getRefresh_token());
        System.out.println(authToken.getToken_type());
        System.out.println(authToken.getExpires_in());
        System.out.println(authToken.getToken_type());
        System.out.println(authToken.getScope());

        Conta conta = new Conta();
        BeanUtils.copyProperties(authToken, conta);

        Optional<User> user = userRepository.findById(state);

        System.out.println(user.get().getId());

        if (user.isEmpty() || user == null) {
            throw new RuntimeException("Usuario inexistente");
        }



            conta.setUsuario_id(user.get());

            repository.save(conta);

            return conta;



    }


    public ResponseEntity<String> delete(User user, Integer conta_id) {

      Optional<Conta> conta =   repository.findById( conta_id);

      if ( repository.findContasByUsuario(user).contains(conta)) {
          repository.delete(conta.get());
          return ResponseEntity.ok("Conta desvinculada com sucesso");
      } else {
          throw new RuntimeException("Conta nao encontrada");
      }

    }
}
