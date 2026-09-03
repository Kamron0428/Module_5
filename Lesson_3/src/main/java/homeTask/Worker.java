package homeTask;

import com.github.javafaker.Faker;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Worker {
    private String name;
    private int age;
    private String workName;
}

class WorkerMain {
    public static void main(String[] args) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Worker worker = Worker
                    .builder()
                    .name(faker.name().name())
                    .age(faker.number().numberBetween(0, 30))
                    .workName(faker.job().title())
                    .build();
            System.out.println(worker);
        }
    }
}
