package homeTask;

import com.github.javafaker.Faker;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProgrammingLanguages {
    private String name;
    private String author;

}
class FakerMain {
    public static void main(String[] args) {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            ProgrammingLanguages languages = ProgrammingLanguages
                    .builder()
                    .name(faker.programmingLanguage().name())
                    .author(faker.programmingLanguage().creator())
                    .build();
            System.out.println(languages);
        }
    }
}
