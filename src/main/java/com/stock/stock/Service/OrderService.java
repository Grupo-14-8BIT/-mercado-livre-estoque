package com.stock.stock.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Order;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.repository.AnuncioRepository;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.repository.OrderRepository;
import com.stock.stock.repository.SkuSimplesRepository;
import com.stock.stock.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class OrderService {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Value("${application.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Autowired
    private SkuSimplesRepository skuSimplesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private OrderRepository repository;



    public Order newOrder (Long mlbId, Conta conta) {

        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.get("https://api.mercadolibre.com/orders/"+mlbId)
                    .header("Authorization", "Bearer "+conta.getAcess_token())
                    .asString();

            String body2 = response.getBody();
            System.out.println(body2);
            JsonParser parser;
            ObjectMapper mapper = new ObjectMapper();
            try {

                Order new_order = new Order();

                parser = mapper.getFactory().createParser(body2);
                JsonNode node = parser.readValueAsTree();


                 Long MlbId = node.path("id").asLong();
                 Optional<SkuSimples> order_sku = skuSimplesRepository.findBySKU(node.path("order_items").path(0).path("item").path("seller_sku").asText());
                if ( order_sku.isPresent()){
                    new_order.setSku(order_sku.get());
                }
                 Conta conta_order = conta;
                new_order.setConta( conta_order);
                new_order.setMlbid(mlbId);
                 Integer quantidade = node.path("order_items").path(0).path("quantity").asInt();
                    new_order.setQuantidade(quantidade);
                 String comprador = node.path("buyer").path("nickname").asText();
                    new_order.setComprador(comprador);
                 String status = node.path("status").asText();
                    new_order.setStatus(status);


                    repository.save(new_order);

                    return new_order;









            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }



    }


}
