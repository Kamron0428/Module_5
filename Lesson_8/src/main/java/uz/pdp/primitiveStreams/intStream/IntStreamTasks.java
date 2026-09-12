package uz.pdp.primitiveStreams.intStream;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

public class IntStreamTasks {

    public static void main(String[] args) {
/*
        maxMinTest();
        getAvarageTest();
        getSumCharNums();
        powTest();
        */
        List<Student> students = new ArrayList<>();
        students.add(new Student("Kamron", 3, 90));
        students.add(new Student("Sardor", 3, 99));
        students.add(new Student("Jasur", 1, 77));
        students.add(new Student("Asadbek", 3, 80));
        students.add(new Student("Mexribon", 2, 100));
        students.add(new Student("Asal", 2, 95));

        sumStudentScore(students);
    }

    private static void sumStudentScore(List<Student> students) {
        IntSummaryStatistics stats = students.stream()
                .filter(student -> student.getCourse() == 3 && student.getScore() > 70)
                .mapToInt(Student::getScore)
                .summaryStatistics();

        System.out.println("Shartga mos talabalar soni: " + stats.getCount());
        System.out.println("Umumiy ballar yig'indisi (sum): " + stats.getSum());
        System.out.println("O'rtacha ball (average): " + stats.getAverage());
    }

    private static void getSumCharNums() {
        String text = "JavaStream2026API";
        int sum = text.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();
        System.out.println("Raqamlar yig'indisi: " + sum);
    }

    private static void getAvarageTest() {
        IntSummaryStatistics intSummaryStatistics = IntStream.rangeClosed(1, 50)
                .filter(i -> i % 3 == 0 && i % 5 == 0)
                .summaryStatistics();

        System.out.println("Count: " + intSummaryStatistics.getCount() + " Avarage: " + intSummaryStatistics.getAverage());
    }

    private static void maxMinTest() {
        int[] arr = {12, -14, 45, 0, 9, 23, 78, -15};
        IntStream.of(arr)
                .filter(i -> i>0)
                .max()
                .ifPresent(System.out::println);

        IntStream.of(arr)
                .filter(i -> i>0)
                .min()
                .ifPresent(System.out::println);
    }

    private static void powTest() {
        IntStream.range(1, 100)
                .filter(i -> i % 2 != 0)
                .forEach(i -> System.out.println(i + " ning kvadrati: " + Math.pow(i, 2)));
    }

}
