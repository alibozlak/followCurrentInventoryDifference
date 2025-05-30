package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;

public interface GiroService {
    long getMinimumDayForCalendarViewInAddGiroFragment();
    boolean addGiro(GiroDateAndAmount giroDateAndAmount);
}
