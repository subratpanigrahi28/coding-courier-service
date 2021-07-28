package code.challenge.courier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DeliveryEstimate {

    private final String id;

    private double discount;

    private double totalCost;

    private double estimatedDeliveryTimeInHours;

    public DeliveryEstimate(@Nonnull String id) {

        assert !id.isBlank() && !id.isEmpty();

        this.id = id.toUpperCase();
        this.discount = 0;
        this.totalCost = 0;
        this.estimatedDeliveryTimeInHours = 0;
    }

    public String getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getEstimatedDeliveryTimeInHours() {
        return estimatedDeliveryTimeInHours;
    }

    public void setEstimatedDeliveryTimeInHours(double estimatedDeliveryTimeInHours) {
        this.estimatedDeliveryTimeInHours = estimatedDeliveryTimeInHours;
    }

}