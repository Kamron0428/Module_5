package uz.pdp.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        /*Stream<String> stream = Stream.of("Kamron", "Abbos", "Sanjar","Obit","Dilmurod","Asad")
                .collect(Collectors.joining(", ")).lines();
        stream.forEach(System.out::println);*/

/*
        List<String> words = Arrays.asList(
                "olma", "anor", "behi", "olma", "nok",
                "anor", "olma", "uzum", "shaftoli", "nok",
                "olma", "behi", "anjir", "anor", "olma"
        );

        Map<String, Long> collect = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        collect.forEach((k, v) -> System.out.println(k + ": " + v));
*/


        /*List<Integer> nums =List.of(1, 28, 5, 6, 5, 3, 21, 5, 6, 78, 2, 50);
        Map<Boolean, List<Integer>> collect1 = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        collect1.forEach((k, v) -> System.out.println(k + ": " + v));



        IntSummaryStatistics statistics = nums.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println(statistics.getSum());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());*/
    }

}
