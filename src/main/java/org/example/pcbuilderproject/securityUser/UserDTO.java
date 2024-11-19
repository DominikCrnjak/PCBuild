package org.example.pcbuilderproject.securityUser;

import lombok.*;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String username;
    private String firstname;
    private String lastname;

    private String phoneNumber;
    private String email;

    private String role;

    private boolean enabled;



}
