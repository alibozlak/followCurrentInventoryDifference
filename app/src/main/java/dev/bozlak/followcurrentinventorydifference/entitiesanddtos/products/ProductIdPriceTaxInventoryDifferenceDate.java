package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products;

public class ProductIdPriceTaxInventoryDifferenceDate {
    private int productId;
    private double currentPrice;
    private byte tax;
    private double inventoryDifference;
    private String lastProductInventoryDate;

    public ProductIdPriceTaxInventoryDifferenceDate(int productId, double currentPrice,
                                                    byte tax, double inventoryDifference,
                                                    String lastProductInventoryDate) {
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

    public String getLastProductInventoryDate() {
        return lastProductInventoryDate;
    }
}
