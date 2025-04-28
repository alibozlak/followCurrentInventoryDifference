package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products;

public class ProductIdPriceTaxInventoryDifferenceDate {
    private int productId;
    private double currentPrice;
    private byte tax;
    private double inventoryDifference;
    private long lastProductInventoryDate;

    public ProductIdPriceTaxInventoryDifferenceDate(){}

    public ProductIdPriceTaxInventoryDifferenceDate(int productId, double currentPrice,
                                                    byte tax, double inventoryDifference,
                                                    long lastProductInventoryDate) {
        this.productId = productId;
        this.currentPrice = currentPrice;
        this.tax = tax;
        this.inventoryDifference = inventoryDifference;
        this.lastProductInventoryDate = lastProductInventoryDate;
    }

    public int getProductId(){
        return this.productId;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public byte getTax() {
        return tax;
    }

    public double getInventoryDifference() {
        return inventoryDifference;
    }

    public long getLastProductInventoryDate() {
        return lastProductInventoryDate;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setTax(byte tax) {
        this.tax = tax;
    }

    public void setInventoryDifference(double inventoryDifference) {
        this.inventoryDifference = inventoryDifference;
    }

    public void setLastProductInventoryDate(long lastProductInventoryDate) {
        this.lastProductInventoryDate = lastProductInventoryDate;
    }
}
