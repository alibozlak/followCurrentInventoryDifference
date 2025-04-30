package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.affectingtypes;

public class EventIdAndAffectingType {
    private int eventId;
    private String affectingType;

    public EventIdAndAffectingType() {
    }

    public EventIdAndAffectingType(int eventId, String affectingType) {
        this.eventId = eventId;
        this.affectingType = affectingType;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getAffectingType() {
        return affectingType;
    }

    public void setAffectingType(String affectingType) {
        this.affectingType = affectingType;
    }
}
