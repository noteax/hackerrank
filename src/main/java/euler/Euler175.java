package euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Euler175 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Euler175 euler175 = new Euler175();
        System.out.println(
                euler175.shortenedBinaryExplanation(euler175.getMinDiv(n, m))
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining()));
    }

    public List<Integer> shortenedBinaryExplanation(int value) {
        List<Integer> result = new ArrayList<Integer>();
        int accumulator = 1;
        char[] chars = Integer.toBinaryString(value).toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                result.add(accumulator);
                accumulator = 1;
            } else {
                accumulator++;
            }
        }
        if (chars.length != 0) {
            result.add(accumulator);
        }
        return result;
    }

    public int getMinDiv(int n, int m) {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (numberOfWaysToWriteValue(i, 1) == n && numberOfWaysToWriteValue(i - 1, 1) == m) {
                return i;
            }
        }
        throw new IllegalArgumentException("couldn't express");
    }

    public int numberOfWaysToWriteValue(int val, int minSub) {
        if (val == 0) {
            return 1;
        }
        if(val < minSub) {
            return 0;
        }
        return numberOfWaysToWriteValue(val, minSub * 2) + numberOfWaysToWriteValue(val - minSub, minSub * 2) + numberOfWaysToWriteValue(val - minSub * 2, minSub * 2);
    }

}