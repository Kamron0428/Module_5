package uz.pdp.homeTask.fi.calculate;

public class Main {

    public static void main(String[] args) {


        Calculator kopaytirish = (a, b) -> a * b;
        Calculator bolish = (a, b) -> a / b;
        Calculator ayrish = (a, b) -> a - b;
        Calculator qoshish = (a, b) -> a + b;

        kopaytirish.printResult(10,20);
        bolish.printResult(10,20);
        ayrish.printResult(10,20);
        qoshish.printResult(10,20);



    }

}
