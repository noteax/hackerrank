package euler;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class Euler175Test {

//    @Test
//    public void testLemmas() throws Exception {
//        try {
//            Euler175 euler175 = new Euler175();
//            long sum = euler175.numberOfWaysToWriteValueNonRec(84);
//            long v1 = euler175.numberOfWaysToWriteValueNonRec(42);
//            long v2 = euler175.numberOfWaysToWriteValueNonRec(41);
//            assertEquals(sum, v1 + v2);
//
//
//            System.out.println(euler175.arcf(17,13));
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    @Test
    public void testTwoWays() throws Exception {
        Euler175 euler175 = new Euler175();
        long v1 = euler175.getMinDiv1(13, 15);
        long v2 = euler175.getMinDiv2(13, 15);
        assertEquals(v1, v2);

        long v3 = euler175.getMinDiv1(18, 25);
        long v4 = euler175.getMinDiv1(36, 50);
        assertEquals(v3, v4);

        long v5 = euler175.getMinDiv1(18, 25);
        long v6 = euler175.getMinDiv2(18, 25);
        assertEquals(v5, v6);
    }

    @Test
    public void testSimple() throws Exception {
        Euler175 euler175 = new Euler175();
        List<Integer> integers = euler175.shortenedBinaryExplanation(euler175.getMinDiv1(13, 17));
        assertEquals(integers, Arrays.asList(4, 3, 1));
    }

    @Test(enabled = false)
    public void testBigNumbers() throws Exception {
        Euler175 euler175 = new Euler175();
        List<Integer> integers = euler175.shortenedBinaryExplanation(euler175.getMinDiv1(123, 13347));
    }

}