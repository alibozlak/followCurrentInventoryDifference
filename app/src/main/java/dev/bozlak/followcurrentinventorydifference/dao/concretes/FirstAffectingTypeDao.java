package dev.bozlak.followcurrentinventorydifference.dao.concretes;

import android.database.sqlite.SQLiteDatabase;

import dev.bozlak.followcurrentinventorydifference.dao.DbConstants;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.AffectingTypeDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.affectingtypes.EventIdAndAffectingType;

public class FirstAffectingTypeDao implements AffectingTypeDao {
    private static FirstAffectingTypeDao firstAffectingTypeDao;

    private FirstAffectingTypeDao() {}

    public static synchronized FirstAffectingTypeDao getInstance() {
        if (firstAffectingTypeDao == null) {
            firstAffectingTypeDao = new FirstAffectingTypeDao();
        }
        return firstAffectingTypeDao;
    }

    @Override
    public boolean addAffectingTypeForEvent(EventIdAndAffectingType eventIdAndAffectingType) {
        boolean result = false;
        String sqlForAddAffectingType = "INSERT INTO "
                + DbConstants.AFFECTING_TYPES_TABLE_NAME + " ("
                + DbConstants.EVENT_ID_COLUMN_NAME + ", "
                + DbConstants.AFFECTING_TYPE_COLUMN_NAME + ") VALUES ("
                + eventIdAndAffectingType.getEventId() + ", '"
                + eventIdAndAffectingType.getAffectingType() + "');";
        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DbConstants.DB_PATH, null)) {
            db.execSQL(sqlForAddAffectingType);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
