/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package code.challenge.courier;

import code.challenge.courier.offer.Offer;
import code.challenge.courier.offer.Range;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;

class AppTest {

    @Test void appHasAGreeting() {
        int baseDeliveryCost = 100;
        int noOfVehicles = 2;
        int maxSpeed = 70;
        int maxCarriageWeight = 200;

        List<CourierPackage> packages = new ArrayList<>() {{
            add(new CourierPackage("PCK1", 50, 30, "OFR001"));
            add(new CourierPackage("PCK2", 75, 125, "OFFR0008"));
            add(new CourierPackage("PCK3", 175, 100, "OFFR0008"));
            add(new CourierPackage("PCK4", 110, 60, "OFFR002"));
            add(new CourierPackage("PCK5", 155, 95, "NA"));
        }};

        App app = new App();
        List<Offer> offers = new ArrayList<Offer>() {{
            add(new Offer("OFFR001", 10, new Range(0, 200), new Range(70, 200)));
            add(new Offer("OFFR002", 7, new Range(50, 150), new Range(100, 250)));
            add(new Offer("OFFR003", 5, new Range(50, 150), new Range(10, 150)));
        }};
        app.initializeOffers(offers);

        List<DeliveryEstimate> estimates = app.estimate(packages, baseDeliveryCost, noOfVehicles, maxSpeed, maxCarriageWeight);

        assertEquals(5, estimates.size());

        assertEquals("PCK1", estimates.get(0).getId());
        assertEquals(0, estimates.get(0).getDiscount());
        assertEquals(750, estimates.get(0).getTotalCost());
        assertEquals(3.98, estimates.get(0).getEstimatedDeliveryTimeInHours());

        assertEquals("PCK2", estimates.get(1).getId());
        assertEquals(0, estimates.get(1).getDiscount());
        assertEquals(1475, estimates.get(1).getTotalCost());
        assertEquals(1.78, estimates.get(1).getEstimatedDeliveryTimeInHours());

        assertEquals("PCK3", estimates.get(2).getId());
        assertEquals(0, estimates.get(2).getDiscount());
        assertEquals(2350, estimates.get(2).getTotalCost());
        assertEquals(1.42, estimates.get(2).getEstimatedDeliveryTimeInHours());

        assertEquals("PCK4", estimates.get(3).getId());
        assertEquals(105, estimates.get(3).getDiscount());
        assertEquals(1395, estimates.get(3).getTotalCost());
        assertEquals(0.85, estimates.get(3).getEstimatedDeliveryTimeInHours());

        assertEquals("PCK5", estimates.get(4).getId());
        assertEquals(0, estimates.get(4).getDiscount());
        assertEquals(2125, estimates.get(4).getTotalCost());
        assertEquals(4.19, estimates.get(4).getEstimatedDeliveryTimeInHours());
    }
}
