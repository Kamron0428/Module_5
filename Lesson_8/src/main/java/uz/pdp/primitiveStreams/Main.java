package uz.pdp.primitiveStreams;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        intStream.forEach(System.out::println);

        System.out.println();

        IntStream.range(1, 10).forEach(System.out::print);

        System.out.println();
        IntStream.rangeClosed(1, 10).forEach(System.out::print);

        System.out.println();
        System.out.println();

        int[] numbers = {10, 20, 30};
        IntStream arrayStream = Arrays.stream(numbers);
        arrayStream.forEach(System.out::println);

        System.out.println();

        IntStream.iterate(1, i -> i + 2).limit(5).forEach(System.out::println);

        System.out.println();

        int total = IntStream.rangeClosed(1, 10).sum();
        System.out.println(total);

        System.out.println();

        OptionalDouble avg = IntStream.of(2, 4, 6, 8).average();
        avg.ifPresent(a -> System.out.println("O'rtacha: " + a));

        System.out.println();

        OptionalInt min = IntStream.of(12, 5, 45, 2).min();
        OptionalInt max = IntStream.of(12, 5, 45, 2).max();
        System.out.println(min);
        System.out.println(max);

        System.out.println();

        long count = IntStream.range(0, 99).count();
        System.out.println(count);

        System.out.println();

        IntStream scores = IntStream.of(85, 92, 78, 96, 64);
        IntSummaryStatistics stats = scores.summaryStatistics();

        System.out.println("Soni: " + stats.getCount());
        System.out.println("Yig'indi: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("O'rtacha: " + stats.getAverage());

        System.out.println();

        int factorial = IntStream.rangeClosed(1, 5)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Factorial: " + factorial);

        System.out.println();


    }

}
