package uz.pdp.homeTask.fi.unaryOperation;

import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {

        UnaryOperator<Integer> square = x -> x * x;
        int res = square.apply(5);
        System.out.println(res);

    }

}
