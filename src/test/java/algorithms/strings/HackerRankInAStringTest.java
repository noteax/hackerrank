package algorithms.strings;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HackerRankInAStringTest {

    @Test
    public void testContainsHackerRank() throws Exception {
        HackerRankInAString hackerRankInAString = new HackerRankInAString();
        assertTrue(hackerRankInAString.containsHackerRank("hereiamstackerrank"));
        assertFalse(hackerRankInAString.containsHackerRank("hackerworld"));
    }

}