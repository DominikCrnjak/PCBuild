package org.example.pcbuilderproject.securityAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pcbuilderproject.securityUser.SessionDTO;
import org.example.pcbuilderproject.securityUser.UserDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UserDTO user; // Korisnički podaci
    private SessionDTO session; // Informacije o sesiji
}
