package uz.pdp.homeTask.bp;

public class PriceFilter implements Filter {
    private double price;

    public PriceFilter(double price) {
        this.price = price;
    }

    @Override
    public boolean test(Product product) {
        return product.getPrice() <= price;
    }
}
