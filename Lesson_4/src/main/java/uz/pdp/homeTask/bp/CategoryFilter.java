package uz.pdp.homeTask.bp;



public class CategoryFilter implements Filter {

    private String category;
    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public boolean test(Product product) {
        return category.equals(product.getCategory());
    }
}
