package uz.pdp.primitiveStreams.doubleStream;

import java.util.*;
import java.util.stream.DoubleStream;

public class DoubleStreamTasks {

    public static void main(String[] args) {
/*
        cToF();
        sumGetAverage();
        differenceBetweenTwoNums();
        randomNumsAverage();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Olma", 16_000.0, 2.0));
        products.add(new Product("Un", 24_000.0, 1.0));
        products.add(new Product("Kartoshka", 5_000.0, 10.0));
        products.add(new Product("Shakar", 22_000.0, 5.0));
        products.add(new Product("Piyoz", 3_000.0, 8.0));
        products.add(new Product("Shaftoli", 14_000.0, 3.0));
        products.add(new Product("Sabzi", 7_000.0, 0.9));

        sumWeightKg(products);
*/
    }

    private static void sumWeightKg(List<Product> products) {
        DoubleSummaryStatistics dss = products.stream()
                .filter(p -> p.getWeightKg() > 2)
                .mapToDouble(Product::getWeightKg)
                .summaryStatistics();
        System.out.println("Tovarlar og'irligi: " + dss.getSum());
        System.out.println("Eng og'ir tovar vazni: " + dss.getMax());
    }

    private static void sumGetAverage() {
        double average = DoubleStream.of(1.5, 2.5, 3.5, 4.5)
                .summaryStatistics()
                .getAverage();
        System.out.println(average + (average * 0.1));
    }

    private static void differenceBetweenTwoNums() {
        double[] rates = {12600.50, 12650.00, 12580.20, 12710.00};

        double min = DoubleStream.of(rates)
                .min().getAsDouble();
        double max = DoubleStream.of(rates)
                .max().getAsDouble();

        System.out.println(Arrays.toString(rates));
        System.out.println("Max va min kurs farqi: " + (max - min));
    }

    private static void randomNumsAverage() {
        DoubleStream.generate(Math::random)
                .limit(5)
                .average()
                .ifPresent(s -> System.out.println("O'rtacha qiymati -> " + s * 100));
    }

    private static void cToF() {
        double[] temperatures = {18.5, 21.0, 19.4, 25.6, 22.8, 16.9};

        DoubleStream.of(temperatures)
                .forEach(t -> System.out.println("Farangeyt: " + (t * 1.8 + 32)));
    }

}
