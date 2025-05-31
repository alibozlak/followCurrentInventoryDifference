package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.Giro;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroWithStartToEndDate;

public interface GiroDao {
    long getLastGiroDate();
    boolean addGiro(GiroDateAndAmount giroDateAndAmount);
    double getSumGiroAmountAfterLastGeneralInventoryDate(long lastGeneralInventoryDate);
    List<Giro> getAllGirosAfterLastGeneralInventoryDate(long lastGeneralInventoryDate);
    GiroWithStartToEndDate getGiroWithStartToEndDate(int giroId);
    boolean updateGiroAmount(int giroId, double newGiroAmount);
}
