package dev.bozlak.followcurrentinventorydifference.business.abstracts;

import dev.bozlak.followcurrentinventorydifference.entitiesanddtos.events.affectingtypes.EventIdAndAffectingType;

public interface AffectingTypeService {
    boolean addAffectingTypeForEvent(EventIdAndAffectingType eventIdAndAffectingType);
    boolean deleteAffectingTypeGivenEventId(int eventId);
}
