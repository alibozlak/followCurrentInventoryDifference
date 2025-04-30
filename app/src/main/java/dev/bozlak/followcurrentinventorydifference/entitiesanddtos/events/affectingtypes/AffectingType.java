package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.affectingtypes;

public class AffectingType extends EventIdAndAffectingType {
    private int affectingTypeId;

    public AffectingType() {
    }

    public AffectingType(int affectingTypeId, int eventId, String affectingType) {
        super(eventId, affectingType);
        this.affectingTypeId = affectingTypeId;
    }

    public int getAffectingTypeId() {
        return affectingTypeId;
    }

    public void setAffectingTypeId(int affectingTypeId) {
        this.affectingTypeId = affectingTypeId;
    }
}
