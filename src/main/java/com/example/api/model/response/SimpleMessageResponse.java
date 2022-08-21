package com.example.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleMessageResponse {
    Boolean ok;
    String message;
}
