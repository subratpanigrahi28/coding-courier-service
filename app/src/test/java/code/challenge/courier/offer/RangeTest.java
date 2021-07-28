package code.challenge.courier.offer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RangeTest {
    @Test
    void testIsValueInRange() {

        Range range = new Range(4, 8);

        assertFalse(range.isValueInRange(3));
        assertTrue(range.isValueInRange(7));
    }
}
