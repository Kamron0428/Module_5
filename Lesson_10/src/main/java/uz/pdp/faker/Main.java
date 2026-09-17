package uz.pdp.faker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.datafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Faker faker = new Faker();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = Employee
                    .builder()
                    .id(UUID.randomUUID())
                    .name(faker.name().fullName())
                    .age(faker.number().numberBetween(20, 100))
                    .department(faker.commerce().department())
                    .salary(faker.number().numberBetween(1, 100) * 100000)
                    .gender(faker.gender().binaryTypes())
                    .build();
            employees.add(employee);
        }

        employees.stream()
                .max(Comparator.comparing(Employee::getAge))
                .ifPresent(System.out::println);
        employees.stream()
                .min(Comparator.comparing(Employee::getAge))
                .ifPresent(System.out::println);

        Map<String, List<Employee>> collect = employees.stream()
                .filter(employee -> employee.getAge() > 40)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.groupingBy(Employee::getDepartment));

        collect.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

    }

}


@Data
@Builder
@AllArgsConstructor
class Employee {
    private UUID id;
    private String name;
    private int age;
    private String department;
    private double salary;
    private String gender;
}
