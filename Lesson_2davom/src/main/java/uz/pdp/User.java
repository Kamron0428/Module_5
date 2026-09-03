package uz.pdp;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends Religion {
    private Long id;
    private String username;
    private String email;
    private int age;
}
