package dev.bozlak.followcurrentinventorydifference.views;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.AffectingTypeService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.EventAffectingInventoryService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.GeneralInventoryDateService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.GiroService;
import dev.bozlak.followcurrentinventorydifference.business.abstracts.ProductService;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstAffectingTypeManager;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstEventManager;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstGeneralInventoryDateManager;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstGiroManager;
import dev.bozlak.followcurrentinventorydifference.business.concretes.FirstProductManager;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.AffectingTypeDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.EventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.GiroDao;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.ProductDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstAffectingTypeDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstEventAffectingInventoryDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstGeneralInventoryDateDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstGiroDao;
import dev.bozlak.followcurrentinventorydifference.dao.concretes.FirstProductDao;

public class ServiceContainer {
    private static final ProductDao productDao = FirstProductDao.getInstance();
    private static final EventAffectingInventoryDao eventAffectingInventoryDao
            = FirstEventAffectingInventoryDao.getInstance();
    private static final GeneralInventoryDateDao generalInventoryDateDao
            = FirstGeneralInventoryDateDao.getInstance();
    private static final AffectingTypeDao affectingTypeDao = FirstAffectingTypeDao.getInstance();
    private static final GiroDao giroDao = FirstGiroDao.getInstance();

    public static final ProductService productService = FirstProductManager.getInstance(
            generalInventoryDateDao,
            productDao,
            eventAffectingInventoryDao
    );

    public static final EventAffectingInventoryService eventAffectingInventoryService =
            FirstEventManager.getInstance(
                    eventAffectingInventoryDao,
                    generalInventoryDateDao,
                    affectingTypeDao,
                    productDao
            );

    public static final AffectingTypeService affectingTypeService =
            FirstAffectingTypeManager.getInstance(affectingTypeDao);

    public static final GeneralInventoryDateService generalInventoryDateService =
            FirstGeneralInventoryDateManager.getInstance(generalInventoryDateDao);

    public static final GiroService giroService = FirstGiroManager.getInstance(giroDao, generalInventoryDateDao);
}
