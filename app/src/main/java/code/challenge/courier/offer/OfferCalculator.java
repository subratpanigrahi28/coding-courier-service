package code.challenge.courier.offer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.exceptions.OfferCannotApplyException;
import code.challenge.courier.offer.rules.OfferRule;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;

public class OfferCalculator {

    private final Map<String, Offer> offerMap;

    private final List<OfferRule> rules;

    public OfferCalculator() {
        this.offerMap = new HashMap<>();
        this.rules = new ArrayList<>();
    }

    public final void initializeOffers(List<Offer> offers) { 

        if (offers == null || offers.isEmpty()) {
            return;
        }

       this.offerMap.putAll(offers.stream().collect(toMap(o -> o.getCode().toUpperCase(), o -> o)));
    }

    public final void initializeRules(List<OfferRule> offerRules) { 

        if (offerRules == null || offerRules.isEmpty()) {
            return;
        }

        this.rules.addAll(offerRules);
    }

    public final int getDiscount(final CourierPackage courierPackage) {

        int discount = 0;

        if (courierPackage == null) {
            return discount;
        }

        Optional<Offer> optional = findOffer(courierPackage.getOfferCode());
        if (optional.isEmpty()) {
            return discount;
        }
        Offer availableOffer = optional.get();

        try {
            discount = discountAfterApplyOffer(courierPackage, availableOffer);
        }
        catch(OfferCannotApplyException exception) {
            discount = 0;
        }

        return discount;

    }

    private int discountAfterApplyOffer(final CourierPackage courierPackage, final Offer availableOffer) throws OfferCannotApplyException {
        for (OfferRule offerRule : rules) {
            offerRule.apply(courierPackage, availableOffer);
        }

        return availableOffer.getDiscount();
    }

    private Optional<Offer> findOffer(final String code) {

        if (code == null || code.isBlank() || code.isEmpty()) {
            return Optional.empty();
        }

        if (this.offerMap.isEmpty()) {
            return Optional.empty();
        }

        if (!this.offerMap.containsKey(code.toUpperCase())) {
            return Optional.empty();
        }

        Offer offer = this.offerMap.get(code.toUpperCase());

        return Optional.of(offer);
    }


}