import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {
    int arrivalTime = 1003240;
    List<Integer> busIntervals;

    Day13() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        busIntervals = new ArrayList<>(Arrays.stream(scan.nextLine().split(",")).filter(x -> x.matches("\\d+")).mapToInt(Integer::parseInt).boxed().toList());

    }

    public int part1() {
        int busArrivalTime = busIntervals.stream().map(i -> (arrivalTime / i + 1) * i).reduce(Integer::min).orElse(0);
        int busID = busIntervals.stream().filter(x -> busArrivalTime % x == 0).findFirst().orElse(0);
        return busID * (busArrivalTime - arrivalTime);
    }
}
