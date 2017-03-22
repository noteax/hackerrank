package algorithms.strings;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MarsExplorationTest {

    @Test
    public void testFindAltered() throws Exception {
        MarsExploration marsExploration = new MarsExploration();
        assertEquals(marsExploration.findAltered("SOSSPSSQSSOR"), 3);
        assertEquals(marsExploration.findAltered("SOSSOT"), 1);
    }

}