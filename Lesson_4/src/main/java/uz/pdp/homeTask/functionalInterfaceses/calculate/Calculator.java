package uz.pdp.homeTask.functionalInterfaceses.calculate;


@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
    default void printResult(int a, int b) {
        System.out.println(calculate(a, b));
    }
}
