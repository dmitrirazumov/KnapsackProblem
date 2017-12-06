import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        ArrayList<Couple> weightCost = new ArrayList<>();
        weightCost.add(new Couple(27.0, 38.0));
        weightCost.add(new Couple(2.0, 86.0));
        weightCost.add(new Couple(41.0, 112.0));
        weightCost.add(new Couple(1.0, 0.0));
        weightCost.add(new Couple(25.0, 66.0));
        weightCost.add(new Couple(1.0, 97.0));
        weightCost.add(new Couple(34.0, 195.0));
        weightCost.add(new Couple(3.0, 85.0));
        weightCost.add(new Couple(50.0, 42.0));
        weightCost.add(new Couple(12.0, 223.0));


        System.out.println(new KnapsackProblem(10, 100, weightCost).result());
    }
}
