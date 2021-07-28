package code.challenge.courier.offer;

public class Offer {
    
    private final String code;

    private final int discount;

    private final Range distanceAllowed;

    private final Range weightAllowed;

    public Offer(String code, int discount, Range distanceAllowed, Range weightAllowed) {
        this.code = code;
        this.discount = discount;
        this.distanceAllowed = distanceAllowed;
        this.weightAllowed = weightAllowed;
    }

    public String getCode() {
        return code;
    }

    public int getDiscount() {
        return discount;
    }

    public Range getDistanceAllowed() {
        return distanceAllowed;
    }

    public Range getWeightAllowed() {
        return weightAllowed;
    }
}
