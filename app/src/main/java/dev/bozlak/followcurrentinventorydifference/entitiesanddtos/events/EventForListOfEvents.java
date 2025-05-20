package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events;

public class EventForListOfEvents {
    private int eventId;
    private String productCode;
    private String productName;
    private double eventAmount;
    private long eventDateAndTime;
    private String eventType;

    public EventForListOfEvents() {
    }

    public EventForListOfEvents(int eventId, String productCode, String productName,
                                double eventAmount, long eventDateAndTime, String eventType) {
        this.eventId = eventId;
        this.productCode = productCode;
        this.productName = productName;
        this.eventAmount = eventAmount;
        this.eventDateAndTime = eventDateAndTime;
        this.eventType = eventType;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
