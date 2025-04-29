package dev.bozlak.followcurrentinventorydifference.views;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstEventManager;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstProductManager;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstEventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstGeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstProductDao;

public class ServiceContainer {
    public static ProductService productService = FirstProductManager.getInstance(
            FirstGeneralInventoryDateDao.getInstance(),
            FirstProductDao.getInstance(),
            FirstEventAffectingInventoryDao.getInstance()
    );

    public static EventAffectingInventoryService eventAffectingInventoryService = FirstEventManager.getInstance(
            FirstEventAffectingInventoryDao.getInstance()
    );
}
