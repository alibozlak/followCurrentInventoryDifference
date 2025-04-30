package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products;

import dev.bozlak.followcurrentinventorydifference.utilities.SalesUnitType;

public class Product extends ProductIdPriceTaxInventoryDifferenceDate{
    private String productCode;
    private String productShortCode;
    private String productName;
    private SalesUnitType salesUnitType;
    private String productDetail;

    public Product(){}

    public Product(int productId, double currentPrice, byte tax, double inventoryDifference,
                   long lastProductInventoryDate) {
        super(productId, currentPrice, tax, inventoryDifference, lastProductInventoryDate);
    }

    public Product(int productId, double currentPrice, byte tax, double inventoryDifference,
                   long lastProductInventoryDate, String productCode, String productShortCode,
                   String productName, SalesUnitType salesUnitType, String productDetail){
        this(productId, currentPrice, tax, inventoryDifference, lastProductInventoryDate);
        this.productCode = productCode;
        this.productShortCode = productShortCode;
        this.productName = productName;
        this.salesUnitType = salesUnitType;
        this.productDetail = productDetail;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductShortCode() {
        return productShortCode;
    }

    public void setProductShortCode(String productShortCode) {
        this.productShortCode = productShortCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SalesUnitType getSalesUnitType() {
        return salesUnitType;
    }

    public void setSalesUnitType(SalesUnitType salesUnitType) {
        this.salesUnitType = salesUnitType;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }
}
