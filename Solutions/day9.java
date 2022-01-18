import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day9 {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Long> inputlist = new ArrayList<Long>();
        File fileObject = new File("data.txt");
        Scanner sc = new Scanner(fileObject);
        long keyNumber = 0;

        while (sc.hasNextLong()) {
            inputlist.add(sc.nextLong());
        }

        for (int i = 25; i < inputlist.size(); i++) {
            if (!checkValid(inputlist, i, 25)) {
                keyNumber = inputlist.get(i);
                System.out.println("Weird number is " + keyNumber);
                break;
            }
        }

        ArrayList<Long> sumList = new ArrayList<Long>();

        boolean exit = false;

        for (int i = 0; i < inputlist.size(); i++) {

            //Empty the arrayList
            for (int j = sumList.size() - 1; j >= 0; j--) {
                sumList.remove(j);
            }
            sumList.add(inputlist.get(i));

            for (int k = i + 1; k < inputlist.size(); k++) {
                sumList.add(inputlist.get(k));
                if (sumArray(sumList) == keyNumber) {
                    exit = true;
                    break;
                }
            }

            if (exit == true) {
                break;
            }
        }

        long part2result = findBiggest(sumList) + findSmallest(sumList);
        System.out.println("Weakness is " + part2result);
    }

    private static boolean checkValid(ArrayList<Long> listOfNumbers, int numberToCheck, int preamble) {
        for (int i = numberToCheck - preamble; i < numberToCheck - 1; i++) {
            for (int j = numberToCheck - preamble + 1; j < numberToCheck; j++) {
                if (listOfNumbers.get(i) + listOfNumbers.get(j) == listOfNumbers.get(numberToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static long sumArray(ArrayList<Long> listOfNumbers) {
        long sum = 0;
        for (int i = 0; i < listOfNumbers.size(); i++) {
            sum += listOfNumbers.get(i);
        }
        return sum;
    }

    private static long findBiggest(ArrayList<Long> listOfNumbers) {
        long biggest = 0;
        for (int i = 0; i < listOfNumbers.size(); i++) {
            if (listOfNumbers.get(i) > biggest) {
                biggest = listOfNumbers.get(i);
            }
        }
        return biggest;
    }

    private static long findSmallest(ArrayList<Long> listOfNumbers) {
        long smallest = 1999999999;
        for (int i = 0; i < listOfNumbers.size(); i++) {
            if (listOfNumbers.get(i) < smallest) {
                smallest = listOfNumbers.get(i);
            }
        }
        return smallest;
    }
}

