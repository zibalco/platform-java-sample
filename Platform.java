package com.company;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Platform {
    private static HttpURLConnection connection;

    public static void main(String[] args) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        StringBuilder response = null;
        try {
            URL url = new URL("https://api.zibal.ir/v1/wallet/balance");
            connection = (HttpURLConnection) url.openConnection();
            // Request setup
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer: Your access token");
            connection.setDoOutput(true);
            String jsonInputString = "{" +
                    "\"id\": \"1010101\"" +
                    "}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            int status = connection.getResponseCode();
            System.out.println(status);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//         Parse response JSON into JSONObject
        JSONObject result = new JSONObject(response.toString());
        System.out.println(result.getJSONObject("data").getInt("balance"));

    }
}
