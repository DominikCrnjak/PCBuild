package org.example.pcbuilderproject.securityUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDTO {
    private Long userID;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String loginDate;
    private String expire;
    private String refreshExpire;

    }

