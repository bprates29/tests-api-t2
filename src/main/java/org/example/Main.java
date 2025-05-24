package org.example;

import com.google.gson.Gson;
import org.example.client.SimpleHttpClient;
import org.example.dtos.AgePrediction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            String ageJson = SimpleHttpClient.get("https://api.agify.io/?name=bernardo");
            System.out.println("idade estimada: " + ageJson);

            Gson gson = new Gson();
            AgePrediction agePrediction = gson.fromJson(ageJson, AgePrediction.class);

            System.out.println("Nome: " + agePrediction.name());
            System.out.println("Idade estimada: " + agePrediction.age());
            System.out.println("Contador: " + agePrediction.count());

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void agifyExample() throws URISyntaxException, IOException {
        URL url = new URI("https://api.agify.io/?name=bernardo").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        //conn.setRequestProperty("Accept", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine).append('\n');
        }

        in.close();
        conn.disconnect();

        System.out.println("IDADE ESTIMADA: " + content);
    }

    private static void jsonPlaceHolderExample() throws URISyntaxException, IOException {
        URL url = new URI("https://jsonplaceholder.typicode.com/posts/1").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine).append('\n');
        }

        in.close();
        conn.disconnect();

        System.out.println("Resposta JSON: " + content);
    }

    private static void exampleRestApi1() throws URISyntaxException, IOException {
        //1. Criar URL
        URL url = new URI("https://api.exemplo.com/dados").toURL();

        //2. Abrir conexão
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //3. Definir método
        conn.setRequestMethod("GET");

        //4. Definir headears
        conn.setRequestProperty("Accept", "application/json");

        // 5. Obter Status HTTP
        int status = conn.getResponseCode();

        //6. Ler resposta
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        //7. Fechar recuersos
        in.close();
        conn.disconnect();

        System.out.println(content);
    }
}