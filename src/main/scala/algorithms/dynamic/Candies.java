package algorithms.dynamic;

import java.util.Scanner;

public class Candies {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(scanner.nextLine());
        }

        long[] candies = new long[n];
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            if (items[i - 1] < items[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (items[i - 1] > items[i]) {
                candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
            }
        }
        long s = 0;
        for (int i = 0; i < n; i++) {
            s += candies[i];
        }
        System.out.println(s);
    }
}
