package org.example.pcbuilderproject.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pcbuilderproject.user.SessionDTO;
import org.example.pcbuilderproject.user.UserDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UserDTO user; // Korisnički podaci
    private SessionDTO session; // Informacije o sesiji
}
