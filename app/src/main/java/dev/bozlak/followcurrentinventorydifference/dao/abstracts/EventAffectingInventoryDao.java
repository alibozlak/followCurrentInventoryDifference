package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventIdProductIdEventAmountEventDate;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;

public interface EventAffectingInventoryDao {
    double getSumOfEventAmountGivenProductIdAndLastProductInventory(
            int productId, long lastProductInventoryDate
    );
    boolean addEventAffectingInventory(EventProductIdEventAmountAndDate eventProductIdEventAmountAndDate);
    int getLastEventId();

    List<EventIdProductIdEventAmountEventDate> getAllPositiveEventDtosGivenLastGeneralInventoryDate(
            long lastGeneralInventoryDate
    );

    List<EventIdProductIdEventAmountEventDate> getAllNegativeEventDtosGivenLastGeneralInventoryDate(
            long lastGeneralInventoryDate
    );
}
