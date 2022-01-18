import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
    Map<String, String> bags = new HashMap<>();

    public Day07() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            String st = scan.nextLine();
            bags.put(st.split("s contain ")[0], st.split("s contain ")[1]);
        }
    }

    public int part1() {
        List<String> containsShinyGold = new ArrayList<>(List.of("shiny gold bag"));

        for (int i = 0; i < containsShinyGold.size(); i++) {
            for (String outerBag : bags.keySet()) {
                if (bags.get(outerBag).contains(containsShinyGold.get(i))) {
                    if (!containsShinyGold.contains(outerBag)) {
                        containsShinyGold.add(outerBag);
                    }
                }
            }
        }
        return containsShinyGold.size() - 1;
    }
}
