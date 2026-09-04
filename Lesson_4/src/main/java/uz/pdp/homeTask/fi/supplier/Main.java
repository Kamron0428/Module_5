package uz.pdp.homeTask.fi.supplier;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println(randomSupplier.get());

    }

}
