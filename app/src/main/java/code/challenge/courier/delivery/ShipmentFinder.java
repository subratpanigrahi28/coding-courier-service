package code.challenge.courier.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import code.challenge.courier.CourierPackage;
import code.challenge.courier.utils.SubSetGenerator;

public class ShipmentFinder {

    private final int maxAllowedWeightPerShipment;

    private final List<CourierPackage> packages;

    public ShipmentFinder(final List<CourierPackage> packages, int maxAllowedWeightPerShipment) {
        assert packages != null && !packages.isEmpty();
        assert maxAllowedWeightPerShipment > 0;

        this.maxAllowedWeightPerShipment = maxAllowedWeightPerShipment;
        this.packages = packages;
    }

    public List<List<CourierPackage>> findAllShipments() {
        List<List<CourierPackage>> allShipments = new ArrayList<>();
        findAllShipments(packages, allShipments);
        return allShipments;
    }

    private void findAllShipments(final List<CourierPackage> packages, final List<List<CourierPackage>> collection) {
        if (packages == null || packages.isEmpty()) {
            return;
        }

        List<CourierPackage> maximizedShipment = findMaximizedShipment(packages);
        collection.add(maximizedShipment);

        List<CourierPackage> deltaList = new ArrayList<>(packages);
        deltaList.removeAll(maximizedShipment);

        findAllShipments(deltaList, collection);
    }

    public List<CourierPackage> findMaximizedShipment(final List<CourierPackage> packages) {

        if (packages == null || packages.isEmpty()) {
            return Collections.emptyList();
        }

        /*
        Shipment should contain max packages vehicle can carry in a trip.
        We should prefer heavier packages when there are multiple shipments with the same no. of packages.
        */
        List<List<CourierPackage>> allPossibleOptions = findAllPossibleOptionsFilteredByMaxAllowedWeight(packages, maxAllowedWeightPerShipment);
        allPossibleOptions = filterByMaxWeight(allPossibleOptions);

        /*
        If the weights are also the same, preference should be given to the shipment which can be delivered first.
         */
        return filterByMinDistance(allPossibleOptions);
    }

    private List<List<CourierPackage>> findAllPossibleOptionsFilteredByMaxAllowedWeight(final List<CourierPackage> packages, final int maxAllowedWeight) {
        
        SubSetGenerator<CourierPackage> iterable = new SubSetGenerator<CourierPackage>(packages);

        return iterable.stream()
        .filter(list -> {
            int totalWeight = calculateTotalWeight(list);
            return totalWeight <= maxAllowedWeight && totalWeight != 0;
        })
        .collect(Collectors.toList());
    }

    private int calculateTotalWeight(final List<CourierPackage> list) {
        int totalWeight = 0;
        for (CourierPackage c : list) {
            totalWeight += c.getWeight();
        }
        return totalWeight;
    }

    private int calculateTotalDistance(final List<CourierPackage> list) {
        int totalDistance = 0;
        for (CourierPackage c : list) {
            totalDistance += c.getDistance();
        }
        return totalDistance;
    }

    private List<List<CourierPackage>> filterByMaxWeight(final List<List<CourierPackage>> options) {

        int max = options.stream().mapToInt(this::calculateTotalWeight).max().orElse(-1);

        return options.stream()
        .filter(list -> max == calculateTotalWeight(list))
        .collect(Collectors.toList());
    }

    private List<CourierPackage> filterByMinDistance(final List<List<CourierPackage>> options) {

        if (options.size() == 1) {
            return options.get(0);
        }
        
        int min = options.stream().mapToInt(this::calculateTotalDistance).min().orElse(-1);

        return options.stream()
        .filter(list -> min == calculateTotalDistance(list))
        .findFirst()
        .orElse(Collections.emptyList());
    }
    
}
