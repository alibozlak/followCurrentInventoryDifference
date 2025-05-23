package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.EventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventIdProductIdEventAmountEventDate;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;

public class FirstEventAffectingInventoryDao implements EventAffectingInventoryDao {
    private static FirstEventAffectingInventoryDao firstEventAffectingInventoryDao;

    private FirstEventAffectingInventoryDao() {}

    public static synchronized FirstEventAffectingInventoryDao getInstance() {
        if (firstEventAffectingInventoryDao == null) {
            firstEventAffectingInventoryDao = new FirstEventAffectingInventoryDao();
        }
        return firstEventAffectingInventoryDao;
    }

    @Override
    public double getSumOfEventAmountGivenProductIdAndLastProductInventory
            (int productId, long lastProductInventoryDate) {
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

    @Override
    public boolean addEventAffectingInventory(EventProductIdEventAmountAndDate eventProductIdEventAmountAndDate) {
        boolean isAdded = false;
        String sqlForAddEvent = "INSERT INTO "
                + DbConstants.EVENTS_AFFECTING_INVENTORY_TABLE_NAME + " ("
                + DbConstants.PRODUCT_ID_COLUMN_NAME + ", "
                + DbConstants.AMOUNT_COLUMN_NAME + ", "
                + DbConstants.EVENT_DATE_AND_TIME_COLUMN_NAME + ") VALUES ("
                + eventProductIdEventAmountAndDate.getProductId() + ", "
                + eventProductIdEventAmountAndDate.getEventAmount() + ", "
                + eventProductIdEventAmountAndDate.getEventDateAndTime() + ");";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            db.execSQL(sqlForAddEvent);
            isAdded = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isAdded;
    }

    @Override
    public int getLastEventId() {
        int lastEventId = 0;
        String sqlForLastEventId = "SELECT MAX("
                + DbConstants.EVENT_ID_COLUMN_NAME + ") FROM "
                + DbConstants.EVENTS_AFFECTING_INVENTORY_TABLE_NAME + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForLastEventId, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                lastEventId = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lastEventId;
    }

    @Override
    public List<EventIdProductIdEventAmountEventDate> getAllPositiveEventDtosGivenLastGeneralInventoryDate(
            long lastGeneralInventoryDate
    ) {
        return this.getAllEventDtosGivenLastGeneralInventoryDate(lastGeneralInventoryDate, true);
    }

    @Override
    public List<EventIdProductIdEventAmountEventDate> getAllNegativeEventDtosGivenLastGeneralInventoryDate(
            long lastGeneralInventoryDate
    ) {
        return this.getAllEventDtosGivenLastGeneralInventoryDate(lastGeneralInventoryDate, false);
    }

    @Override
    public boolean deleteEventGivenEventId(int eventId) {
        boolean result = false;
        String sqlForDeleteEvent = "DELETE FROM "
                + DbConstants.EVENTS_AFFECTING_INVENTORY_TABLE_NAME + " WHERE "
                + DbConstants.EVENT_ID_COLUMN_NAME + " = " + eventId + ";";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            db.execSQL(sqlForDeleteEvent);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private List<EventIdProductIdEventAmountEventDate> getAllEventDtosGivenLastGeneralInventoryDate(
            long lastGeneralInventoryDate, boolean isPositiveEvent
    ){
        List<EventIdProductIdEventAmountEventDate> eventDtos = new ArrayList<>();
        String sqlForEventDtos = "SELECT "
                + DbConstants.EVENT_ID_COLUMN_NAME + ", "
                + DbConstants.PRODUCT_ID_COLUMN_NAME + ", "
                + DbConstants.AMOUNT_COLUMN_NAME + ", "
                + DbConstants.EVENT_DATE_AND_TIME_COLUMN_NAME + " FROM "
                + DbConstants.EVENTS_AFFECTING_INVENTORY_TABLE_NAME + " WHERE "
                + DbConstants.EVENT_DATE_AND_TIME_COLUMN_NAME + " >= " + lastGeneralInventoryDate + " AND "
                + DbConstants.AMOUNT_COLUMN_NAME;
        if(isPositiveEvent){
            sqlForEventDtos += " > 0;";
        } else {
            sqlForEventDtos += " < 0;";
        }
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            var cursor = db.rawQuery(sqlForEventDtos, null);
            if(cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    var eventDto = new EventIdProductIdEventAmountEventDate();
                    eventDto.setEventId(cursor.getInt(0));
                    eventDto.setProductId(cursor.getInt(1));
                    eventDto.setEventAmount(cursor.getDouble(2));
                    eventDto.setEventDate(cursor.getLong(3));
                    eventDtos.add(eventDto);
                }
            }
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eventDtos;
    }
}
