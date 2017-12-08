import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        ArrayList<Types.Couple> couples = new ArrayList<>();
        couples.add(new Types.Couple(3, 100));
        couples.add(new Types.Couple(4, 100));
        couples.add(new Types.Couple(7, 100));

        /*Выводит следующий ответ:
        Best cost = 200, combination: [1, 1, 0]
        или
        Best cost = 200, combination: [1, 0, 1]*/
        System.out.println(new SimulatedAnnealing(3, 10, couples).resultSA());
    }
}
