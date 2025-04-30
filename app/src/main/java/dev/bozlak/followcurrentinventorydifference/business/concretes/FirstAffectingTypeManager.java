package dev.bozlak.followcurrentinventorydifference.business.concretes;

import dev.bozlak.followcurrentinventorydifference.business.abstracts.AffectingTypeService;
import dev.bozlak.followcurrentinventorydifference.dao.abstracts.AffectingTypeDao;
import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.affectingtypes.EventIdAndAffectingType;

public class FirstAffectingTypeManager implements AffectingTypeService {
    private static FirstAffectingTypeManager firstAffectingTypeManager;
    private final AffectingTypeDao affectingTypeDao;

    private FirstAffectingTypeManager(AffectingTypeDao affectingTypeDao) {
        this.affectingTypeDao = affectingTypeDao;
    }

    public static synchronized FirstAffectingTypeManager getInstance(AffectingTypeDao affectingTypeDao) {
        if (firstAffectingTypeManager == null) {
            firstAffectingTypeManager = new FirstAffectingTypeManager(affectingTypeDao);
        }
        return firstAffectingTypeManager;
    }

    @Override
    public boolean addAffectingTypeForEvent(EventIdAndAffectingType eventIdAndAffectingType) {
        return this.affectingTypeDao.addAffectingTypeForEvent(eventIdAndAffectingType);
    }
}
