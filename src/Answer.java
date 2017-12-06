import java.util.Arrays;

public class Answer {

    private Double bestCost;
    private int[] combination;

    Answer(Double bestCost, int[] combination) {
        this.combination = combination;
        this.bestCost = bestCost;
    }

    @Override
    public String toString() {
        return "Best cost = " + bestCost + ", " +
                "combination: " + Arrays.toString(combination);
    }
}
