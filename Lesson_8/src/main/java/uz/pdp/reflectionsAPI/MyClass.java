package uz.pdp.reflectionsAPI;

import java.util.Date;

public class MyClass {
    private final String message;
    public MyClass(String message) {
        this.message = message;
    }

    private void hi() {
        System.out.println("Hello World!");
        System.out.println(message);
    }

    private Date showMessage() {
        System.out.println(message);
        return new Date();
    }

    public String getMessage() {
        return message;
    }

    public int sum(int a, int b) {
        return a + b;
    }

}
