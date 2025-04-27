package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.EventAffectingInventoryDao;

public class FirstEventAffectingInventoryDao implements EventAffectingInventoryDao {
    private static FirstEventAffectingInventoryDao firstEventAffectingInventoryDao;

    private FirstEventAffectingInventoryDao() {}

    public static FirstEventAffectingInventoryDao getInstance() {
        if (firstEventAffectingInventoryDao == null) {
            firstEventAffectingInventoryDao = new FirstEventAffectingInventoryDao();
        }
        return firstEventAffectingInventoryDao;
    }

    @Override
    public double getSumOfEventAmountGivenProductIdAndLastProductInventory
            (int productId, String lastProductInventoryDate) {
        double sumOfEventAmount = 0;
        String sqlForSumOfEventAmount = "SELECT SUM("
                + DbConstants.AMOUNT_COLUMN_NAME + ") FROM "
                + DbConstants.EVENTS_AFFECTING_INVENTORY_TABLE_NAME + " WHERE "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + " = " + productId + " AND "
                + DbConstants.EVENT_DATE_AND_TIME_COLUMN_NAME + " >= " + lastProductInventoryDate + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForSumOfEventAmount, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                sumOfEventAmount = cursor.getDouble(0);
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sumOfEventAmount;
    }
}
