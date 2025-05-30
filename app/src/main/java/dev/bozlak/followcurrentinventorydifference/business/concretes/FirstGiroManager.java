package dev.bozlak.followcurrentinventorydifference.business.concretes;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GiroService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GiroDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;

public class FirstGiroManager implements GiroService {
    private static FirstGiroManager firstGiroManager;
    private final GiroDao giroDao;
    private final GeneralInventoryDateDao generalInventoryDateDao;

    private FirstGiroManager(GiroDao giroDao, GeneralInventoryDateDao generalInventoryDateDao) {
        this.giroDao = giroDao;
        this.generalInventoryDateDao = generalInventoryDateDao;
    }

    public static synchronized FirstGiroManager getInstance(
            GiroDao giroDao, GeneralInventoryDateDao generalInventoryDateDao
    ) {
        if (firstGiroManager == null) {
            firstGiroManager = new FirstGiroManager(giroDao, generalInventoryDateDao);
        }
        return firstGiroManager;
    }

    @Override
    public long getMinimumDayForCalendarViewInAddGiroFragment() {
        long minimumDayForCalendarViewInAddGiroFragment = 86_400_000;
        long lastGiroDate = giroDao.getLastGiroDate();
        long lastGeneralInventoryDate = generalInventoryDateDao.getLastGeneralInventoryDate();
        minimumDayForCalendarViewInAddGiroFragment += Math.max(lastGiroDate, lastGeneralInventoryDate);
        return minimumDayForCalendarViewInAddGiroFragment;
    }

    @Override
    public boolean addGiro(GiroDateAndAmount giroDateAndAmount) {
        return this.giroDao.addGiro(giroDateAndAmount);
    }
}
