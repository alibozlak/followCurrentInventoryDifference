package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;

public interface GiroDao {
    long getLastGiroDate();
    boolean addGiro(GiroDateAndAmount giroDateAndAmount);
}
