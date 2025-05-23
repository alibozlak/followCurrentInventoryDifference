package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventForListOfEvents;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;

public interface EventAffectingInventoryService {
    boolean addEventAffectingInventory(EventProductIdEventAmountAndDate eventProductIdEventAmountAndDate);
    int getLastEventId();
    List<EventForListOfEvents> getAllPositiveEvents();
    List<EventForListOfEvents> getAllNegativeEvents();
    boolean deleteEventGivenEventId(int eventId);
}
