package code.challenge.courier.offer.rules;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.exceptions.OfferCannotApplyException;
import code.challenge.courier.offer.Offer;

public class OfferWeightRule implements OfferRule {

    public void apply(CourierPackage courierPackage, Offer availableOffer) throws OfferCannotApplyException {

        // return if valid weight
        if (availableOffer != null
            && availableOffer.getWeightAllowed() != null
            && courierPackage != null 
            && availableOffer.getWeightAllowed().isValueInRange(courierPackage.getWeight())) {
            return;
        }

        throw new OfferCannotApplyException("provided weight is not in range");
    }
    
}
