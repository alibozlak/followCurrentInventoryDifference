package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products;

public class ProductIdCodeNameAndPrice extends ProductCodeAndName {
    private int productId;
    private double price;

    public ProductIdCodeNameAndPrice() {
    }

    public ProductIdCodeNameAndPrice(String productCode, String productName) {
        super(productCode, productName);
    }

    public ProductIdCodeNameAndPrice(String productCode, String productName, int productId, double price) {
        super(productCode, productName);
        this.productId = productId;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
