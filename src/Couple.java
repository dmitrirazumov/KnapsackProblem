public class Couple {

    private Double weight;
    private Double cost;

    Couple(Double weight, Double cost) {
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Weight = " + weight + ", " +
                "Cost = " + cost;
    }

    Double getWeight() {
        return weight;
    }

    Double getCost() {
        return cost;
    }
}
