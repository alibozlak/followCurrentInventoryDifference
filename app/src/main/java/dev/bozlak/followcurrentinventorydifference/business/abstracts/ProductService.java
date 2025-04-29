package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductCodeAndName;

public interface ProductService {
    double getSummaryCurrentInventoryDifferencePrice();
    boolean existsEnteredProductCode(String productCode);
    boolean addProduct(Product product);
    List<ProductCodeAndName> productCodeAndNames();
    int getProductIdByProductCode(String productCode);

}
