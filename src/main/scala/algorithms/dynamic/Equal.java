package algorithms.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class Equal {

    public static void main(String args[]) throws Exception {
        Equal equal = new Equal();

        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < t; i++) {
            scanner.nextLine();
            int[] vals = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            System.out.println(equal.numberOfMoves(vals));
        }
    }

    public int numberOfMoves(int[] vals) {
        int minVal = min(vals);

        int minCount = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            int count = 0;
            for (int i = 0; i < vals.length; i++) {
                int t = vals[i] - (minVal - j);
                count += t / 5;
                count += (t % 5) / 2;
                count += (t % 5) % 2;
            }
            if (count < minCount) {
                minCount = count;
            }
        }
        return minCount;
    }

    private int min(int[] vals) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] < min) {
                min = vals[i];
            }
        }
        return min;
    }

}
