package code.challenge.courier.offer.rules;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.offer.Offer;
import code.challenge.courier.exceptions.OfferCannotApplyException;

public interface OfferRule {

    void apply(CourierPackage courierPackage, Offer availableOffer) throws OfferCannotApplyException;
    
}
