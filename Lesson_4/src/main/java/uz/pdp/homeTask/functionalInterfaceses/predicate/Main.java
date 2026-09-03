package uz.pdp.homeTask.functionalInterfaceses.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {


        int[] numbers = {3, 8, -5, 12, 20, 7, -14, 25, 4, 11};
        Predicate<Integer> even = (nums)-> nums % 2 == 0;
        Predicate<Integer> odd = (nums)-> nums % 2 == 1;
        Predicate<Integer> positive = (nums)-> nums > 0;
        Predicate<Integer> greaterThan10 = (nums)-> nums > 10;

        /*List<Integer> evenPredicate = getNumbers(numbers, even);
        List<Integer> oddPredicate = getNumbers(numbers, odd);
        List<Integer> positivePredicate = getNumbers(numbers, positive);
        List<Integer> greaterThan10Predicate = getNumbers(numbers, greaterThan10);

        System.out.println(evenPredicate);
        System.out.println(oddPredicate);
        System.out.println(positivePredicate);
        System.out.println(greaterThan10Predicate);*/


        List<Integer> evanAndPositive = getNumbers(numbers, even.and(positive));
        List<Integer> evenOrGreaterThan10 = getNumbers(numbers, even.or(greaterThan10));
        List<Integer> positiveAndGreaterThan10 = getNumbers(numbers, positive.and(greaterThan10));

        System.out.println(evanAndPositive);
        System.out.println(evenOrGreaterThan10);
        System.out.println(positiveAndGreaterThan10);



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
