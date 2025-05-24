package org.example;

import com.google.gson.Gson;
import org.example.client.SimpleHttpClient;
import org.example.dtos.PostRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class PostJsonExample {
    public static void main(String[] args) {
        try {
            PostRequest postRequest = new PostRequest("Cavaleiros Templarios",
                    "Aprenda sobre os cavaleiros templarios dos zodiacos",
                    1);
            String postResponse = SimpleHttpClient.post("https://jsonplaceholder.typicode.com/posts",
                    postRequest);
            System.out.println("Post Response: " + postResponse);

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testPostMunheca() throws URISyntaxException, IOException {
        URL url = new URI("https://jsonplaceholder.typicode.com/posts").toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

//            String jsonInputString = """
//                    {
//                        "title": "Olá Post",
//                        "body": "Esse é uma escrita muito legal",
//                        "userId": 1
//                    }
//                    """;
        Gson gson = new Gson();
        var postRequest = new PostRequest("Olá title", "olá body do artigo", 2);
        String jsonInputString = gson.toJson(postRequest);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        int status = conn.getResponseCode();
        System.out.println("Código de resposta: " + status);

        String resposta = new String(conn.getInputStream().readAllBytes());

        System.out.println("Resposta da API: " + resposta);

        conn.disconnect();
    }
}
