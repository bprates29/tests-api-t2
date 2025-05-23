package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class SimpleHttpClient {

    public static String get(String urlStr) throws URISyntaxException, IOException {
        URL url = new URI(urlStr).toURL();
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

        return content.toString();
    }

}
