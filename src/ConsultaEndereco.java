import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsultaEndereco {
    public Endereco buscaCep(String uf, String cidade, String logradouro) {
        try {
            uf = uf.trim().toUpperCase();
            cidade = cidade.trim();
            logradouro = logradouro.trim();

            if (cidade.length() < 3 || logradouro.length() < 3) {
                throw new IllegalArgumentException("Cidade e logradouro devem ter no mínimo 3 caracteres");
            }

            String cidadeEncoded = URLEncoder.encode(cidade, StandardCharsets.UTF_8).replace("+", "%20");
            String logradouroEncoded = URLEncoder.encode(logradouro, StandardCharsets.UTF_8).replace("+", "%20");

            URI endereco = URI.create("https://viacep.com.br/ws/" +
                    uf + "/" +
                    cidadeEncoded + "/" +
                    logradouroEncoded +
                    "/json/");

            System.out.println("URL de consulta: " + endereco); // Para debug

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();

            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na consulta. Código HTTP: " + response.statusCode());
            }

            String responseBody = response.body();
            
            if (responseBody == null || responseBody.isEmpty()) {
                return null;
            }

            try {
                Gson gson = new Gson();
                Endereco[] enderecosArray = gson.fromJson(responseBody, Endereco[].class);

                if (enderecosArray != null && enderecosArray.length > 0) {
                    return enderecosArray[0];
                } else {
                    System.out.println("Nenhum endereço encontrado para os critérios fornecidos.");
                    return null;
                }
            } catch (JsonSyntaxException e) {
                System.out.println("Resposta da API: " + responseBody);
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível buscar o CEP desse endereco: " + e.getMessage());
        }
    }
}