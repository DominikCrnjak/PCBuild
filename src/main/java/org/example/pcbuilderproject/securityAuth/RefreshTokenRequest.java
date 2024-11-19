package org.example.pcbuilderproject.securityAuth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;  //Vrijednost refresh tokena
}