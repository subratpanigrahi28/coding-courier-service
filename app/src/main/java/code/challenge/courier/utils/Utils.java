package code.challenge.courier.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    private Utils() {

    }

    public static double valueAfterRoundingDown(double v) {
        BigDecimal bd = new BigDecimal(v).setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }

    public static String stringValueAfterRounding(double v) {
        String result = "";
        BigDecimal bd = new BigDecimal(v).setScale(2, RoundingMode.DOWN);

        try {
            result += bd.longValueExact();
        }
        catch (ArithmeticException e) {
            result += bd.doubleValue();
        }
        return result;
    }
    
}
