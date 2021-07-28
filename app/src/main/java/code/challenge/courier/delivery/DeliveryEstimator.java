package code.challenge.courier.delivery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.utils.Utils;

public class DeliveryEstimator {

    private final int noOfVehicles;
    private final double maxSpeedPerHour;
    private final double[] vehicleTime;

    public DeliveryEstimator(int noOfVehicles, double maxSpeedPerHour) {
        assert noOfVehicles > 0;
        assert maxSpeedPerHour > 0;

        this.noOfVehicles = noOfVehicles;
        this.maxSpeedPerHour = maxSpeedPerHour;
        
        vehicleTime = new double[this.noOfVehicles];
        for(int i = 0; i < this.noOfVehicles; i++) {
            vehicleTime[i] = 0.0;
        }
    }

    public Map<String, Double> estimate(List<List<CourierPackage>> deliveries) {
        Map<String, Double> estimates = new HashMap<>();

        deliveries.forEach(s -> {
            int availableVehicle = nextAvailableVehicle();
            double vehicleAvailableTime = vehicleTime[availableVehicle];
            estimates.putAll(deliveryTime(s, vehicleAvailableTime));
            addTime(availableVehicle, 2 * calculateMaxDistance(s));
        });

        return estimates;
    }

    private Map<String, Double> deliveryTime(final List<CourierPackage> delivery, double vehicleAvailableTime) {
        Map<String, Double> estimates = new HashMap<>();
        delivery.forEach(c -> {
            estimates.put(c.getId(), Utils.valueAfterRoundingDown(vehicleAvailableTime + c.getDistance()/maxSpeedPerHour));
        });
        return estimates;
    }

    private double calculateMaxDistance(final List<CourierPackage> delivery) {
        return delivery.stream()
        .mapToDouble(c -> Utils.valueAfterRoundingDown(c.getDistance()/maxSpeedPerHour))
        .max()
        .orElse(0.0);
    }

    private int nextAvailableVehicle() {
        double min = Arrays.stream(vehicleTime).min().orElse(0.0);

        for(int i = 0; i < noOfVehicles; i++) {
            if (min == vehicleTime[i]) {
                return i;
            }
        }

        return -1;
    }

    private void addTime(int index, double time) {
        if (index >= 0 && index < vehicleTime.length) {
            vehicleTime[index] += time;
        }
    }

}
