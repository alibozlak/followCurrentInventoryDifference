package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;

public interface EventAffectingInventoryDao {
    double getSumOfEventAmountGivenProductIdAndLastProductInventory(
            int productId, long lastProductInventoryDate
    );
    boolean addEventAffectingInventory(EventProductIdEventAmountAndDate eventProductIdEventAmountAndDate);
}
