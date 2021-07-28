package code.challenge.courier.delivery;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.exceptions.DeliveryTimeCannotEstimateException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryEstimatorTest {

    @Test
    void timeTakenToFinishDelivery()  {

        List<CourierPackage> packages = new ArrayList<>() {{
            add(new CourierPackage("PCK1", 50, 30, "OFR001"));
            add(new CourierPackage("PCK2", 75, 125, "OFFR0008"));
            add(new CourierPackage("PCK3", 175, 100, "OFFR0008"));
            add(new CourierPackage("PCK4", 110, 60, "OFFR002"));
            add(new CourierPackage("PCK5", 155, 95, "NA"));
        }};

        ShipmentFinder shipmentFinder = new ShipmentFinder(packages, 200);
        List<List<CourierPackage>> shipments = shipmentFinder.findAllShipments();
        DeliveryEstimator deliveryEstimator = new DeliveryEstimator(2, 70);
        Map<String, Double> estimates = deliveryEstimator.estimate(shipments);

        assertEquals(5, estimates.size());
        assertEquals(1.78, estimates.get("PCK2"));
        assertEquals(0.85, estimates.get("PCK4"));
        assertEquals(1.42, estimates.get("PCK3"));
        assertEquals(4.19, estimates.get("PCK5"));
        assertEquals(3.98, estimates.get("PCK1"));

    }
}