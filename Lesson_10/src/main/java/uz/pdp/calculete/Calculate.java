package uz.pdp.calculete;


@FunctionalInterface
public interface Calculate {
    int calculate(int a, int b);

    default void print(int a, int b) {
        System.out.println(calculate(a, b));
    }
}

