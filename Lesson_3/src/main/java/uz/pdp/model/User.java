package uz.pdp.model;

import lombok.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;


    public String toString() {
        return "%s, %s, %s, %s, %s, %s".formatted(
                id,firstName, lastName, email, username, password);
    }
}
