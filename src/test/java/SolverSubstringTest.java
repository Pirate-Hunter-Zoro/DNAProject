import SolvingProblem.Pair;
import SolvingProblem.SolverSubstring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolverSubstringTest {

    /** Strings to find subsequence of */
    static String s1 = "DCDFB";
    static String s2 = "AABCDFA";

    /** Our actual solver */
    SolverSubstring solver;

    /** To enable easy emplacement into the expected list of Pairs */
    static int[] s1Indices = {1,2,3};
    static int[] s2Indices = {3,4,5};

    /** Lists of Pairs containing the indices to use for each String to create the longest common subsequence */
    static ArrayList<Pair> expected = new ArrayList<>();
    static ArrayList<Pair> expectedReverse = new ArrayList<>();

    /** Pre-test String setup */
    @BeforeEach
    void setUp() {
        for (int k=0; k< s1Indices.length; k++){
            expected.add(new Pair(s1Indices[k], s2Indices[k]));
            expectedReverse.add(new Pair(s2Indices[k], s1Indices[k]));
        }
        solver = new SolverSubstring();
    }

    /** A test to ensure that Solver's getLineUp method returns the expected list of Pairs */
    @Test
    void findSubsequencePositions() {
        ArrayList<Pair> actual = solver.getLineUp(s1, s2);
        assertEquals(expected, actual);

        ArrayList<Pair> actualReverse = solver.getLineUp(s2, s1);
        assertEquals(expectedReverse, actualReverse);
    }

}