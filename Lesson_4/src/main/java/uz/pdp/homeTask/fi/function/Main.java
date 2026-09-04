package uz.pdp.homeTask.fi.function;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Function<String, Integer> stringLength = str -> str.length();
        int length = stringLength.apply("Kamron");
        System.out.println(length);

    }

}
