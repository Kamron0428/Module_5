package uz.pdp.stringToInt;


@FunctionalInterface
interface StringToInt {
    int convert(String value);
}

public class Main {

    public static void main(String[] args) {

        StringToInt lambda = Integer::parseInt;

        System.out.println(lambda.convert("123"));


        StringToInt methodReference = Integer::parseInt;

        System.out.println(methodReference.convert("456"));
    }
}

