package code.challenge.courier.delivery;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import code.challenge.courier.CourierPackage;

import static org.junit.jupiter.api.Assertions.*;

public class ShipmentFinderTest {

    @Test
    void testGetMaximizedPackagesForShipment() {


        List<CourierPackage> packages = new ArrayList<>() {{
            add(new CourierPackage("PCK1", 50, 30, "OFR001"));
            add(new CourierPackage("PCK2", 75, 125, "OFFR0008"));
            add(new CourierPackage("PCK3", 175, 100, "OFFR0008"));
            add(new CourierPackage("PCK4", 110, 60, "OFFR002"));
            add(new CourierPackage("PCK5", 155, 95, "NA"));
        }};

        List<List<CourierPackage>> expectedResult = new ArrayList<>(){{
            add(new ArrayList<>() {{
                add(new CourierPackage("PCK2", 75, 125, "OFFR0008"));
                add(new CourierPackage("PCK4", 110, 60, "OFFR002"));
            }});
            add(new ArrayList<>() {{
                add(new CourierPackage("PCK3", 175, 100, "OFFR0008"));
            }});
            add(new ArrayList<>() {{
                add(new CourierPackage("PCK5", 155, 95, "NA"));
            }});
            add(new ArrayList<>() {{
                add(new CourierPackage("PCK1", 50, 30, "OFR001"));
            }});
        }};

        ShipmentFinder shipmentFinder = new ShipmentFinder(packages, 200);

        List<List<CourierPackage>> shipments = shipmentFinder.findAllShipments();

        assertEquals(4, shipments.size());
        assertArrayEquals(expectedResult.toArray(), shipments.toArray());
    }
}
