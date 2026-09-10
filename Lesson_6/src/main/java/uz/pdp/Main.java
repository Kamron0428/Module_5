package uz.pdp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Employee> empList = List.of(
                new Employee("Javohir Elmurodov", "UZB", "SOFTWARE ENGINEER", 28),
                new Employee("John Doe", "US", "MANAGER ", 108),
                new Employee("Akmal Turdiyev", "UZB", "SALES_MANAGER", 29),
                new Employee("John Leg", "GER", "MANAGER", 25),
                new Employee("Akobir Jo'raqulov", "US", "DOCTOR", 17),
                new Employee("Rayxona Sariyeva", "JAP", "COACH", 17),
                new Employee("Kamron Omonov", "VOD", "SINGLE", 17),
                new Employee("Akbar Akbarov", "US", "FOOTBALL PALYER", 18),
                new Employee("Sardor G'ofurov", "ANG", "QA ENGINEER", 17),
                new Employee("Akbar Akbarov", "US", "TEACHER", 20),
                new Employee("Akbar Akbarov", "US", "SOFTWARE ENGINEER", 17)
        );


        Map<Integer, List<Employee>> collect = empList.stream()
                .filter(employee -> employee.getName().startsWith("A") && employee.getName().length()>4)
                .collect(Collectors.groupingBy(Employee::getAge));

        System.out.println(collect);

    }
}

@Data
@AllArgsConstructor
class Employee {
    private String name;
    private String country;
    private String position;
    private int age;
}

