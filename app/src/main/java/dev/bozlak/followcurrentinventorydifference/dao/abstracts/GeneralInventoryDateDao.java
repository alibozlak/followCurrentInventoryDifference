package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.GeneralInventoryDate;

public interface GeneralInventoryDateDao {
    long getLastGeneralInventoryDate();
    boolean addGeneralInventoryDate(long date);
    List<GeneralInventoryDate> getAllGeneralInventoryDates();
    boolean deleteGeneralInventoryDate(int generalInventoryId);
}
