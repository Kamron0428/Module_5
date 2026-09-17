package uz.pdp.calculete;

public class Main {
    public static void main(String[] args) {
        Calculate multiply = (a, b)-> a*b;
        multiply.print(20,5);

        Calculate addition = Integer::sum;
        addition.print(20,5);

        Calculate subtraction = (a, b)-> a-b;
        subtraction.print(20,5);

        Calculate divide = (a, b)-> a/b;
        divide.print(20,5);
    }
}
