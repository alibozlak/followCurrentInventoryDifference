package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.ProductDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.Product;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductCodeAndName;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdCodeNameAndPrice;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductIdPriceTaxInventoryDifferenceDate;

public class FirstProductDao implements ProductDao {
    private static FirstProductDao firstProductDao;

    private FirstProductDao() {}

    public static synchronized FirstProductDao getInstance() {
        if (firstProductDao == null) {
            firstProductDao = new FirstProductDao();
        }
        return firstProductDao;
    }

    @Override
    public List<ProductIdPriceTaxInventoryDifferenceDate> getProductIdPriceTaxInventoryDifferenceDates(
            long lastGeneralInventoryDate
    ) {
        return this.getProductIdPriceTaxInventoryDifferenceDates(
                true, lastGeneralInventoryDate
        );
    }

    @Override
    public List<ProductIdPriceTaxInventoryDifferenceDate> getProductIdPriceTaxInventoryDifferenceDates() {
        return this.getProductIdPriceTaxInventoryDifferenceDates(false, 0);
    }

    private List<ProductIdPriceTaxInventoryDifferenceDate> getProductIdPriceTaxInventoryDifferenceDates(
            boolean existLastGeneralInventoryDateParam, long param
    ){
        List<ProductIdPriceTaxInventoryDifferenceDate> productIdPriceTaxInventoryDifferenceAndDates
                = new ArrayList<>();
        String sqlForProductDtos = "SELECT "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_PRICE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_TAX_COLUMN_NAME + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DIFFERENCE_COLUMN_NAME + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME + " FROM "
                + DbConstants.PRODUCTS_TABLE_NAME;
        if(existLastGeneralInventoryDateParam){
            sqlForProductDtos += " WHERE "
                    + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME
                    + " >= " + param;
        }
        sqlForProductDtos += ";";
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
        return  productIdPriceTaxInventoryDifferenceAndDates;
    }

    @Override
    public boolean existsEnteredProductCode(String productCode) {
        var result = false;
        String sqlForExistsEnteredProductCode = "SELECT COUNT(*) FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " WHERE "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + " = " + productCode + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForExistsEnteredProductCode, null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                if(cursor.getInt(0) > 0){
                    result = true;
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
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

    @Override
    public List<ProductCodeAndName> productCodeAndNames() {
        var productCodeAndNames = new ArrayList<ProductCodeAndName>();
        String sqlForProductCodeAndNames = "SELECT "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_NAME_COLUMN_NAME + " FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " ORDER BY "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " DESC;";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForProductCodeAndNames, null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()) {
                    String productCode = cursor.getString(0);
                    String productName = cursor.getString(1);
                    var product = new ProductCodeAndName(productCode, productName);
                    productCodeAndNames.add(product);
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productCodeAndNames;
    }

    @Override
    public int getProductIdByProductCode(String productCode) {
        int productId = 0;
        String sqlForProductIdByProductCode = "SELECT "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " WHERE "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + " = " + productCode + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForProductIdByProductCode, null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                productId = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productId;
    }

    @Override
    public List<ProductIdCodeNameAndPrice> getAllProductIdCodeNameAndPrice() {
        List<ProductIdCodeNameAndPrice> productIdCodeNameAndPrices = new ArrayList<>();
        String sqlForProductIdCodeNameAndPrices = "SELECT "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_NAME_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_PRICE_COLUMN_NAME + " FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " ORDER BY "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForProductIdCodeNameAndPrices, null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()) {
                    int productId = cursor.getInt(0);
                    String productCode = cursor.getString(1);
                    String productName = cursor.getString(2);
                    double price = cursor.getDouble(3);
                    var productIdCodeNameAndPrice = new ProductIdCodeNameAndPrice(
                            productCode, productName, productId, price
                    );
                    productIdCodeNameAndPrices.add(productIdCodeNameAndPrice);
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productIdCodeNameAndPrices;
    }

    @Override
    public Product getProductByProductId(int productId) {
        Product product = new Product();
        String sqlForGetProductByProductId = "SELECT * FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " WHERE "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " = " + productId + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForGetProductByProductId, null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                product.setProductId(productId);
                product.setProductCode(cursor.getString(7));
                product.setCurrentPrice(cursor.getDouble(1));
                product.setTax((byte) cursor.getShort(2));
                product.setInventoryDifference(cursor.getDouble(3));
                product.setLastProductInventoryDate(cursor.getLong(4));
                product.setProductName(cursor.getString(6));
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean result = false;
        String sqlForUpdateProduct = "UPDATE "
                + DbConstants.PRODUCTS_TABLE_NAME + " SET "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + " = " + product.getProductCode() + ", "
                + DbConstants.PRODUCT_PRICE_COLUMN_NAME + " = " + product.getCurrentPrice() + ", "
                + DbConstants.PRODUCT_TAX_COLUMN_NAME + " = " + product.getTax() + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DIFFERENCE_COLUMN_NAME + " = "
                + product.getInventoryDifference() + ", "
                + DbConstants.LAST_PRODUCT_INVENTORY_DATE_COLUMN_NAME + " = "
                + product.getLastProductInventoryDate() + ", "
                + DbConstants.PRODUCT_NAME_COLUMN_NAME + " = '" + product.getProductName() + "' WHERE "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " = " + product.getProductId() + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            db.execSQL(sqlForUpdateProduct);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public ProductCodeAndName getProductCodeAndNameByProductId(int productId) {
        ProductCodeAndName productCodeAndName = new ProductCodeAndName();
        String sqlForGetProductCodeAndNameByProductId = "SELECT "
                + DbConstants.PRODUCT_CODE_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_NAME_COLUMN_NAME + " FROM "
                + DbConstants.PRODUCTS_TABLE_NAME + " WHERE "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " = " + productId + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForGetProductCodeAndNameByProductId, null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                productCodeAndName.setProductCode(cursor.getString(0));
                productCodeAndName.setProductName(cursor.getString(1));
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productCodeAndName;
    }


}
