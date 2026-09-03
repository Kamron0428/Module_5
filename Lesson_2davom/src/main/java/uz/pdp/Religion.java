package uz.pdp;


import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Religion {
    private String religion;
}
