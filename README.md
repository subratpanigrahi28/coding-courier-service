# Welcome To Coding Challenge

##Background - Challenge
Kiki, a first-time entrepreneur from the city of Koriko has decided to open a small distance courier service to deliver packages, with her friend Tombo and cat Joji.

Kiki has invested in N no. of vehicles and have driver partners to drive each vehicle & deliver packages.

- problem-1: Delivery Cost Estimation with Offers
- problem-2: Delivery Time Estimation

### Tools
- gradle
- IntelliJ CE IDE
- Command line Application

### Building
To build the entire application, including running unit tests:

`./gradlew build`
`./gradlew test`

To run the application by using Gradle:

`./gradlew run`

### Implementation
- **DeliveryCostCalculator**: class implements the calculation of delivery cost. 
  
  `Delivery Cost = Base Delivery Cost + (Package Total Weight * 10) + (Distance to Destination * 5)`


- **OfferCalculator**: implements all offer calculations, including offer initialization, and offer rules. To add/extends offers class `Offer` and `OfferRule` can be used.
```
        List<Offer> offers = new ArrayList<>() {{
            add(new Offer("OFFR001", 10, new Range(0, 200), new Range(70, 200)));
            add(new Offer("OFFR002", 7, new Range(50, 150), new Range(100, 250)));
            add(new Offer("OFFR003", 5, new Range(50, 150), new Range(10, 150)));
        }};

        List<OfferRule> offerRules = new ArrayList<>() {{
            add(new OfferDistanceRule());
            add(new OfferWeightRule());
        }};

        OfferCalculator offerCalculator = new OfferCalculator();
        offerCalculator.initializeOffers(offers);
        offerCalculator.initializeRules(offerRules);
```
- **ShipmentFinder**: finds all maximized shipments for the available packages. it uses a dynamic programming(memorization) and java streams to find all subsets of given package set. This class have the logic `Shipment should contain max packages vehicle can carry in a trip.
  We should prefer heavier packages when there are multiple shipments with the same no. of packages.
  If the weights are also the same, preference should be given to the shipment which can be delivered first.`
  
- **DeliveryEstimator**: estimate te delivery time for all shipments based on the vehicle available and delivery time. `Each Vehicle has a limit (L) on maximum weight (kg) that it can carry.
  All Vehicles travel at the same speed (S km/hr) and in the same route. It is assumed that all the destinations
  can be covered in a single route.`
  
- **Models** - CourierPackage, DeliveryEstimate for package details and estimates

- **SubSetGenerator** - stream impl to generates subsets.

### Example
```
        int baseDeliveryCost = 100;
        int noOfVehicles = 2;
        int maxSpeed = 70;
        int maxCarriageWeight = 200;

        List<CourierPackage> packages = new ArrayList<>() {{
            add(new CourierPackage("PCK1", 50, 30, "OFR001"));
            add(new CourierPackage("PCK2", 75, 125, "OFFR0008"));
            add(new CourierPackage("PCK3", 175, 100, "OFFR0008"));
            add(new CourierPackage("PCK4", 110, 60, "OFFR002"));
            add(new CourierPackage("PCK5", 155, 95, "NA"));
        }};

        App app = new App();
        List<Offer> offers = new ArrayList<Offer>() {{
            add(new Offer("OFFR001", 10, new Range(0, 200), new Range(70, 200)));
            add(new Offer("OFFR002", 7, new Range(50, 150), new Range(100, 250)));
            add(new Offer("OFFR003", 5, new Range(50, 150), new Range(10, 150)));
        }};
        app.initializeOffers(offers);

        List<DeliveryEstimate> estimates = app.estimate(packages, baseDeliveryCost, noOfVehicles, maxSpeed, maxCarriageWeight);
```

## unit tests added to support test driven implementation...


