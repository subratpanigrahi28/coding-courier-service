package code.challenge.courier.cost;

import code.challenge.courier.CourierPackage;

public class DeliveryCostCalculator {

    private final CourierPackage courierPackage;
    private final double baseDeliverCost;
    private final int discount;

    public DeliveryCostCalculator(CourierPackage courierPackage, double baseDeliverCost, int discount) {
        this.courierPackage = courierPackage;
        this.discount = discount;
        this.baseDeliverCost = baseDeliverCost;
    }

    public double totalCostAfterDiscount() {
        double deliveryCost = deliveryCost();
        return deliveryCost - discountValue(deliveryCost, discount);
    }

    public double deliveryCost() {
        return baseDeliverCost + (courierPackage.getWeight() * 10) + (courierPackage.getDistance()* 5);
    }

    public double discountedValue() {
        return discountValue(deliveryCost(), discount);
    }

    private double discountValue(double total, int discount) {
        return (total * discount) / 100;
    }

}
