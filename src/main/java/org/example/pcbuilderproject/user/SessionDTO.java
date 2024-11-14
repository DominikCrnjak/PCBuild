package org.example.pcbuilderproject.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDTO {
    private Long userID;
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private String deviceId;
    private String deviceName;
    private String loginDate;
    private String expire;
    private String refreshExpire;

    }

