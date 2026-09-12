package uz.pdp.reflectionsAPI.animals;

import lombok.ToString;

@ToString
public class Animal {
    private final String type;


    private Animal(String type) {
        this.type = type;
    }


    private void sound() {
        System.out.println("I am Sound");
    }


    private String getType() {
        return type;
    }
}
