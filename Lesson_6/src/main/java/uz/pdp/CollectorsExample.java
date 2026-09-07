package uz.pdp;

import uz.pdp.Employee;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.*;

public class CollectorsExample {
    static void main() {

       /* List<Employee> uzb = empList.stream()
                .filter(emp -> emp.getName().startsWith("A"))
                .limit(2)
                .collect(Collectors.toList());*/

       /* List<String> collect = Stream.of("java", ".net", "python", "Scala", "Kotlin", "Groovy")
                .map(String::toUpperCase)
                .toList();
//                .collect(Collectors.toList());
        System.out.println(collect);*/

       /* Set<String> collect = Stream.of("java",
                        "java",
                        ".net",
                        "python",
                        "Scala",
                        "Kotlin",
                        "Groovy")
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        System.out.println(collect);*/
       /* HashSet<String> collect = Stream.of("java",
                        "java",
                        ".net",
                        "python",
                        "Scala",
                        "Kotlin",
                        "Groovy")
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect);*/
/*
        Map<String, Integer> integerMap = Stream.of("java", "java", "java", ".net", "python", "Scala", "Kotlin", "Groovy", "JaVa")
                .map(String::toUpperCase)
                .collect(Collectors.toMap(String::toLowerCase,
                        String::length,
                        (k1, k2) -> k1));

        integerMap.forEach((k, v) -> System.out.println(k + "->" + v));

        String joinedString = Stream.of("java", ".net", "python", "Scala", "Kotlin", "Groovy", "JaVa")
                .map(String::toUpperCase)
                .collect(Collectors.joining(", ", "[ ", " ]"));

        System.out.println(joinedString);*/

/*
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
                .collect(Collectors.groupingBy(Employee::getAge));


        collect.forEach((k, v) -> {

            System.out.println(k);
            v.forEach(System.out::println);

        });*/

        Stream.of()
                .collect(Collectors.reducing((l1, l2) -> l1 + ", " + l2))
                .ifPresentOrElse(
                        (r) -> System.out.println(r),
                        () -> System.out.println("There is no Items ")
                );



    }
}