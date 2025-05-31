package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros;

public class GiroWithStartToEndDate extends Giro {
    private long startDate;

    public GiroWithStartToEndDate(){}

    public GiroWithStartToEndDate(int giroId, long startDate, long endDate, double giroAmount) {
        super(giroId, endDate, giroAmount);
        this.startDate = startDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
}
