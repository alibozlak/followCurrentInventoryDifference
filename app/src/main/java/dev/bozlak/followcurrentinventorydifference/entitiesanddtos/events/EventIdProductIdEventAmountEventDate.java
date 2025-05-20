package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events;

public class EventIdProductIdEventAmountEventDate {
    private int eventId;
    private int productId;
    private double eventAmount;
    private long eventDate;

    public EventIdProductIdEventAmountEventDate() {
    }

    public EventIdProductIdEventAmountEventDate(int eventId, int productId, double eventAmount, long eventDate) {
        this.eventId = eventId;
        this.productId = productId;
        this.eventAmount = eventAmount;
        this.eventDate = eventDate;
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

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }
}
