package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

public interface EventAffectingInventoryDao {
    double getSumOfEventAmountGivenProductIdAndLastProductInventory(
            int productId, long lastProductInventoryDate
    );


}
