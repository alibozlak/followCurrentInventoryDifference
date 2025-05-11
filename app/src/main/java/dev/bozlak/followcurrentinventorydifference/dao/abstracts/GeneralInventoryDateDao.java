package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

public interface GeneralInventoryDateDao {
    long getLastGeneralInventoryDate();
    boolean addGeneralInventoryDate(long date);
}
