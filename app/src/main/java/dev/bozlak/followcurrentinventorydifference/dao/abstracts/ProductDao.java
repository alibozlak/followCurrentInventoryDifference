package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdPriceTaxInventoryDifferenceDate;

public interface ProductDao {
    List<ProductIdPriceTaxInventoryDifferenceDate> getProductIdPriceTaxInventoryDifferenceDates(
            long lastGeneralInventoryDate
    );

    boolean addProduct(Product product);
}
