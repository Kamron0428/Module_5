package uz.pdp.homeTask.fi.consumer;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        Consumer<String> printer = message -> System.out.println("Xabar: " + message);
        printer.accept("Salom dunyo!");

    }

}
