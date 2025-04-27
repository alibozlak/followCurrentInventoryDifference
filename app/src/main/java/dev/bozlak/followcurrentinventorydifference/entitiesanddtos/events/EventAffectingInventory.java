package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events;

public class EventAffectingInventory {
    private int eventId;
    private int productId;
    /**
     * Can be positive or negative.
     * Positive value mean positive event for store,
     * Negative value mean negative event for store.
     */
    private double amount;
    private String eventDateAndTime;
}
