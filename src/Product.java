import java.util.Objects;

/**
 * Product class
 */
public class Product {
    private String bcode;
    private String title;
    private Integer quantity;
    private double price;

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * Constructor method to initialize a product
     *
     * @param bcode    Product's bar code
     * @param title    Product's title
     * @param quantity Product's quantity
     * @param price    Product's price
     */
    public Product(String bcode, String title, Integer quantity, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Convert this product to string for printing
     *
     * @return the string representation of this product
     */
    @Override
    public String toString() {
        return String.format("%-15s | %-40s | %10d | %,20.2f", bcode, title, quantity, price);
    }

    /**
     * Check if this product is equals to another product
     *
     * @return true if this product is equals to another product
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Product product))
            return false;

        return Objects.equals(bcode, product.bcode) && Objects.equals(title, product.title)
                && Objects.equals(quantity, product.quantity) && Objects.equals(price, product.price);
    }
}