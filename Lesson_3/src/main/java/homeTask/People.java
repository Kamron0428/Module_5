package homeTask;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class People {
    private String name;
    private int age;
    private String address;
}

class PeopleMain {
    public static void main(String[] args) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            People people = People
                    .builder()
                    .name(faker.name().name())
                    .age(faker.number().numberBetween(1, 30))
                    .address(faker.address().cityName())
                    .build();
            System.out.println(people);
        }
    }
}
