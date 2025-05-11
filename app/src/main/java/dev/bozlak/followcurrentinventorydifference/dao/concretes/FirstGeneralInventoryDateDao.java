package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;

public class FirstGeneralInventoryDateDao implements GeneralInventoryDateDao {
    private static FirstGeneralInventoryDateDao firstGeneralInventoryDateDao;

    private FirstGeneralInventoryDateDao() {}

    public static synchronized FirstGeneralInventoryDateDao getInstance() {
        if (firstGeneralInventoryDateDao == null) {
            firstGeneralInventoryDateDao = new FirstGeneralInventoryDateDao();
        }
        return firstGeneralInventoryDateDao;
    }

    @Override
    public long getLastGeneralInventoryDate() {
        long lastGeneralInventoryDate = 0;
        String sqlForGetLastGeneralInventoryDate = "SELECT "
                + DbConstants.GENERAL_INVENTORY_DATE_AND_TIME_COLUMN_NAME + " FROM "
                + DbConstants.GENERAL_INVENTORY_DATES_TABLE_NAME + " ORDER BY "
                + DbConstants.GENERAL_INVENTORY_ID_COLUMN_NAME + " DESC LIMIT 1";

        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)){
            var cursor = db.rawQuery(sqlForGetLastGeneralInventoryDate,null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                lastGeneralInventoryDate = cursor.getLong(0);
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lastGeneralInventoryDate;
    }

    @Override
    public boolean addGeneralInventoryDate(long date) {
        boolean result = false;
        String sqlForAddGeneralInventoryDate = "INSERT INTO "
                + DbConstants.GENERAL_INVENTORY_DATES_TABLE_NAME + " ("
                + DbConstants.GENERAL_INVENTORY_DATE_AND_TIME_COLUMN_NAME + ") VALUES ("
                + date + ");";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)){
            db.execSQL(sqlForAddGeneralInventoryDate);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
