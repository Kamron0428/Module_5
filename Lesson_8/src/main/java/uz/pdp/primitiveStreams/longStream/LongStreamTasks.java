package uz.pdp.primitiveStreams.longStream;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

public class LongStreamTasks {

    public static void main(String[] args) {
/*
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1",1,900,Status.SUCCESS));
        transactions.add(new Transaction("2",2,1200,Status.SUCCESS));
        transactions.add(new Transaction("3",3,3000,Status.SUCCESS));
        transactions.add(new Transaction("4",4,500,Status.FAILED));
        transactions.add(new Transaction("5",5,9000,Status.FAILED));
        transactions.add(new Transaction("6",6,100,Status.FAILED));
        transactions.add(new Transaction("7",7,590,Status.SUCCESS));
        transactionMethod(transactions);
        getFibonacciNums();
        lowNums();
        factorial();
        sumLongNums();
*/
    }

    private static void transactionMethod(List<Transaction> transactions) {
        LongSummaryStatistics stats = transactions.stream()
                .filter(t -> t.getStatus().equals(Status.SUCCESS))
                .mapToLong(Transaction::getAmountIntCents)
                .summaryStatistics();

        long sum = stats.getSum();
        long max = stats.getMax();

        System.out.println("Muvaffaqiyatli tranzaksiyalar soni: " + stats.getCount());
        System.out.println("Jami aylanma (Sum): " + sum + " cents");
        System.out.println("Eng katta tranzaksiya (Max): " + max + " cents");
    }

    private static void sumLongNums() {
        long sum = LongStream.rangeClosed(1000000, 1000050)
                .filter(n -> n % 2 != 0)
                .sum();
        System.out.println(sum);
    }

    private static void lowNums() {
        long[] timestamps = {1710000000L, 1715000000L, 1720000000L, 1725000000L};
        LongStream.of(timestamps).forEach(timestamp -> {
            if (timestamp > 1712000000L) {
                System.out.println(timestamp);
            }
        });
    }

    //0 1 1 2 3 5 8
    private static void getFibonacciNums() {
        long[] state = {0, 1};
        LongStream.iterate(0, prev -> {
                    long current = state[0];
                    long next = state[0] + state[1];
                    state[0] = state[1];
                    state[1] = next;
                    return current;
                })
                .limit(15)
                .forEach(n -> System.out.print(n + " "));
    }

    private static void factorial() {
        long factorial = LongStream.rangeClosed(1, 20)
                .reduce(1, (a, b) -> a * b);
        System.out.println(factorial);
    }

}
