package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;


    @Override
    public String toString() {
        return "%s, %s, %s, %s, %s, %s".formatted(id, fullName, phoneNumber, email, username, password);
    }
}
