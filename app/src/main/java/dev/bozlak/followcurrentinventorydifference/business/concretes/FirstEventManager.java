package dev.bozlak.followcurrentinventorydifference.business.concretes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.AffectingTypeDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.EventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.ProductDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventForListOfEvents;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventIdProductIdEventAmountEventDate;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.EventProductIdEventAmountAndDate;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.products.ProductCodeAndName;

public class FirstEventManager implements EventAffectingInventoryService {
    private static FirstEventManager firstEventManager;
    private final EventAffectingInventoryDao eventAffectingInventoryDao;
    private final GeneralInventoryDateDao generalInventoryDateDao;
    private final AffectingTypeDao affectingTypeDao;
    private final ProductDao productDao;

    private FirstEventManager(
            EventAffectingInventoryDao eventAffectingInventoryDao,
            GeneralInventoryDateDao generalInventoryDateDao,
            AffectingTypeDao affectingTypeDao,
            ProductDao productDao
    ) {
        this.eventAffectingInventoryDao = eventAffectingInventoryDao;
        this.generalInventoryDateDao = generalInventoryDateDao;
        this.affectingTypeDao = affectingTypeDao;
        this.productDao = productDao;
    }

    public static synchronized FirstEventManager getInstance(
            EventAffectingInventoryDao eventAffectingInventoryDao,
            GeneralInventoryDateDao generalInventoryDateDao,
            AffectingTypeDao affectingTypeDao,
            ProductDao productDao) {
        if (firstEventManager == null) {
            firstEventManager = new FirstEventManager(
                    eventAffectingInventoryDao,
                    generalInventoryDateDao,
                    affectingTypeDao,
                    productDao
            );
        }
        return firstEventManager;
    }

    @Override
    public boolean addEventAffectingInventory(EventProductIdEventAmountAndDate eventProductIdEventAmountAndDate) {
        return this.eventAffectingInventoryDao.addEventAffectingInventory(eventProductIdEventAmountAndDate);
    }

    @Override
    public int getLastEventId() {
        return this.eventAffectingInventoryDao.getLastEventId();
    }

    @Override
    public List<EventForListOfEvents> getAllPositiveEvents() {
        return this.getAllEvents(true);
    }

    @Override
    public List<EventForListOfEvents> getAllNegativeEvents() {
        return this.getAllEvents(false);
    }

    private List<EventForListOfEvents> getAllEvents(boolean isPositiveEvent){
        List<EventForListOfEvents> events = new ArrayList<>();
        long lastGeneralInventoryDate = this.generalInventoryDateDao.getLastGeneralInventoryDate();
        List<EventIdProductIdEventAmountEventDate> eventDtos = null;
        if(isPositiveEvent){
            eventDtos = this.eventAffectingInventoryDao.getAllPositiveEventDtosGivenLastGeneralInventoryDate(
                    lastGeneralInventoryDate
            );
        } else {
            eventDtos = this.eventAffectingInventoryDao.getAllNegativeEventDtosGivenLastGeneralInventoryDate(
                    lastGeneralInventoryDate
            );
        }
        for (EventIdProductIdEventAmountEventDate eventDto : eventDtos) {
            int eventId = eventDto.getEventId();
            String affectingType = this.affectingTypeDao.getAffectingTypeGivenEventId(eventId);
            ProductCodeAndName productCodeAndName = this.productDao.getProductCodeAndNameByProductId(
                    eventDto.getProductId()
            );
            var eventForListOfEvents = new EventForListOfEvents();
            eventForListOfEvents.setEventId(eventId);
            eventForListOfEvents.setProductCode(productCodeAndName.getProductCode());
            eventForListOfEvents.setProductName(productCodeAndName.getProductName());
            eventForListOfEvents.setEventAmount(eventDto.getEventAmount());
            eventForListOfEvents.setEventDateAndTime(eventDto.getEventDate());
            eventForListOfEvents.setEventType(affectingType);
            events.add(eventForListOfEvents);
        }
        return events;
    }
}
