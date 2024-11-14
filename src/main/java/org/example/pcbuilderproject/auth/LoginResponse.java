package org.example.pcbuilderproject.auth;

import lombok.Data;
import org.example.pcbuilderproject.user.SessionDTO;
import org.example.pcbuilderproject.user.UserDTO;

@Data
public class LoginResponse {

    private UserDTO user; // Informacije o korisniku
    private SessionDTO session; // Informacije o sesiji


}