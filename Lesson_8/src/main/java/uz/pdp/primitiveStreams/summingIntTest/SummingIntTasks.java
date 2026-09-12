package uz.pdp.primitiveStreams.summingIntTest;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SummingIntTasks {

    public static void main(String[] args) {

        /*
        sumChar();
        unliWordsLength();
        higherThen10();
        wordsToInt();

        List<Book> books = new ArrayList<>();
        books.add(new Book("The Lathe of Heaven","Javier Koelpin",331));
        books.add(new Book("As I Lay Dying","Harris Koss V",369));
        books.add(new Book("A Summer Bird-Cage","Ms. Eleni Jacobi",342));
        books.add(new Book("The Other Side of Silence","Bernie Ondricka",196));
        books.add(new Book("Clouds of Witness","Irving Nikolaus",270));
        books.add(new Book("Tiger! Tiger!","Brendon Aufderhar DDS",132));
        books.add(new Book("Angel","Brendon Aufderhar DDS",88));

        countPagesBook(books);
        */
    }

    private static void countPagesBook(List<Book> books) {
        Map<String, Integer> collect = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingInt(Book::getPages)));
        collect.forEach((k,v) -> System.out.println(k + ": " + v+" pages written"));
    }

    private static void wordsToInt() {
        String[] arr = {"10", "25", "40"};
        Integer collect = Arrays.stream(arr).map(Integer::valueOf).collect(Collectors.summingInt(Integer::intValue));
        System.out.println(collect);
    }

    private static void unliWordsLength() {
        List<String> words = List.of("Java", "Stream", "API", "PDP", "Backend");
        List<String> vowels = List.of("A", "E", "I", "O", "U");

        int totalLength = words.stream()
                .filter(w -> vowels.stream().anyMatch(v -> w.toUpperCase().startsWith(v)))
                .collect(Collectors.summingInt(String::length));

        System.out.println("Natija: " + totalLength);
    }

    private static void higherThen10() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(3);
        nums.add(112);
        nums.add(22);
        nums.add(11);
        nums.add(10);
        nums.add(8);

        Integer collect1 = nums.stream()
                .filter(num -> num >= 10)
                .collect(Collectors.summingInt(Integer::intValue));

        System.out.println(collect1);
    }

    private static void sumChar() {
        List<String> words = List.of("Java", "Stream", "API", "PDP", "Backend");
        Integer collect = words.stream().collect(Collectors.summingInt(String::length));
        System.out.println("Barcha so'zlarning uzunligini yig'indisi: " + collect);
    }

}
