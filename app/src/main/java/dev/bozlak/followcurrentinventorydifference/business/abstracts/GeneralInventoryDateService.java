package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.GeneralInventoryDate;

public interface GeneralInventoryDateService {
    boolean addGeneralInventoryDate(long date);
    List<GeneralInventoryDate> getAllGeneralInventoryDates();
    boolean deleteGeneralInventoryDate(int generalInventoryId);
    long getLastGeneralInventoryDate();
}
