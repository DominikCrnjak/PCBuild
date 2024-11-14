package org.example.pcbuilderproject.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}