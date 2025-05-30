package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros;

public class GiroDateAndAmount {
    private long giroDate;
    private double giroAmount;

    public GiroDateAndAmount(long giroDate, double giroAmount){
        this.giroDate = giroDate;
        this.giroAmount = giroAmount;
    }

    public long getGiroDate() {
        return giroDate;
    }

    public void setGiroDate(long giroDate) {
        this.giroDate = giroDate;
    }

    public double getGiroAmount() {
        return giroAmount;
    }

    public void setGiroAmount(double giroAmount) {
        this.giroAmount = giroAmount;
    }
}
