package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products;

public class ProductCodeAndName {
    private String productCode;
    private String productName;

    public ProductCodeAndName() {
    }

    public ProductCodeAndName(String productCode, String productName) {
        this.productCode = productCode;
        this.productName = productName;
    }

    @Override
    public String toString() {
        return getProductCode() + " : " + getProductName();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
