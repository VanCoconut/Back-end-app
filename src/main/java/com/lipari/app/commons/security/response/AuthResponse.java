package com.lipari.app.commons.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthResponse {

    private final String accessToken;
    private String tokenType = "Bearer ";



}
