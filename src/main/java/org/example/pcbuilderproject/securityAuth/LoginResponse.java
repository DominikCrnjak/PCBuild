package org.example.pcbuilderproject.securityAuth;

import lombok.Data;
import org.example.pcbuilderproject.securityUser.SessionDTO;
import org.example.pcbuilderproject.securityUser.UserDTO;

@Data
public class LoginResponse {

    private UserDTO user; // Informacije o korisniku
    private SessionDTO session; // Informacije o sesiji


}