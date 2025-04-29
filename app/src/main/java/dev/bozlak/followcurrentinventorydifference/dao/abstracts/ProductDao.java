package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductCodeAndName;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdPriceTaxInventoryDifferenceDate;

public interface ProductDao {
    List<ProductIdPriceTaxInventoryDifferenceDate> getProductIdPriceTaxInventoryDifferenceDates(
            long lastGeneralInventoryDate
    );
    boolean existsEnteredProductCode(String productCode);
    boolean addProduct(Product product);
    List<ProductCodeAndName> productCodeAndNames();
    int getProductIdByProductCode(String productCode);
}
