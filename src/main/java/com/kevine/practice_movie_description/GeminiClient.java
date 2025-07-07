package com.kevine.practice_movie_description;

// lets me ask JSON for child fields, arrays, etc.
import com.fasterxml.jackson.databind.JsonNode;
// converts JSON strings to Java objects and vice versa
import com.fasterxml.jackson.databind.ObjectMapper;
// THIS makes the actual requests
import okhttp3.*;
// lets me inject values from application.properties or environment variables
import org.springframework.beans.factory.annotation.Value;
// lets me inject this claass wherever I want this app
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GeminiClient {

    // grabs my API key from app.env
    @Value("${GOOGLE_API_KEY}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();

    // this feeds the AI a prompt to come up with a description for whatever movie I tell it in Postman
    public String generateDescription(String title) {
        String prompt = "Come up with a short description for a film titled \"" + title + "\".";

        String jsonRequest = "{ \"contents\": [ { \"parts\": [ { \"text\": " + quote(prompt) + " } ] } ] }";

        RequestBody body = RequestBody.create(
                jsonRequest,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body() != null ? response.body().string() : "";
            if (!response.isSuccessful()) {
                System.err.println("Gemini API error: " + response.code() + " " + response.message());
                System.err.println("Error body: " + responseBody);
                return "Description not found - API error.";
            }

            return parseDescription(responseBody);

        } catch (IOException e) {
            e.printStackTrace();
            return "Description not found - Exception Error.";
        }
    }

    private String quote(String text) {
        return "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    private String parseDescription(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode textNode = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            if (textNode.isMissingNode() || textNode.isNull()) {
                return "Description not found.";
            }

            return textNode.asText();

        } catch (Exception e) {
            e.printStackTrace();
            return "Description not found.";
        }
    }
}
