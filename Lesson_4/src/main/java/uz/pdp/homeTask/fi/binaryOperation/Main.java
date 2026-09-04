package uz.pdp.homeTask.fi.binaryOperation;

import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {

        BinaryOperator<Integer> sum = (a, b) -> a + b;
        int total = sum.apply(10, 20);
        System.out.println(total);

    }

}
