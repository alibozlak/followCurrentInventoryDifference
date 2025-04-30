package dev.bozlak.followcurrentinventorydifference.dao.abstracts;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.affectingtypes.EventIdAndAffectingType;

public interface AffectingTypeDao {
    boolean addAffectingTypeForEvent(EventIdAndAffectingType eventIdAndAffectingType);
}
