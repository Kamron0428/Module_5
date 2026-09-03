package homeTask;

import com.github.javafaker.Faker;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class App {
    private String name;
    private String author;
    private String version;

}

class AppMain {
    public static void main(String[] args) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            App app = App
                    .builder()
                    .name(faker.app().name())
                    .author(faker.app().author())
                    .version(faker.app().version())
                    .build();
            System.out.println(app);
        }
    }
}
