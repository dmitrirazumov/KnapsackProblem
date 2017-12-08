import java.util.ArrayList;

class GreedyAlgorithm {

    private final int capacity;
    private final ArrayList<Types.Couple> weightCost;

    GreedyAlgorithm(int capacity, ArrayList<Types.Couple> weightCost) {
        this.capacity = capacity;
        this.weightCost = weightCost;
    }

    int resultGR() {

        ArrayList<Types.UnitCost> solution;

        solution = sortUnitCost(weightCost);

        int bestCost = 0;
        int weight = 0;

        for (Types.UnitCost element : solution) {
            if (weightCost.get(element.getIndex()).getWeight() + weight <= capacity) {
                weight += weightCost.get(element.getIndex()).getWeight();
                bestCost += weightCost.get(element.getIndex()).getCost();
            }
        }
        return bestCost;
    }

    private ArrayList<Types.UnitCost> sortUnitCost(ArrayList<Types.Couple> weightCost) {

        ArrayList<Types.UnitCost> solution = new ArrayList<>();

        for (Types.Couple element : weightCost) {
            solution.add(new Types.UnitCost(weightCost.indexOf(element), ((double) element.getCost() / element.getWeight())));
        }
        solution.sort((o1, o2) -> o2.getUnitCost() < o1.getUnitCost() ? -1 : o2.getUnitCost() == o1.getUnitCost() ? 0 : 1);

        return solution;
    }
}
