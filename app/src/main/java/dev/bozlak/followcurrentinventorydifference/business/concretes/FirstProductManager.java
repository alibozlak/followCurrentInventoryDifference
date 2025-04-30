package dev.bozlak.followcurrentinventorydifference.business.concretes;

import java.util.Collections;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.EventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.ProductDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductCodeAndName;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdPriceTaxInventoryDifferenceDate;

public class FirstProductManager implements ProductService {
    private static FirstProductManager firstProductManager;
    private final GeneralInventoryDateDao generalInventoryDateDao;
    private final ProductDao productDao;
    private final EventAffectingInventoryDao eventAffectingInventoryDao;

    private FirstProductManager(GeneralInventoryDateDao generalInventoryDateDao,
                                ProductDao productDao,
                                EventAffectingInventoryDao eventAffectingInventoryDao) {
        this.generalInventoryDateDao = generalInventoryDateDao;
        this.productDao = productDao;
        this.eventAffectingInventoryDao = eventAffectingInventoryDao;
    }

    public static synchronized FirstProductManager getInstance(GeneralInventoryDateDao generalInventoryDateDao,
                                                  ProductDao productDao,
                                                  EventAffectingInventoryDao eventAffectingInventoryDao)
    {
        if (firstProductManager == null) {
            firstProductManager =
                    new FirstProductManager(generalInventoryDateDao, productDao, eventAffectingInventoryDao);
        }
        return firstProductManager;
    }

    @Override
    public double getSummaryCurrentInventoryDifferencePrice() {
        long lastGeneralInventoryDate = this.generalInventoryDateDao.getLastGeneralInventoryDate();
        List<ProductIdPriceTaxInventoryDifferenceDate> productIdPriceTaxInventoryDifferenceDateList
                = this.productDao.getProductIdPriceTaxInventoryDifferenceDates(lastGeneralInventoryDate);
        double summaryCurrentInventoryDifferencePrice = 0;
        for (var productDto : productIdPriceTaxInventoryDifferenceDateList){
            int productId = productDto.getProductId();
            double productCurrentPrice = productDto.getCurrentPrice();
            byte tax = productDto.getTax();
            double productCurrentInventoryDifference = productDto.getInventoryDifference();
            long lastProductInventoryDate = productDto.getLastProductInventoryDate();

            double sumOfAmountFromLastProductInventoryDate
                    = this.eventAffectingInventoryDao.getSumOfEventAmountGivenProductIdAndLastProductInventory(
                            productId, lastProductInventoryDate
                    );
            double totalProductCurrentInventoryDifference
                    = productCurrentInventoryDifference + sumOfAmountFromLastProductInventoryDate;
            double productInventoryPriceUnedited = productCurrentPrice * (1 - tax/100.0);
            double productInventoryPrice = Math.round(productInventoryPriceUnedited * 100.0) / 100.0;
            double productInventoryDifferencePriceUnedited
                    = totalProductCurrentInventoryDifference * productInventoryPrice;
            double productInventoryDifferencePrice
                    = Math.round(productInventoryDifferencePriceUnedited * 100.0) / 100.0;
            summaryCurrentInventoryDifferencePrice += productInventoryDifferencePrice;
        }
        return summaryCurrentInventoryDifferencePrice;
    }

    @Override
    public boolean existsEnteredProductCode(String productCode) {
        return this.productDao.existsEnteredProductCode(productCode);
    }

    @Override
    public boolean addProduct(Product product) {
        return this.productDao.addProduct(product);
    }

    @Override
    public List<ProductCodeAndName> productCodeAndNames() {
        return this.productDao.productCodeAndNames();
    }

    @Override
    public int getProductIdByProductCode(String productCode) {
        return this.productDao.getProductIdByProductCode(productCode);
    }
}
