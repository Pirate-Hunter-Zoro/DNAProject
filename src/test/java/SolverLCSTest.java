import FileScanner.MatchMaker;
import SolvingProblem.Pair;
import SolvingProblem.SolverLCS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolverLCSTest {

    /** Strings to find subsequence of */
    static String s1 = "CACATA";
    static String s2 = "CAGCTA";

    static String s3 = "SIMILARITY";
    static String s4 = "PILLAR";

    static String s5 = "ATGGCGT";
    static String s6 = "ATGAGT";

    /** Our actual solver */
    SolverLCS solver;

    /** Lists of Pairs containing the indices to use for each String to create the longest common subsequence */
    static String expected1 = "CACTA";

    static String expected2 = "ILAR";

    static String expected3 = "ATGGT";

    /** Pre-test String setup */
    @BeforeEach
    void setUp() {
        solver = new SolverLCS();
    }

    /** A test to ensure that Solver's getLineUp method returns the expected list of Pairs */
    @Test
    void findSubsequencePositions() {
        assertEquals(expected1, new MatchMaker(s1,s2,solver.getLineUp(s1,s2)).makeMatch());

        ///////////////////////////////////////////////////////////////////////////////////////

        assertEquals(expected2, new MatchMaker(s3,s4,solver.getLineUp(s3,s4)).makeMatch());

        ///////////////////////////////////////////////////////////////////////////////////////

        assertEquals(expected3, new MatchMaker(s5,s6,solver.getLineUp(s5,s6)).makeMatch());

        ///////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Pair> actual4 = solver.getLineUp("","DUMMY");
        assertEquals(new ArrayList<>(), actual4);

        ArrayList<Pair> actualReverse4 = solver.getLineUp("DUMMY","");
        assertEquals(new ArrayList<>(), actualReverse4);

        assertEquals(new ArrayList<>(), solver.getLineUp("",""));

    }
}