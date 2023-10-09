package com.stock.stock.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.stock.stock.dto.SkuSimplesDTO;
import com.stock.stock.entity.Anuncio;
import com.stock.stock.entity.Conta;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.repository.AnuncioRepository;
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
import java.util.Optional;

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
    @Autowired
    private AnuncioRepository anuncioRepository;


    public ResponseEntity<String> fetch(User usuario ) {

      List<Conta> contas = contaRepository.findContasByUsuario(usuario);

      if ( contas.isEmpty()) {
          throw new RuntimeException("nenhuma conta ativa");

      }
      contas.forEach( conta -> {
                  Unirest.setTimeouts(0, 0);
                  HttpResponse<String> json = null;
                  try {
                      json = Unirest.get("https://api.mercadolibre.com/users/" + conta.getContaid() + "/items/search?limit=100")
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
                  }
                  ;
///////////////////////////////////////////////////////////////////////////////
                  // OBTER TODOS OS IDS DOS ANUNCIOS //
                  Integer paginas = (total / 100) + 1;
                  List<String> anuncios = new ArrayList<>();

                  for (int i = 0; i <= paginas; i++) {

                      Unirest.setTimeouts(0, 0);
                      HttpResponse<String> json2 = null;
                      try {
                          json2 = Unirest.get("https://api.mercadolibre.com/users/" + conta.getContaid() + "/items/search?limit=100&offset=" + i)
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
                          if (!anuncios.contains(anuncio)) {
                              anuncios.add(anuncio);
                          }

                      });
                  }
                  ;
///////////////////////////////////////////////////////////////////////////////
                  // pega o sku e o conta_id de cada anuncio //

                  anuncios.forEach(mlb -> {

                      //pegar mlb, conta_id e sku

                      Unirest.setTimeouts(0, 0);
                      HttpResponse<String> response;
                      try {
                          response = Unirest.get("https://api.mercadolibre.com/items?ids=" + mlb)
                                  .header("Authorization", "Bearer " + conta.getAcess_token())
                                  .asString();
                          String body2 = response.getBody();
                          System.out.println(body2);
                          JsonParser parser2;
                          try {
                              parser2 = mapper.getFactory().createParser(body2);
                              try {
                                  JsonNode node = parser2.readValueAsTree();
                                  String sku = node.path(0).path("body").path("attributes").path(3).path("value_name").asText();
                                  String conta_id = node.path(0).path("body").path("seller_id").asText();

                                  System.out.println("FETCH ANUNCIO -> SKU:     " + sku + "  || CONTA-ID : " + conta_id  + "  || ID DO ANUNCIO : " + mlb);

                                  //se mlb ja cadastrado, e sku sao os mesmos -> exit
                                  Optional<Anuncio> anuncio = anuncioRepository.findAnuncioByMlb(mlb);
                                  Optional<SkuSimples> skuSimplesOptional = repository.findBySKU(sku);
                                  if (anuncio.isPresent() && skuSimplesOptional.isPresent()){
                                      //se o SKU for o mesmo
                                      if (anuncio.get().getSkuSimples().getSKU() == sku) {
                                          anuncioRepository.save(anuncio.get());
                                          System.out.println(anuncio +"  ->  "+ sku +"   = Nao aterado");

                                      } else {
                                          anuncio.get().setSkuSimples(skuSimplesOptional.get());
                                          anuncioRepository.save(anuncio.get());
                                      }

                                  } else{
                                        // se o anuncio nao foi salvo mas o sku ja existe
                                      if (!anuncio.isPresent() && skuSimplesOptional.isPresent()){
                                          Anuncio novo_anuncio = new Anuncio();
                                          novo_anuncio.setSkuSimples(skuSimplesOptional.get());
                                          novo_anuncio.setMlb(mlb);
                                          novo_anuncio.setConta(conta);
                                          anuncioRepository.save(novo_anuncio);

                                      } else {
                                        //se o anuncio ja existe mas o sku nao
                                          if ( anuncio.isPresent() && !skuSimplesOptional.isPresent()){
                                          SkuSimples novoSku = new SkuSimples();
                                          novoSku.setSKU(sku);
                                          novoSku.setUser(conta.getUsuario());
                                          repository.save(novoSku);
                                          anuncio.get().setSkuSimples(novoSku);



                                          } else {
                                              // se ambos nao existir
                                              if ( !anuncio.isPresent() && !skuSimplesOptional.isPresent()){
                                                      Anuncio novo_anuncio = new Anuncio();
                                                      novo_anuncio.setMlb(mlb);
                                                      novo_anuncio.setConta(conta);
                                                      SkuSimples novoSku = new SkuSimples();
                                                     novoSku.setSKU(sku);
                                                     novoSku.setUser(conta.getUsuario());
                                                  novo_anuncio.setSkuSimples(novoSku);


                                                  repository.save(novoSku);
                                                    anuncioRepository.save(novo_anuncio);
                                              }


                                          }


                                      }

                                  }



                              } catch (IOException e) {
                                  throw new RuntimeException(e);
                              }
                          } catch (IOException e) {
                              throw new RuntimeException(e);
                          }
                      } catch (UnirestException e) {
                          throw new RuntimeException(e);
                      }
        /////////////////////////////////////////////////////////////////////////////////////

                  });


              });
        System.out.println("Atualizado com sucesso");
        return ResponseEntity.ok("Atualizado com sucesso");



    }

    public ResponseEntity<String> update (SkuSimplesDTO skuSimplesDTO, String sku) {

        Optional<SkuSimples> skuOptional = repository.findBySKU(sku);
        if( skuOptional.isPresent()) {
            List<Anuncio> anuncioList = anuncioRepository.findAllBySkuSimples(skuOptional.get());
            anuncioList.forEach(anuncio -> {
                mudarSkuAnuncio(anuncio,skuSimplesDTO.getSKU(),anuncio.getConta());
            });
            skuOptional.get().setSKU(skuSimplesDTO.getSKU());
            skuOptional.get().setNome(skuSimplesDTO.getNome());
            skuOptional.get().setDescricao(skuSimplesDTO.getDescricao());
            skuOptional.get().setFoto(skuSimplesDTO.getFoto());
            repository.save(skuOptional.get());
        } else {
            return ResponseEntity.badRequest().body("sku nao existe");
        }



        return null;
    };

    public ResponseEntity<String> mudarSkuAnuncio (Anuncio anuncio, String sku, Conta conta) {

        Optional<SkuSimples> optionalSkuSimples = repository.findBySKU(sku);

        if ( !optionalSkuSimples.isPresent()) {
            SkuSimples novoSku = new SkuSimples();
            novoSku.setSKU(sku);
            novoSku.setUser(conta.getUsuario());
            repository.save(novoSku);
        }

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.put("https://api.mercadolibre.com/items/"+anuncio.getMlb())
                    .header("Authorization", "Bearer "+ conta.getAcess_token())
                    .header("Content-Type", "application/json")
                    .body("{\n   \"attributes\": [\n       {\n           \"id\": \"SELLER_SKU\",\n           \"value_name\": \""+optionalSkuSimples.get().getSKU()+"\"\n       }\n   ]\n}")
                    .asString();

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        fetch(conta.getUsuario());
        System.out.println("SKU do anuncio  "+ anuncio.getMlb()+ "  atualizado com sucesso");
        return ResponseEntity.ok("SKU do anuncio  "+ anuncio.getMlb()+ "  atualizado com sucesso");
    };




}
