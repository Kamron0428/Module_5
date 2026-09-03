package uz.pdp.test;

import java.util.*;
import java.util.function.*;

public class FunctionalInterfaceTest {


}
class PredicateTest {
    public static void main(String[] args) {
        int[] array = {5, 4, 8, -3, -4, 10};
        Predicate<Integer> odd = (number) -> number % 2 != 0;
        System.out.println(filter(array, odd));

        Predicate<Integer> even = (number) -> number % 2 == 0;
        System.out.println(filter(array, even));

        Predicate<Integer> negative = (number) -> number < 0;
        System.out.println(filter(array, negative));

        Predicate<Integer> negativeAndEven = negative.and(even);
        System.out.println(filter(array, negativeAndEven));

        Predicate<Integer> negativeOrOdd = negative.or(odd);
        System.out.println(filter(array, negativeOrOdd));
    }

    public static List<Integer> filter(int[] array, Predicate<Integer> predicate) {
        List<Integer> numbers = new ArrayList<>();
        for ( int i : array ) {
            if ( predicate.test(i) )
                numbers.add(i);
        }
        return numbers;
    }
}

class ConsumerTest {
    public static void main(String[] args) {
        List<Employee> empList = List.of(
                new Employee("Javohir Elmurodov", "UZB", "SOFTWARE ENGINEER", 28),
                new Employee("John Doe", "US", "MANAGER ", 108),
                new Employee("Akmal Turdiyev", "UZB", "SALES_MANAGER", 29),
                new Employee("John Leg", "GER", "MANAGER", 25),
                new Employee("Akbar Akbarov", "US", "SOFTWARE ENGINEER", 17)
        );

        Consumer<Employee> printOnConsole = ( System.out :: println );
        Consumer<Employee> storeInDB = ( e -> System.out.println(e.toString() + " saving database") );
        Consumer<Employee> priConsumerThenStoreInDB = printOnConsole.andThen(storeInDB);
        forEach(empList, priConsumerThenStoreInDB);
    }

    static <T> void forEach(List<T> list, Consumer<T> consumer) {
        int nullCount = 0;
        for ( T t : list ) {
            if ( t != null ) {
                consumer.accept(t);
            } else {
                nullCount++;
            }
        }
        System.out.printf("%d null entries count  in the list.\n", nullCount);
    }
}

class FunctionTest {
    public static void main(String[] args) {
        Function<String, Integer> charsCount = String :: length;
        Integer helloPdp = charsCount.apply("Hello PDP");
        System.out.println(helloPdp);
    }
}

class SupplierTest {
    public static void main(String[] args) {
        Supplier<Throwable> supplier = () -> new RuntimeException("Exception occur");
    }
}
