package uz.pdp.primitiveStreams.doubleStream;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private String name;
    private double price;
    private double weightKg;

}
