package algorithms.strings;

import java.util.Scanner;

public class HackerRankInAString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HackerRankInAString hackerRankInAString = new HackerRankInAString();
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            String source = scanner.next();
            System.out.println(hackerRankInAString.containsHackerRank(source) ? "YES" : "NO");
        }
    }

    public boolean containsHackerRank(String line) {
        char[] original = "hackerrank".toCharArray();
        int index = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == original[index]) {
                index++;
            }
            if (index == original.length) {
                return true;
            }
        }
        return false;
    }
}