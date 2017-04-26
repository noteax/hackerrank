package euler;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class Euler175Test {

    @Test
    public void testSimple() throws Exception {
        try {
            Euler175 euler175 = new Euler175();
            List<Integer> integers = euler175.shortenedBinaryExplanation(euler175.getMinDiv(13, 17));
            assertEquals(integers, Arrays.asList(4, 3, 1));
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void testBigNumbers() throws Exception {
        try {
            Euler175 euler175 = new Euler175();
            List<Integer> integers = euler175.shortenedBinaryExplanation(euler175.getMinDiv(123, 13347));
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

}