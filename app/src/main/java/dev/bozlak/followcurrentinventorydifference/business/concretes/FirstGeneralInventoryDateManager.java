package dev.bozlak.followcurrentinventorydifference.business.concretes;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;

public class FirstGeneralInventoryDateManager implements GeneralInventoryDateService {
    private static FirstGeneralInventoryDateManager firstGeneralInventoryDateManager;
    private final GeneralInventoryDateDao generalInventoryDateDao;

    private FirstGeneralInventoryDateManager(GeneralInventoryDateDao generalInventoryDateDao) {
        this.generalInventoryDateDao = generalInventoryDateDao;
    }

    public static synchronized FirstGeneralInventoryDateManager getInstance(
            GeneralInventoryDateDao generalInventoryDateDao
    ) {
        if (firstGeneralInventoryDateManager == null) {
            firstGeneralInventoryDateManager = new FirstGeneralInventoryDateManager(generalInventoryDateDao);
        }
        return firstGeneralInventoryDateManager;
    }

    @Override
    public boolean addGeneralInventoryDate(long date) {
        return this.generalInventoryDateDao.addGeneralInventoryDate(date);
    }
}
