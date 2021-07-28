package code.challenge.courier;

import code.challenge.courier.utils.Utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;

public class CourierPackage {

    private final String id;

    private final String offerCode;

    private final double weight;

    private final double distance;

    public CourierPackage(@Nonnull String id, double weight, double distance, @Nullable String offerCode) {

        assert !id.isBlank() && !id.isEmpty();

        this.id = id.toUpperCase();
        this.offerCode = offerCode;
        this.weight = weight;
        this.distance = distance;
    }
    public String getId() {
        return id;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public double getDistance() {
        return distance;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        return id.equalsIgnoreCase(((CourierPackage)obj).getId());
    }

    public static void main(String[] args) {
        double d = 11.0;
        System.out.println(Utils.stringValueAfterRounding(d));
    }

}