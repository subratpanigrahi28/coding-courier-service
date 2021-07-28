package code.challenge.courier.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void valueAfterRoundingDown() {
        assertEquals(11.0, Utils.valueAfterRoundingDown(11.0));
        assertEquals(11.74, Utils.valueAfterRoundingDown(11.743567645736));
        assertEquals(11.78, Utils.valueAfterRoundingDown(11.7899999999));
    }

    @Test void stringValueAfterRounding() {
        assertEquals("11", Utils.stringValueAfterRounding(11.0));
        assertEquals("11.55", Utils.stringValueAfterRounding(11.554));

    }
}