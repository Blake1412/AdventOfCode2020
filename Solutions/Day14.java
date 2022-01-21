import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {
    Map<Integer, Long> addresses = new HashMap<>();
    List<Map<Integer, Long>> commands = new ArrayList<>();
    List<String> bitmasks = new ArrayList<>();

    public Day14() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            Map<Integer, Long> temp = new HashMap<>();
            String st = scan.nextLine();

            while (st.contains("mem")) {
                String address = st.split(" = ")[0];
                Long amount = Long.parseLong(st.split(" = ")[1]);
                temp.put(Integer.parseInt(st.split(" = ")[0].substring(address.indexOf('[') + 1, address.indexOf(']'))), amount);
                if (!scan.hasNextLine()) {
                    break;
                }
                st = scan.nextLine();
            }
            if (temp.size() > 0) {
                commands.add(temp);
            }
            if (!scan.hasNextLine()) {
                break;
            }
            bitmasks.add(st.split(" = ")[1]);
        }
        for (Map<Integer, Long> commandGroups : commands) {
            for (Integer address : commandGroups.keySet()) {
                addresses.put(address, 0L);
            }
        }
    }

    private long applyMask(long number, String mask) {
        StringBuilder numberBinary = new StringBuilder(Long.toBinaryString(number));
        while (numberBinary.length() < mask.length()) {
            numberBinary.insert(0, '0');
        }
        for (int i = 0; i < mask.length(); i++) {
            switch (mask.charAt(i)) {
                case '1' -> numberBinary.replace(i, (i + 1), "1");
                case '0' -> numberBinary.replace(i, (i + 1), "0");
            }
        }
        return Long.parseLong(numberBinary.toString(), 2);
    }

    public long part1() {
        for (int i = 0; i < commands.size(); i++) {
            for (Integer address : commands.get(i).keySet()) {
                addresses.put(address, applyMask(commands.get(i).get(address), bitmasks.get(i)));
            }
        }
        long sum = 0;
        for (Integer address : addresses.keySet()) {
            sum += addresses.get(address);
        }
        return sum;
    }
}
