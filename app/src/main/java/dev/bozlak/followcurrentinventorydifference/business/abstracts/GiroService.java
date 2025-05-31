package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.Giro;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroWithStartToEndDate;

public interface GiroService {
    long getMinimumDayForCalendarViewInAddGiroFragment();
    boolean addGiro(GiroDateAndAmount giroDateAndAmount);
    double getSumGiroAmountAfterLastGeneralInventoryDate();
    List<Giro> getAllGirosAfterLastGeneralInventoryDate();
    GiroWithStartToEndDate getGiroWithStartToEndDate(int giroId);
    boolean updateGiroAmount(int giroId, double newGiroAmount);
}
