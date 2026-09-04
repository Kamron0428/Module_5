package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private UUID id;
    private String model;
    private String brand;
    private int year;
    private double price;
    private boolean isAvailable;
    private UUID rentID;

    @Override
    public String toString() {
        return "%s, %s, %s, %d, %.2f, %b, %s".formatted(id, model, brand, year, price, isAvailable, rentID);
    }
}
