package code.challenge.courier.cost;

import code.challenge.courier.CourierPackage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostCalculatorTest {

    @Test void testTotalCostAfterDiscount() {
        DeliveryCostCalculator deliveryCostCalculator =
                new DeliveryCostCalculator(
                        new CourierPackage("PCK1", 50, 30, "OFR001"), 100, 10);

        assertEquals(750, deliveryCostCalculator.deliveryCost());
        assertEquals(675, deliveryCostCalculator.totalCostAfterDiscount());
        assertEquals(75, deliveryCostCalculator.discountedValue());
    }

}