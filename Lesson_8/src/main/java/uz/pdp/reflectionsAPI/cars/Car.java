package uz.pdp.reflectionsAPI.cars;

import lombok.ToString;

@ToString
public class Car {
    private String brand;
    private String model;
    private int year;
    private double price;

    private Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    private double priceDiscount(double discount) {
        return this.price-(this.price * discount);
    }

    private String getBrand() {
        return brand;
    }

    private String getModel() {
        return model;
    }

    private int getYear() {
        return year;
    }

    private double getPrice() {
        return price;
    }
}
