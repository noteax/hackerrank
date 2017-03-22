package algorithms.strings;

import java.util.Scanner;

public class MarsExploration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String source = scanner.nextLine();
        System.out.println(new MarsExploration().findAltered(source));
    }

    public int findAltered(String line) {
        char[] original = new char[] {'S', 'O', 'S'};
        int result = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != original[i % 3]) {
                result++;
            }
        }
        return result;
    }

}