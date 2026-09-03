package uz.pdp.homeTask.bp;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Olma","Meva",20000.00));
        productList.add(new Product("Uzum","Meva",19000.00));
        productList.add(new Product("Iphone 17 pro","Telefon",14_000_000.00));
        productList.add(new Product("MacBook M2pro","Kompyuter",15_000_000.00));
        productList.add(new Product("Samsung TV","Televizor",5_000_000.00));

        /*CategoryFilter categoryFilter = new CategoryFilter("Meva");
        List<Product> products = getProducts(productList, categoryFilter);
        System.out.println(products);

        System.out.println();

        PriceFilter priceFilter = new PriceFilter(10_000_000);
        List<Product> products1 = getProducts(productList, priceFilter);
        System.out.println(products1);*/


        Filter filter = (product) -> product.getCategory().equals("Meva");
        List<Product> products2 = getProducts(productList, filter);
        System.out.println(products2);

        System.out.println();

        Filter filter1 = (product) -> product.getPrice() < 10_000_000.00;
        List<Product> products3 = getProducts(productList, filter1);
        System.out.println(products3);
    }

    private static List<Product> getProducts(List<Product> products, Filter filter) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (filter.test(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

}
