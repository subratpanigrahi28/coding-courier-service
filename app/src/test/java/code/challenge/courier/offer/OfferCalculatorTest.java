package code.challenge.courier.offer;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.offer.rules.OfferDistanceRule;
import code.challenge.courier.offer.rules.OfferRule;
import code.challenge.courier.offer.rules.OfferWeightRule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferCalculatorTest {

    @Test
    void getDiscount() {
        List<Offer> offers = new ArrayList<>() {{
            add(new Offer("OFFR001", 10, new Range(0, 200), new Range(70, 200)));
            add(new Offer("OFFR002", 7, new Range(50, 150), new Range(100, 250)));
            add(new Offer("OFFR003", 5, new Range(50, 150), new Range(10, 150)));
        }};

        List<OfferRule> offerRules = new ArrayList<>() {{
            add(new OfferDistanceRule());
            add(new OfferWeightRule());
        }};

        OfferCalculator offerCalculator = new OfferCalculator();
        offerCalculator.initializeOffers(offers);
        offerCalculator.initializeRules(offerRules);

        assertEquals(0, offerCalculator.getDiscount(null));
        assertEquals(0, offerCalculator.getDiscount(
                new CourierPackage("PCK1", 50, 30, "OFFR001")));
        assertEquals(10, offerCalculator.getDiscount(
                new CourierPackage("PCK1", 100, 100, "OFFR001")));
    }
}