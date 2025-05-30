package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events;

public class EventAffectingInventory {
    private int eventId;
    private int productId;
    /**
     * Can be positive or negative.
     * Positive value mean positive event for store,
     * Negative value mean negative event for store.
     */
    private double eventAmount;
    private long eventDateAndTime;

    public EventAffectingInventory(int productId, double eventAmount, long eventDateAndTime) {
        this.productId = productId;
        this.eventAmount = eventAmount;
        this.eventDateAndTime = eventDateAndTime;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getEventAmount() {
        return eventAmount;
    }

    public void setEventAmount(double eventAmount) {
        this.eventAmount = eventAmount;
    }

    public long getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(long eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }
}
