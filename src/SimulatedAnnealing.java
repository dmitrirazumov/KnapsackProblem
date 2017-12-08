import java.util.ArrayList;
import java.util.Random;

class SimulatedAnnealing {

    private Random random = new Random();

    private final static double INITIAL_TEMPERATURE = 100.0;
    private final static double FINAL_TEMPERATURE = 0.5;
    private final static double ALPHA = 0.85;
    private final static int STEPS = 100;

    private final int number;
    private final int capacity;
    private final ArrayList<Types.Couple> weightCost;

    SimulatedAnnealing(int number, int capacity, ArrayList<Types.Couple> weightCost) {
        this.number = number;
        this.capacity = capacity;
        this.weightCost = weightCost;
    }

    ArrayList<Types.Answer> resultSA() {

        ArrayList<Types.Answer> answers = new ArrayList<>();

        int bestCost;
        int[] answer = new int[number];
        ArrayList<Integer> solution, startSolution;

        startSolution = initSolution(weightCost, capacity);
        solution = simulation(weightCost, startSolution, capacity);
        bestCost = computeCost(weightCost, solution);

        for (Integer element : solution) answer[element] = 1;

        answers.add(new Types.Answer(bestCost, answer));
        return answers;
    }

    private ArrayList<Integer> initSolution(ArrayList<Types.Couple> weightCost, int maxWeight) {

        int index;
        Integer selectedPosition;
        ArrayList<Integer> intermediate = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        ArrayList<Integer> allowedPositions = new ArrayList<>(weightCost.size());

        for (Types.Couple element : weightCost) allowedPositions.add(weightCost.indexOf(element));

        while (allowedPositions.size() > 0) {
            index = random.nextInt(allowedPositions.size());
            selectedPosition = allowedPositions.remove(index);
            intermediate.add(selectedPosition);
            if (computeWeight(weightCost, intermediate) <= maxWeight) {
                solution.add(selectedPosition);
            } else break;
        }
        return solution;
    }

    private int computeWeight(ArrayList<Types.Couple> weightCost, ArrayList<Integer> solution) {

        int weight = 0;

        for (Integer element : solution) {
            weight += weightCost.get(element).getWeight();
        }
        return weight;
    }

    private int computeCost(ArrayList<Types.Couple> weightCost, ArrayList<Integer> solution) {

        int cost = 0;

        for (Integer element : solution) {
            cost += weightCost.get(element).getCost();
        }
        return cost;
    }

    private ArrayList<ArrayList<Integer>> tweakSolutions(ArrayList<Types.Couple> weightCost, ArrayList<Integer> solution, int maxWeight) {

        ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
        for (int i = 0; i < weightCost.size(); i++) {
            if (!solution.contains(i)) {
                ArrayList<Integer> member = new ArrayList<>(solution);
                member.add(i);
                if (computeWeight(weightCost, member) <= maxWeight) {
                    solutions.add(new ArrayList<>(member));
                }
            }
        }
        for (int i = 0; i < solution.size(); i++) {
            ArrayList<Integer> member = new ArrayList<>(solution);
            member.remove(i);
            if (!solutions.contains(member)) {
                solutions.add(new ArrayList<>(member));
            }
        }
        return solutions;
    }

    private ArrayList<Integer> simulation(ArrayList<Types.Couple> weightCost, ArrayList<Integer> solution, int maxWeight) {

        double temperature = INITIAL_TEMPERATURE;

        int index;
        int currentCost, delta;
        ArrayList<Integer> best = new ArrayList<>(solution);
        ArrayList<Integer> current = new ArrayList<>(solution);
        ArrayList<ArrayList<Integer>> solutions;
        ArrayList<Integer> test;

        int bestCost = computeCost(weightCost, solution);

        while(true) {
            currentCost = computeCost(weightCost, best);
            for (int i = 0; i < STEPS; i++) {
                solutions = tweakSolutions(weightCost, current, maxWeight);
                index = random.nextInt(solutions.size());
                test = solutions.get(index);
                delta = computeCost(weightCost, test) - bestCost;
                if (delta > 0) {
                    best = new ArrayList<>(test);
                    bestCost = computeCost(weightCost, best);
                    current = new ArrayList<>(test);
                } else {
                    if (Math.exp(-delta / temperature) > Math.random()) {
                        current = new ArrayList<>(test);
                    }
                }
            }
            temperature *= ALPHA;
            if ((temperature <= FINAL_TEMPERATURE) || (currentCost >= bestCost)) break;
        }

        return best;
    }
}
