package uz.pdp.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> predicate = (n) -> n % 2 == 0;
        List<Integer> numbers = getNumbers(arr, predicate);
        System.out.println(numbers);
    }

    private static List<Integer> getNumbers(int[] numbers, Predicate<Integer> predicate) {
        List<Integer> list = new ArrayList<>();
        for (int number : numbers) {
            if (predicate.test(number)) {
                list.add(number);
            }
        }
        return list;
    }

}
