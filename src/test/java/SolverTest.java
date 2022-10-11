import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    /** Strings to find subsequence of */
    static String s1;
    static String s2;

    /** To enable easy emplacement into the expected list of Pairs */
    static int[] s1Indices = {0,1,2,3,4};
    static int[] s2Indices = {0,1,13,14,16};

    /** Lists of Pairs containing the indices to use for each String to create the longest common subsequence */
    static ArrayList<Pair> expected = new ArrayList<>();
    static ArrayList<Pair> expectedReverse = new ArrayList<>();

    /** Pre-test String setup */
    @BeforeEach
    void setUp() {
        s1 = "CGACT";
        s2 = "CGGGGTTTGGGGGACAT";
        for (int k=0; k< s1Indices.length; k++){
            expected.add(new Pair(s1Indices[k], s2Indices[k]));
            expectedReverse.add(new Pair(s2Indices[k], s1Indices[k]));
        }
    }

    /** A test to ensure that Solver's findSubsequencePositions static method returns the expected list of Pairs */
    @Test
    void findSubsequencePositions() {
        assertEquals(expected, Solver.findSubsequencePositions(s1, s2));
        assertEquals(expectedReverse, Solver.findSubsequencePositions(s2, s1));
    }
}