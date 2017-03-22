package algorithms.strings;

import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        String source = scanner.nextLine();
        int key = scanner.nextInt();

        CaesarCipher caesarCipher = new CaesarCipher();
        System.out.println(caesarCipher.encode(source, key));
    }

    public String encode(String source, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : source.toCharArray()) {
            result.append(rotate(c, key));
        }
        return result.toString();
    }

    private char rotate(char c, int count) {
        int alphSize = 'Z' - 'A' + 1;
        int actualCount = count % alphSize;

        if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            char newValue = (char) (c + actualCount);
            if (c <= 'Z' && newValue > 'Z' || c <= 'z' && newValue > 'z') {
                return (char) (newValue - alphSize);
            }
            return newValue;
        }
        return c;
    }
}
