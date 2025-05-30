package dev.bozlak.followcurrentinventorydifference.business.concretes;

import java.util.List;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.GiroService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GiroDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.Giro;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroDateAndAmount;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.giros.GiroWithStartToEndDate;

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

    @Override
    public double getSumGiroAmountAfterLastGeneralInventoryDate() {
        long lastGeneralInventoryDate = generalInventoryDateDao.getLastGeneralInventoryDate();
        return this.giroDao.getSumGiroAmountAfterLastGeneralInventoryDate(lastGeneralInventoryDate);
    }

    @Override
    public List<Giro> getAllGirosAfterLastGeneralInventoryDate() {
        long lastGeneralInventoryDate = generalInventoryDateDao.getLastGeneralInventoryDate();
        return this.giroDao.getAllGirosAfterLastGeneralInventoryDate(lastGeneralInventoryDate);
    }

    @Override
    public GiroWithStartToEndDate getGiroWithStartToEndDate(int giroId) {
        long lastGeneralInventoryDate = generalInventoryDateDao.getLastGeneralInventoryDate();
        GiroWithStartToEndDate giroWithStartToEndDate = this.giroDao.getGiroWithStartToEndDate(giroId);
        long giroStartDate = giroWithStartToEndDate.getStartDate();
        if(giroStartDate < lastGeneralInventoryDate){
            giroWithStartToEndDate.setStartDate(lastGeneralInventoryDate + 86_400_000);
        }
        return giroWithStartToEndDate;
    }

    @Override
    public boolean updateGiroAmount(int giroId, double newGiroAmount) {
        return this.giroDao.updateGiroAmount(giroId, newGiroAmount);
    }
}
