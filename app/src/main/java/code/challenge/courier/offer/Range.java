package code.challenge.courier.offer;

public class Range {

    private final double min;
    private final double max;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean isValueInRange(double value) {
        return value >= min && value <= max;
    }

}