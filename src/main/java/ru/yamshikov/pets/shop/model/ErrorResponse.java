package ru.yamshikov.pets.shop.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
    private String timestamp;
    public ErrorResponse(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
