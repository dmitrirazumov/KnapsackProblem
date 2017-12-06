import java.util.ArrayList;
import java.util.Random;

class KnapsackProblem {

    private Random random = new Random();

    private final static double INITIAL_TEMPERATURE = 100.0;
    private final static double FINAL_TEMPERATURE = 0.5;
    private final static double ALPHA = 0.98;
    private final static int STEPS = 100;

    private final int number;
    private final int capacity;
    private final ArrayList<Couple> weightCost;

    KnapsackProblem(int number, int capacity, ArrayList<Couple> weightCost) {
        this.number = number;
        this.capacity = capacity;
        this.weightCost = weightCost;
    }

    ArrayList<Answer> result() {

        ArrayList<Answer> answers = new ArrayList<>();

        double bestCost;
        int[] answer = new int[number];
        ArrayList<Integer> solution, startSolution;

        startSolution = initSolution(weightCost, capacity);
        solution = simulatedAnnealing(weightCost, startSolution, capacity);
        bestCost = computeCost(weightCost, solution);

        for (Integer element : solution) answer[element] = 1;

        answers.add(new Answer(bestCost, answer));
        return answers;
    }

    private ArrayList<Integer> initSolution(ArrayList<Couple> weightCost, double maxWeight) {

        int index;
        Integer selectedPosition;
        ArrayList<Integer> intermediate = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        ArrayList<Integer> allowedPositions = new ArrayList<>(weightCost.size());

        for (Couple element : weightCost) allowedPositions.add(weightCost.indexOf(element));

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

    private double computeWeight(ArrayList<Couple> weightCost, ArrayList<Integer> solution) {

        double weight = 0.0;

        for (Integer element : solution) {
            weight += weightCost.get(element).getWeight();
        }
        return weight;
    }

    private double computeCost(ArrayList<Couple> weightCost, ArrayList<Integer> solution) {

        double cost = 0.0;

        for (Integer element : solution) {
            cost += weightCost.get(element).getCost();
        }
        return cost;
    }

    private ArrayList<ArrayList<Integer>> tweakSolutions(ArrayList<Couple> weightCost, ArrayList<Integer> solution, double maxWeight) {

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

    private ArrayList<Integer> simulatedAnnealing(ArrayList<Couple> weightCost, ArrayList<Integer> solution, double maxWeight) {

        double temperature = INITIAL_TEMPERATURE;

        int index;
        double currentCost, delta;
        ArrayList<Integer> best = new ArrayList<>(solution);
        ArrayList<Integer> current = new ArrayList<>(solution);
        ArrayList<ArrayList<Integer>> solutions;
        ArrayList<Integer> test;

        double bestCost = computeCost(weightCost, solution);

        do {
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
                    if (Math.exp(delta / temperature) > Math.random()) {
                        current = new ArrayList<>(test);
                    }
                }
            }
            temperature *= ALPHA;
        } while ((temperature > FINAL_TEMPERATURE) || (currentCost < bestCost));

        return best;
    }
}
