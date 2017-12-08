import java.util.Arrays;

class Types {

    static class Couple {

        private int weight;
        private int cost;

        Couple(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Weight = " + weight + ", " +
                    "Cost = " + cost + ";";
        }

        int getWeight() {
            return weight;
        }

        int getCost() {
            return cost;
        }
    }

    static class Answer {

        private int bestCost;
        private int[] combination;

        Answer(int bestCost, int[] combination) {
            this.bestCost = bestCost;
            this.combination = combination;
        }

        @Override
        public String toString() {
            return "Best cost = " + bestCost + ", " +
                    "combination: " + Arrays.toString(combination);
        }

        int getBestCost() {
            return bestCost;
        }
    }

    static class UnitCost {

        private int index;
        private double unitCost;

        UnitCost(int index, double unitCost) {
            this.index = index;
            this.unitCost = unitCost;
        }

        int getIndex() {
            return index;
        }

        double getUnitCost() {
            return unitCost;
        }
    }
}
