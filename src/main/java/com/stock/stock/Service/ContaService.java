package com.stock.stock.Service;

import com.google.gson.Gson;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.responses.AuthToken;
import jakarta.transaction.Transactional;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public class FullResponseBuilder {
        public static String getFullResponse(HttpURLConnection con) throws IOException {
            StringBuilder fullResponseBuilder = new StringBuilder();

            fullResponseBuilder.append(con.getResponseCode())
                    .append(" ")
                    .append(con.getResponseMessage())
                    .append("\n");


            // read headers


            con.getHeaderFields().entrySet().stream()
                    .filter(entry -> entry.getKey() != null)
                    .forEach(entry -> {
                        fullResponseBuilder.append(entry.getKey()).append(": ");
                        List headerValues = entry.getValue();
                        Iterator it = headerValues.iterator();
                        if (it.hasNext()) {
                            fullResponseBuilder.append(it.next());
                            while (it.hasNext()) {
                                fullResponseBuilder.append(", ").append(it.next());
                            }
                        }
                        fullResponseBuilder.append("\n");
                    });

            // read response content

            return fullResponseBuilder.toString();
        }
    }


    @Transactional
    public void cadastra ( String code, Integer state) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=authorization_code&client_id=6393312633221000&client_secret=4FMu9IcRQvRMXgVDHSZ9iIAfZkHyyX7N&code=TG-65170321c7a1fd0001f0452f-195710882&redirect_uri=https://template.tr1.com.br/redirect");
        Request request = new Request.Builder()
                .url("https://api.mercadolibre.com/oauth/token")
                .method("POST", body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(response);
        System.out.println(response.body().string().toString());
        System.out.println(response.message());


//
//        URL url = new URL("https://api.mercadolibre.com/oauth/token");
//
//        // Criar um objeto `HttpURLConnection` com a URL da request
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//
//        // Set the doOutput property to true
//        connection.setDoOutput(true);
//
//
//        // Configurar o método HTTP e os headers da request
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//        //Criando o body da request
//        Map<String,String> body = new HashMap<>();
//        body.put("grant_type", "authorization_code");
//        body.put("client_id", APP_ID);
//        body.put("client_secret", CLIENT_SECRET);
//        body.put("code", code);
//        body.put("redirect_uri",YOUR_URL);
//
//
//        //cofigurando o body da request
//        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
//        outputStream.writeBytes(body.toString());
//        outputStream.flush();
//
//        //enviar a request
//        System.out.printf(String.valueOf(connection));
//        connection.setInstanceFollowRedirects(true);
//
//            connection.connect();
//            System.out.println(connection.getResponseMessage());
//
//
//        System.out.println(FullResponseBuilder.getFullResponse(connection));
//
//
//


//        // Ler a resposta da request
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String line;
//        String response = "";
//        while ((line = reader.readLine()) != null) {
//            response += line;
//        }
//
//      // Transformar a resposta em um objeto JSON
//        AuthToken auth = new Gson().fromJson(response, AuthToken.class);
//
//        // Imprimir o objeto Java
//        System.out.println(auth);








    }





}
