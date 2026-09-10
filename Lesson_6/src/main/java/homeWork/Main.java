package homeWork;


import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student("Ali", "UZB", "Java", 20, 87.5),
                new Student("Vali", "UZB", "Java", 22, 91.0),
                new Student("Hasan", "UZB", "Spring", 21, 76.5),
                new Student("John", "US", "Java", 23, 88.0),
                new Student("Mike", "US", "Spring", 20, 95.5),
                new Student("Anna", "US", "Java", 21, 82.0),
                new Student("Hans", "GER", "Java", 25, 79.5),
                new Student("Emma", "GER", "Spring", 22, 93.0),
                new Student("Yuki", "JAP", "Java", 19, 89.5),
                new Student("Ken", "JAP", "Spring", 24, 72.0),
                new Student("Sardor", "UZB", "Java", 20, 95.0),
                new Student("Bobur", "UZB", "Spring", 19, 68.5),
                new Student("Sardor", "UZB", "Java", 23, 84.0)
        );


        compareToAge(students);
        compareToNameAndAge(students);
        comparingInt(students);
        listedOnlyUZB(students);
        allCountrySet(students);
        treeSetWithName(students);
        listToMap(students);
        countStudentsInCountry(students);
        testJoining(students);
        groupingByAge(students);
        groupingByCountry(students);
        countingByGroup(students);
        averageAgeByCountry(students);
        partitioningByAge(students);
        partitioningByScore(students);



    }

    private static void partitioningByScore(List<Student> students) {
        students.stream()
                .collect(Collectors.partitioningBy(s -> s.getScore() >= 85))
                .forEach((k, v) -> System.out.printf("%b -> %s\n", k, v));
    }

    private static void partitioningByAge(List<Student> students) {
        students.stream()
                .collect(Collectors.partitioningBy(s -> s.getAge() >= 21))
                .forEach((k, v) -> System.out.printf("%b -> %s\n", k, v));
    }

    private static void averageAgeByCountry(List<Student> students) {
        Map<String, Double> averageAgeByCountry = students.stream()
                .collect(Collectors.groupingBy(Student::getCountry, Collectors.averagingInt(Student::getAge)));
        averageAgeByCountry.forEach((country, avgAge) ->
                System.out.printf("%s -> %.2f%n", country, avgAge));
    }

    private static void countingByGroup(List<Student> students) {
        Map<String, Long> groupCount = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()));
        groupCount.forEach((group, count) -> System.out.println(group + " -> " + count));
    }

    private static void groupingByCountry(List<Student> students) {
        Map<String, List<Student>> collect = students.stream()
                .collect(Collectors.groupingBy(Student::getCountry));

        collect.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }

    private static void groupingByAge(List<Student> students) {
        Map<Integer, List<Student>> collect = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        collect.forEach((k, v) -> System.out.println(k + " " + v));
    }

    private static void testJoining(List<Student> students) {
        String collect = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(collect);
    }

    private static void countStudentsInCountry(List<Student> students) {
        Map<String, Integer> countryCount = students.stream()
                .collect(Collectors.toMap(Student::getCountry, student -> 1, Integer::sum));
        countryCount.forEach((country, count) -> System.out.println(country + " -> " + count));
    }

    private static void listToMap(List<Student> students) {
        students.stream()
                .collect(Collectors.toMap(Student::getName, Student::getScore, (k, v) -> k))
                .forEach((k, v) -> System.out.println(k + " " + v));
    }

    private static void treeSetWithName(List<Student> students) {
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .map(Student::getName)
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(student -> System.out.println(student.toUpperCase()));
    }

    private static void allCountrySet(List<Student> students) {
        students.stream()
                .map(Student::getCountry)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    private static void listedOnlyUZB(List<Student> students) {
        students.stream()
                .filter(student -> student.getCountry().equals("UZB"))
                .toList()
                .forEach(System.out::println);
    }

    private static void comparingInt(List<Student> students) {
        students.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .forEach(System.out::println);
    }

    private static void compareToNameAndAge(List<Student> students) {
        students.stream()
                .sorted(Comparator.comparing(Student::getName).thenComparing(Student::getAge))
                .forEach(System.out::println);
    }

    private static void compareToAge(List<Student> students) {
        students.stream()
                .sorted(Comparator.comparing(Student::getAge))
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .forEach(System.out::println);
    }

}
