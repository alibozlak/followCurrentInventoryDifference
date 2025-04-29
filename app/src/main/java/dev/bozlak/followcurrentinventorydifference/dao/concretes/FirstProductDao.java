package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.ProductDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdPriceTaxInventoryDifferenceDate;

public class FirstProductDao implements ProductDao {
    private static FirstProductDao firstProductDao;

    private FirstProductDao() {}

    public static FirstProductDao getInstance() {
        if (firstProductDao == null) {
            firstProductDao = new FirstProductDao();
        }
        return firstProductDao;
    }

    @Override
    public List<ProductIdPriceTaxInventoryDifferenceDate> getProductIdPriceTaxInventoryDifferenceDates(
            long lastGeneralInventoryDate
    ) {
        List<ProductIdPriceTaxInventoryDifferenceDate> productIdPriceTaxInventoryDifferenceAndDates
                = new ArrayList<>();
        String sqlForProductDtos = "SELECT "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_PRICE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_TAX_COLUMN_NAME + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DIFFERENCE_COLUMN_NAME + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME + " FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " WHERE "
                + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME
                + " >= " + lastGeneralInventoryDate + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForProductDtos, null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()) {
                    int productId = cursor.getInt(0);
                    double currentPrice = cursor.getDouble(1);
                    byte tax = (byte) cursor.getShort(2);
                    double inventoryDifference = cursor.getDouble(3);
                    long lastProductInventoryDate = cursor.getLong(4);
                    var productDto = new ProductIdPriceTaxInventoryDifferenceDate(
                            productId, currentPrice, tax, inventoryDifference, lastProductInventoryDate
                    );
                    productIdPriceTaxInventoryDifferenceAndDates.add(productDto);
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productIdPriceTaxInventoryDifferenceAndDates;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean result = false;
        String sqlForAddProduct = "INSERT INTO "
                + DbConstants.PRODUCTS_TABLE_NAME + " ("
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_PRICE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_TAX_COLUMN_NAME + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DIFFERENCE_COLUMN_NAME + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_NAME_COLUMN_NAME + ") VALUES ("
                + product.getProductCode() + ", "
                + product.getCurrentPrice() + ", "
                + product.getTax() + ", "
                + product.getInventoryDifference() + ", "
                + product.getLastProductInventoryDate() + ", '"
                + product.getProductName() + "');";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            System.out.println(db.isReadOnly());
            db.execSQL(sqlForAddProduct);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
