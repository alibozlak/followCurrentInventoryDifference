package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GiroDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;

public class FirstGiroDao implements GiroDao {
    private static FirstGiroDao firstGiroDao;

    private FirstGiroDao() {}

    public static synchronized FirstGiroDao getInstance() {
        if (firstGiroDao == null) {
            firstGiroDao = new FirstGiroDao();
        }
        return firstGiroDao;
    }

    @Override
    public long getLastGiroDate() {
        long lastGiroDate = 0;
        String sqlForGetLastGiroDate = "SELECT "
                + DbConstants.GIRO_SELECTED_DATE_COLUMN_NAME + " FROM "
                + DbConstants.GIRO_TABLE_NAME + " ORDER BY "
                + DbConstants.GIRO_SELECTED_DATE_COLUMN_NAME + " DESC LIMIT 1";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)){
            var cursor = db.rawQuery(sqlForGetLastGiroDate,null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                lastGiroDate = cursor.getLong(0);
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lastGiroDate;
    }

    @Override
    public boolean addGiro(GiroDateAndAmount giroDateAndAmount) {
        boolean isAdded = false;
        String sqlForAddGiro = "INSERT INTO " + DbConstants.GIRO_TABLE_NAME + " ("
                + DbConstants.GIRO_SELECTED_DATE_COLUMN_NAME + ","
                + DbConstants.GIRO_AMOUNT_COLUMN_NAME + ") VALUES ("
                + giroDateAndAmount.getGiroDate() + ","
                + giroDateAndAmount.getGiroAmount() + ");";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)){
            db.execSQL(sqlForAddGiro);
            isAdded = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isAdded;
    }
}
