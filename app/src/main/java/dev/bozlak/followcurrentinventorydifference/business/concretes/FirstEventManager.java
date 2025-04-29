package dev.bozlak.followcurrentinventorydifference.business.concretes;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.EventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;

public class FirstEventManager implements EventAffectingInventoryService {
    private static FirstEventManager firstEventManager;
    private final EventAffectingInventoryDao eventAffectingInventoryDao;

    private FirstEventManager(EventAffectingInventoryDao eventAffectingInventoryDao) {
        this.eventAffectingInventoryDao = eventAffectingInventoryDao;
    }

    public static synchronized FirstEventManager getInstance(EventAffectingInventoryDao eventAffectingInventoryDao) {
        if (firstEventManager == null) {
            firstEventManager = new FirstEventManager(eventAffectingInventoryDao);
        }
        return firstEventManager;
    }

    @Override
    public boolean addEventAffectingInventory(EventProductIdEventAmountAndDate eventProductIdEventAmountAndDate) {
        return this.eventAffectingInventoryDao.addEventAffectingInventory(eventProductIdEventAmountAndDate);
    }
}
