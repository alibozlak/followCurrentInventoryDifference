package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GiroDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.Giro;
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

    @Override
    public double getSumGiroAmountAfterLastGeneralInventoryDate(long lastGeneralInventoryDate) {
        double sumGiroAmountAfterLastGeneralInventoryDate = 0;
        String sqlForGetSumGiroAmountAfterLastGeneralInventoryDate = "SELECT SUM("
                + DbConstants.GIRO_AMOUNT_COLUMN_NAME + ") FROM "
                + DbConstants.GIRO_TABLE_NAME + " WHERE "
                + DbConstants.GIRO_SELECTED_DATE_COLUMN_NAME + " > "
                + lastGeneralInventoryDate;
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)){
            var cursor = db.rawQuery(sqlForGetSumGiroAmountAfterLastGeneralInventoryDate,null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                sumGiroAmountAfterLastGeneralInventoryDate = cursor.getDouble(0);
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sumGiroAmountAfterLastGeneralInventoryDate;
    }

    @Override
    public List<Giro> getAllGirosAfterLastGeneralInventoryDate(long lastGeneralInventoryDate) {
        List<Giro> giros = new ArrayList<>();
        String sqlForGetAllGirosAfterLastGeneralInventoryDate = "SELECT * FROM "
                + DbConstants.GIRO_TABLE_NAME + " WHERE "
                + DbConstants.GIRO_SELECTED_DATE_COLUMN_NAME + " > "
                + lastGeneralInventoryDate;
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)){
            var cursor = db.rawQuery(sqlForGetAllGirosAfterLastGeneralInventoryDate,null);
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    int giroId = cursor.getInt(0);
                    double giroAmount = cursor.getDouble(1);
                    long giroDate = cursor.getLong(2);
                    giros.add(new Giro(giroId,giroDate,giroAmount));
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return giros;
    }
}
