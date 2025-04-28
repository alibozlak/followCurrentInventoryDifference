package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;

public interface ProductService {
    double getSummaryCurrentInventoryDifferencePrice();
    boolean addProduct(Product product);
}
