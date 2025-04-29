package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events;

public class EventProductIdEventAmountAndDate {
    private int productId;
    private double eventAmount;
    private long eventDateAndTime;

    public EventProductIdEventAmountAndDate(int productId, double eventAmount, long eventDateAndTime) {
        setProductId(productId);
        setEventAmount(eventAmount);
        setEventDateAndTime(eventDateAndTime);
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
