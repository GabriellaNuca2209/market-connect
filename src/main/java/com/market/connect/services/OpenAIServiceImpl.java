package com.market.connect.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.connect.models.dtos.OpenAIRequestDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ObjectMapper objectMapper;

    public OpenAIServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String getOpenAIResponse(String prompt) {
        String jsonBody = null;
        try {
            jsonBody = objectMapper.writeValueAsString(new OpenAIRequestDTO("gpt-3.5-turbo", prompt));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer here_goes_the_api_key")
                .post(requestBody)
                .build();

        String response;
        try {
            response = new OkHttpClient().newCall(request).execute().body().string();
            log.info("Request to openAI was executed.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
