/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package code.challenge.courier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import code.challenge.courier.cost.DeliveryCostCalculator;
import code.challenge.courier.delivery.DeliveryEstimator;
import code.challenge.courier.delivery.ShipmentFinder;
import code.challenge.courier.offer.Offer;
import code.challenge.courier.offer.OfferCalculator;
import code.challenge.courier.offer.rules.OfferDistanceRule;
import code.challenge.courier.offer.rules.OfferRule;
import code.challenge.courier.offer.rules.OfferWeightRule;
import code.challenge.courier.offer.Range;
import code.challenge.courier.utils.Utils;

public class App {

    private final OfferCalculator offerCalculator;

    public App() {
        this.offerCalculator = new OfferCalculator();
    }

    public void initializeOffers(List<Offer> offers) {
        List<OfferRule> offerRules = new ArrayList<>() {{
            add(new OfferDistanceRule());
            add(new OfferWeightRule());
        }};
        offerCalculator.initializeOffers(offers);
        offerCalculator.initializeRules(offerRules);
    }

    public OfferCalculator getOfferCalculator() {
        return offerCalculator;
    }

    public List<DeliveryEstimate> estimate(final List<CourierPackage> packages, double baseDeliveryCost,
                                           int noOfVehicles, int maxSpeed, int maxCarriageWeight) {
        List<DeliveryEstimate> estimates = new ArrayList<>();

        ShipmentFinder shipmentFinder = new ShipmentFinder(packages, maxCarriageWeight);
        List<List<CourierPackage>> shipments = shipmentFinder.findAllShipments();

        DeliveryEstimator deliveryEstimator = new DeliveryEstimator(noOfVehicles, maxSpeed);
        Map<String, Double> deliveryEstimates = deliveryEstimator.estimate(shipments);

        packages.forEach(courierPackage -> {
            DeliveryEstimate estimate = new DeliveryEstimate(courierPackage.getId());
            int discountPercentage = getOfferCalculator().getDiscount(courierPackage);
            DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(courierPackage, baseDeliveryCost, discountPercentage);
            estimate.setDiscount(deliveryCostCalculator.discountedValue());
            estimate.setTotalCost(deliveryCostCalculator.totalCostAfterDiscount());
            estimate.setEstimatedDeliveryTimeInHours(deliveryEstimates.get(courierPackage.getId()));
            estimates.add(estimate);
        });

        return estimates;
    }

    public static void main(String[] args) {
        System.out.println("input-start");
        List<CourierPackage> packages = new ArrayList<>();
        App app = new App();

        List<Offer> offers = new ArrayList<Offer>() {{
            add(new Offer("OFFR001", 10, new Range(0, 200), new Range(70, 200)));
            add(new Offer("OFFR002", 7, new Range(50, 150), new Range(100, 250)));
            add(new Offer("OFFR003", 5, new Range(50, 150), new Range(10, 150)));
        }};
        app.initializeOffers(offers);

        Scanner scanner = new Scanner(System.in);

        // base deliver cost - input
        int baseDeliverCost = scanner.nextInt();

        // package count - input
        int packageCounts = scanner.nextInt();

        for(int i = 0; i < packageCounts; i++) {
            packages.add(new CourierPackage(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.next()));
        }

        int noOfVehicles = scanner.nextInt();
        int maxSpeed = scanner.nextInt();
        int maxCarriageWeight = scanner.nextInt();

        scanner.close();
        System.out.println("input-end");

        System.out.println("output-start");
        List<DeliveryEstimate> estimates = app.estimate(packages, baseDeliverCost, noOfVehicles, maxSpeed, maxCarriageWeight);
        estimates.forEach(estimate -> {
            System.out.println(estimate.getId() + " " +
                    Utils.stringValueAfterRounding(estimate.getDiscount())+ " " +
                    Utils.stringValueAfterRounding(estimate.getTotalCost()) + " " +
                    Utils.stringValueAfterRounding(estimate.getEstimatedDeliveryTimeInHours()));
        });
        System.out.println("output-end");
    }


}