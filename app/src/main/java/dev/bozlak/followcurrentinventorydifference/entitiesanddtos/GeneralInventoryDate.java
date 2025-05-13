package dev.bozlak.followcurrentinventorydifference.entitiesanddtos;

public class GeneralInventoryDate {
    private int generalInventoryId;
    private long generalInventoryDateAndTime;

    public GeneralInventoryDate() {
    }

    public GeneralInventoryDate(int generalInventoryId, long generalInventoryDateAndTime) {
        this.generalInventoryId = generalInventoryId;
        this.generalInventoryDateAndTime = generalInventoryDateAndTime;
    }

    public int getGeneralInventoryId() {
        return generalInventoryId;
    }

    public void setGeneralInventoryId(int generalInventoryId) {
        this.generalInventoryId = generalInventoryId;
    }

    public long getGeneralInventoryDateAndTime() {
        return generalInventoryDateAndTime;
    }

    public void setGeneralInventoryDateAndTime(long generalInventoryDateAndTime) {
        this.generalInventoryDateAndTime = generalInventoryDateAndTime;
    }
}
