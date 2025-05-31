package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.Giro;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;

public interface GiroDao {
    long getLastGiroDate();
    boolean addGiro(GiroDateAndAmount giroDateAndAmount);
    double getSumGiroAmountAfterLastGeneralInventoryDate(long lastGeneralInventoryDate);
    List<Giro> getAllGirosAfterLastGeneralInventoryDate(long lastGeneralInventoryDate);
}
