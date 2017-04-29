package euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Euler175 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextLong();
        long m = scanner.nextLong();

        Euler175 euler175 = new Euler175();
        System.out.println(
                euler175.shortenedBinaryExplanation(euler175.getMinDiv1(n, m))
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(",")));
    }

    public List<Integer> shortenedBinaryExplanation(long value) {
        List<Integer> result = new ArrayList<>();
        int accumulator = 1;
        char[] chars = Long.toBinaryString(value).toCharArray();
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

    // --------

    // algorithm from http://2000clicks.com/mathhelp/BasicRecurrenceRelationsSumsOfPowersOfTwo.aspx
    public long getMinDiv1(long y, long x) {
        long result = 0;
        if (x < 1 || y < 1) {
            throw new RuntimeException("Less than 1");
        }
        long z = 1;
        while (x != 0) {
            if (x >= y) {
                x = x - y;
                result = result + z;
            } else {
                y = y - x;
            }
            z = z * 2;
        }
        return result;
    }



    // way 2

    public long getMinDiv2(long n, long m) {
        for (long i = 1; i < Long.MAX_VALUE; i++) {
            if (numberOfWaysToWriteValueNonRec(i) == n && numberOfWaysToWriteValueNonRec(i - 1) == m) {
                return i;
            }
        }
        throw new IllegalArgumentException("couldn't express");
    }

    private long numberOfWaysToWriteValue(long val, long minSub) {
        if (val == 0) {
            return 1;
        }
        if (val < minSub) {
            return 0;
        }
        return numberOfWaysToWriteValue(val, minSub * 2)
                + numberOfWaysToWriteValue(val - minSub, minSub * 2)
                + numberOfWaysToWriteValue(val - minSub * 2, minSub * 2);
    }

    private long numberOfWaysToWriteValueNonRec(long val) {
        long numberOfWays = 0;

        Stack<Val> vals = new Stack<>();
        vals.add(new Val(val, 1));

        while (!vals.isEmpty()) {
            Val currentVal = vals.pop();

            if (currentVal.n == 0) {
                numberOfWays++;
            }

            if (currentVal.n >= currentVal.m) {
                vals.push(new Val(currentVal.n, currentVal.m * 2));
                vals.push(new Val(currentVal.n - currentVal.m, currentVal.m * 2));
                vals.push(new Val(currentVal.n - currentVal.m * 2, currentVal.m * 2));
            }
        }
        return numberOfWays;
    }

    private static class Val {
        long n;
        long m;

        Val(long n, long m) {
            this.n = n;
            this.m = m;
        }
    }

}