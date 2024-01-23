package com.market.connect.models.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OpenAIRequestDTO {

    private String model;
    private List<MessageDTO> messages;

    public OpenAIRequestDTO(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new MessageDTO("user", prompt));
    }
}
