package com.stock.stock.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.stock.stock.entity.Conta;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.repository.SkuSimplesRepository;
import com.stock.stock.responses.ListItems;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkuSimplesService {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Value("${application.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Autowired
    private SkuSimplesRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContaRepository contaRepository;



    public ResponseEntity<String> fetch(User usuario ) {

      List<Conta> contas = contaRepository.findContasByUsuario(usuario);

      if ( contas.isEmpty()) {
          throw new RuntimeException("nenhuma conta ativa");

      }
      contas.forEach( conta -> {
          Unirest.setTimeouts(0, 0);
          HttpResponse<String> json = null;
          try {
              json = Unirest.get("https://api.mercadolibre.com/users/" + conta.getConta_id() + "/items/search?limit=100")
                      .header("Authorization", "Bearer " + conta.getAcess_token())
                      .asString();
          } catch (UnirestException e) {
              throw new RuntimeException(e);
          }
          // Get the body of the response.
          String body = json.getBody();

          // Create a JsonParser object.
          ObjectMapper mapper = new ObjectMapper();
          JsonParser parser = null;


          int total;
          try {
              parser = mapper.getFactory().createParser(body);


              // Deserialize the body into a Java object.
              JsonNode node = parser.readValueAsTree();

              // Get the paging object.
              JsonNode pagingNode = node.path("paging");

              // Get the limit.
              int limit = pagingNode.path("limit").asInt();

              // Get the offset.
              int offset = pagingNode.path("offset").asInt();

              // Get the total.
              total = pagingNode.path("total").asInt();

              String anuncios = pagingNode.path("results").asText();
              System.out.println(anuncios);

              // Print the values.
              System.out.println("Offset: " + offset);
              System.out.println("Total: " + total);


          } catch (IOException e) {
              throw new RuntimeException(e);
          };

          Integer paginas = ( total /100 ) + 1;
          List<String> anuncios = new ArrayList<>();

          for (int i = 0; i <= paginas; i++ ) {

              Unirest.setTimeouts(0, 0);
              HttpResponse<String> json2 = null;
              try {
                  json = Unirest.get("https://api.mercadolibre.com/users/" + conta.getConta_id() + "/items/search?limit=100&offset="+i)
                          .header("Authorization", "Bearer " + conta.getAcess_token())
                          .asString();
              } catch (UnirestException e) {
                  throw new RuntimeException(e);
              }

              // Converter o JSON para um objeto Java
              GsonBuilder builder = new GsonBuilder();
              // Create a Gson object using the GsonBuilder object.
              Gson gson = builder.create();
              // Parse the JSON into a Java object.
              ListItems result = gson.fromJson(json2.getBody(), ListItems.class);
              // Print the result.
              System.out.println(result.getResults());
              result.getResults().forEach(anuncio -> {
                  anuncios.add(anuncio);
              });

              //pega o sku e o conta_id de cada anuncio

              anuncios.forEach( mlb -> {

                  //pegar mlb, conta_id e sku
                    //se mlb ja cadastrado, conta_id e sku sao os mesmos -> exit
                    //se mlb ja cadastrado, mas sku esta diferente -> sku ja cadastrado = associa o anuncio com o sku
                    //se mlb ja cadastrado, mas sku esta diferente -> sku nao cadastrado = cadastra o sku e associa com o mlb



              });


          }




          //pegar o sku e o user_id
          // repository Find By sku and User_id


      });

        return null;
    }




}
