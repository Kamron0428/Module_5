package uz.pdp.faker;

import lombok.*;
import net.datafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class FakerTest {


    public static void main(String[] args) {

        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = Product
                    .builder()
                    .id(UUID.randomUUID())
                    .name(faker.commerce().productName())
                    .price(faker.number().numberBetween(1000L, 10000L)*1000L)
                    .category(faker.commerce().department())
                    .build();
            products.add(product);
        }
        Map<String, Long> countByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()
                ));

        countByCategory.forEach((category, count) ->
                System.out.println(category + ": " + count + " ta"));

        products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .limit(5)
                .forEach(System.out::println);

    }

}
@Data
@AllArgsConstructor
@Builder
class Product {
    private UUID id;
    private String name;
    private Long price;
    private String category;
}