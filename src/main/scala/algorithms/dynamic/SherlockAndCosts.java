package algorithms.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class SherlockAndCosts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < k; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            int[] items = Arrays.stream(scanner.nextLine().split("\\s"))
                    .mapToInt(Integer::parseInt).toArray();

            int prevLow = 0;
            int prevHigh = 0;
            for (int j = 1; j < n; j++) {
                int currentLow = Math.max(prevLow, prevHigh + Math.abs(items[j - 1] - 1));
                int currentHigh = Math.max(prevHigh + Math.abs(items[j - 1] - items[j]),
                        prevLow + Math.abs(items[j] - 1));

                prevLow = currentLow;
                prevHigh = currentHigh;
            }
            System.out.println(Math.max(prevHigh, prevLow));
        }

    }
}
