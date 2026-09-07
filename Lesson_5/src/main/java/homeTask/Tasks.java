package homeTask;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Tasks {

    public static void main(String[] args) {

        m1();
        m2();
        m3();
        m4();
        m5();
        m6();
        m7();

    }

    private static void m7() {
        List<String> groupA = List.of("Ali", "Vali");
        List<String> groupB = List.of("Hasan", "Husan");
        List<String> groupC = List.of("Dilshod", "Jasur");

        List<List<String>> allGroups = List.of(groupA, groupB, groupC);

        List<String> allStudents = allGroups.stream()
                .flatMap(Collection::stream)
                .toList();

        System.out.println(allStudents);
    }

    private static void m6() {
        Stream<Integer> integerStream = Stream.of(1, 5, 2, -1, 25, 10, 12, 12, 12, 5, 28, -44);
        System.out.println(integerStream.count());
    }

    private static void m5() {
        Stream<Integer> integerStream = Stream.of(1, 5, 2, -1, 25, 10, 12, 12, 12, 5, 28, -44);
        integerStream.distinct().forEach(System.out::println);
    }

    private static void m4() {
        Stream<Integer> integerStream = Stream.of(1, 5, 2, -1, 25, 10, 12, 28, -44);
        integerStream
                .min(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }

    private static void m3() {
        Stream<Integer> integerStream = Stream.of(1, 5, 2, -1, 25 - 10, 12, 28, -44);
        integerStream
                .sorted()
                .toList()
                .forEach(System.out::println);
    }

    private static void m2() {
        Stream<Integer> integerStream = Stream.of(1, 5, 2, -1, 25 - 10, 12, 28, -44);
        boolean b = integerStream
                .anyMatch(number -> number > 2);
        System.out.println(b);

        Stream<Integer> integerStream2 = Stream.of(1, 5, 2, -1, 25 - 10, 12, 28, -44);
        boolean b2 = integerStream2
                .allMatch(number -> number > -45);
        System.out.println(b2);
        Stream<Integer> integerStream3 = Stream.of(1, 5, 2, -1, 25 - 10, 12, 28, -44);

        boolean b3 = integerStream3
                .noneMatch(number -> number > 44);
        System.out.println(b3);
    }

    private static void m1() {
        Stream<Integer> integerStream = Stream.of(1, 5, 2, -1, 25 - 10, 12, 28, -44);
        integerStream
                .filter(number -> number % 2 == 0)
                .skip(1)
                .limit(3)
                .sorted()
                .forEach(System.out::println);
    }

}