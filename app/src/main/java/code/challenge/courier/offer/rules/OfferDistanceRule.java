package code.challenge.courier.offer.rules;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.offer.Offer;
import code.challenge.courier.exceptions.OfferCannotApplyException;

public class OfferDistanceRule implements OfferRule {

    public void apply(CourierPackage courierPackage, Offer availableOffer) throws OfferCannotApplyException {

        // return if valid distance
        if (availableOffer != null
            && availableOffer.getDistanceAllowed() != null
            && courierPackage != null 
            && availableOffer.getDistanceAllowed().isValueInRange(courierPackage.getDistance())) {
            return;
        }

        throw new OfferCannotApplyException("provided distance is not in range");
    }
    
}
