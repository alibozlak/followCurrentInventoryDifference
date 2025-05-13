package dev.bozlak.followcurrentinventorydifference.business.concretes;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.GeneralInventoryDate;

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

    @Override
    public List<GeneralInventoryDate> getAllGeneralInventoryDates() {
        return this.generalInventoryDateDao.getAllGeneralInventoryDates();
    }

    @Override
    public boolean deleteGeneralInventoryDate(int generalInventoryId) {
        return this.generalInventoryDateDao.deleteGeneralInventoryDate(generalInventoryId);
    }
}
