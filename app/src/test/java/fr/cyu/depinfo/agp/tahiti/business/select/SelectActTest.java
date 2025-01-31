package fr.cyu.depinfo.agp.tahiti.business.select;

import fr.cyu.depinfo.agp.tahiti.business.locations.Position;
import fr.cyu.depinfo.agp.tahiti.business.locations.Site;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectActTest {

    @Test
    public void testNbActMaxWithValidComfort() {
        SelectAct selectAct = new SelectAct();

        int duration = 2;
        int comfort = 3; // Middle row in the probability matrix
        // nbActMax(...) returns i * duration, where i in {0..3} chosen by random distribution

        int result = selectAct.nbActMax(duration, comfort);
        // The result must be 0, 2, 4, or 6 (because i is in [0..3], multiplied by duration=2)

        assertTrue(result == 0 || result == 2 || result == 4 || result == 6,
                "nbActMax should return a multiple of 'duration' in {0, 2, 4, 6} but got: " + result);
    }

    @Test
    public void testNbActMaxBoundaryComfort() {
        SelectAct selectAct = new SelectAct();

        int duration = 1;

        // If comfort < 1, the code clamps comfort to 1
        int resultLow = selectAct.nbActMax(duration, 0);
        // Probability row used is for comfort=1
        assertTrue(resultLow == 0 || resultLow == 1 || resultLow == 2 || resultLow == 3,
                "nbActMax with comfort=0 should clamp to row=1 => {0,1,2,3}");

        // If comfort > 5, the code clamps comfort to 5
        int resultHigh = selectAct.nbActMax(duration, 999);
        // Probability row used is for comfort=5
        assertTrue(resultHigh == 0 || resultHigh == 1 || resultHigh == 2 || resultHigh == 3,
                "nbActMax with comfort=999 should clamp to row=5 => {0,1,2,3}");
    }

    /*@Test
    public void testSelectActWhenActivitiesAreFewerThanNbActMax() {
        SelectAct selectAct = new SelectAct();

        // Suppose we pick a high comfort => likely a bigger value from nbActMax
        int comfort = 4;
        // We'll cheat to increase the chance that nbActMax is 2 or 3 times the duration
        // but it's still random. The main test is whether we fill if needed.

        int prix = 500;
        int duration = 5;
        List<String> keywords = new ArrayList<>();
        keywords.add("dummy");

        // The getActivities(...) in your code returns 2 dummy activities by default.
        // We want to see if fillAct(...) gets called if nbActMax > 2
        List<Site> activities = new ArrayList<>();
        List<Site> selected = selectAct.selectAct(activities, prix, duration, comfort);

        // The method returns either exactly the random number of activities
        // (and if that is >2, we see filler ones).
        // Let's just check that it's not null and at least 2 in size:
        assertNotNull(selected, "Returned activity list should not be null");
        assertFalse(selected.isEmpty(), "Should return at least one activity");
    }*/
}
