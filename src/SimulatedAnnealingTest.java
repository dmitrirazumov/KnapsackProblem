import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SimulatedAnnealingTest {

    private Random random = new Random();

    @Test
    public void test1() {

        ArrayList<Types.Answer> solution;

        ArrayList<Types.Couple> couples = new ArrayList<>();
        couples.add(new Types.Couple(3, 100));
        couples.add(new Types.Couple(5, 100));
        couples.add(new Types.Couple(10, 100));

        solution = new SimulatedAnnealing(3, 10, couples).resultSA();

        assertEquals(200, solution.get(0).getBestCost());
    }

    @Test
    public void test2() {

        ArrayList<Types.Answer> solution;

        ArrayList<Types.Couple> couples = new ArrayList<>();
        couples.add(new Types.Couple(8, 38));
        couples.add(new Types.Couple(2, 86));
        couples.add(new Types.Couple(4, 110));
        couples.add(new Types.Couple(3, 52));
        couples.add(new Types.Couple(1, 66));


        solution = new SimulatedAnnealing(5, 15, couples).resultSA();

        assertEquals(314, solution.get(0).getBestCost());

    }

    @Test
    public void test3() {

        ArrayList<Types.Answer> solution;

        ArrayList<Types.Couple> couples = new ArrayList<>();
        couples.add(new Types.Couple(2, 51));
        couples.add(new Types.Couple(12, 44));
        couples.add(new Types.Couple(3, 108));
        couples.add(new Types.Couple(40, 9));
        couples.add(new Types.Couple(1, 86));
        couples.add(new Types.Couple(24, 213));
        couples.add(new Types.Couple(9, 36));
        couples.add(new Types.Couple(17, 218));
        couples.add(new Types.Couple(66, 47));
        couples.add(new Types.Couple(5, 186));

        solution = new SimulatedAnnealing(10, 100, couples).resultSA();

        assertEquals(942, solution.get(0).getBestCost());

    }

    @Test
    public void test4() {

        int number = 100;

        int lowWeight = 1;
        int highWeight = 1000;

        int winsSA = 0;
        int winsGR = 0;

        for (int i = 0; i < 10; i++) {

            ArrayList<Types.Couple> couples = new ArrayList<>(number);

            for (int j = 0; j < number; j++) {
                couples.add(new Types.Couple(random.nextInt((highWeight - lowWeight) + lowWeight), random.nextInt(200)));
            }

            int costSA = new SimulatedAnnealing(number, 100, couples).resultSA().get(0).getBestCost();
            int costGR = new GreedyAlgorithm(100, couples).resultGR();

            if (costSA >= costGR) winsSA++;
            else winsGR++;
        }

        assertTrue(winsSA > winsGR);
    }
}
