package homeTask;

import com.github.javafaker.Faker;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Animal {
    private String animalType;
    private String animalSound;
}

class Main {
    public static void main(String[] args) {
        Faker animalFaker = new Faker();
        for (int i = 0; i < 10; i++) {
            Animal animal = Animal
                    .builder()
                    .animalType(animalFaker.animal().name())
                    .animalSound("WOW")
                    .build();
            System.out.println(animal);
        }

    }
}
