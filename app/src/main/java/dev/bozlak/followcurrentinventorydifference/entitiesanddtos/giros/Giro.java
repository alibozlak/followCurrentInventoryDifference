package dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros;

public class Giro extends GiroDateAndAmount{
    private int giroId;

    public Giro(){}

    public Giro(int giroId, long giroDate, double giroAmount) {
        super(giroDate, giroAmount);
        this.giroId = giroId;
    }

    public int getGiroId() {
        return giroId;
    }

    public void setGiroId(int giroId) {
        this.giroId = giroId;
    }
}
