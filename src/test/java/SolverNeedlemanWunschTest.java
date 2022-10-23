import SolvingProblem.Pair;
import SolvingProblem.SolverNeedlemanWunsch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolverNeedlemanWunschTest {
    // source for test cases: https://www.cs.sjsu.edu/~aid/cs152/NeedlemanWunsch.pdf

    /** Strings to find subsequence of */
    static String s1 = "CACATA";
    static String s2 = "CAGCTA";

    static String s3 = "SIMILARITY";
    static String s4 = "PILLAR";

    static String s5 = "ATGGCGT";
    static String s6 = "ATGAGT";

    /** Our actual solver */
    SolverNeedlemanWunsch solver;

    /** To enable easy emplacement into the expected list of Pairs */
    static int[] s1Indices = {0,1,2,4,5};
    static int[] s2Indices = {0,1,3,4,5};

    static int[] s3Indices = {0,1,3,4,5,6};
    static int[] s4Indices = {0,1,2,3,4,5};

    static int[] s5Indices = {0,1,2,4,5,6};
    static int[] s5IndicesSecondPossibility = {0,1,3,4,5,6}; // same gap either way
    static int[] s6Indices = {0,1,2,3,4,5};

    /** Lists of Pairs containing the indices to use for each String to create the longest common subsequence */
    static ArrayList<Pair> expected1 = new ArrayList<>();
    static ArrayList<Pair> expectedReverse1 = new ArrayList<>();

    static ArrayList<Pair> expected2 = new ArrayList<>();
    static ArrayList<Pair> expectedReverse2 = new ArrayList<>();

    static ArrayList<Pair> expected3 = new ArrayList<>();
    static ArrayList<Pair> expected3SecondPossibility = new ArrayList<>();
    static ArrayList<Pair> expectedReverse3 = new ArrayList<>();
    static ArrayList<Pair> expectedReverse3SecondPossibility = new ArrayList<>();


    /** Pre-test String setup */
    @BeforeEach
    void setUp() {
        for (int k=0; k< s1Indices.length; k++){
            expected1.add(new Pair(s1Indices[k], s2Indices[k]));
            expectedReverse1.add(new Pair(s2Indices[k], s1Indices[k]));
        }
        for (int k=0; k< s3Indices.length; k++){
            expected2.add(new Pair(s3Indices[k], s4Indices[k]));
            expectedReverse2.add(new Pair(s4Indices[k], s3Indices[k]));
        }
        for (int k=0; k< s5Indices.length; k++){
            expected3.add(new Pair(s5Indices[k], s6Indices[k]));
            expectedReverse3.add(new Pair(s6Indices[k], s5Indices[k]));
            expected3SecondPossibility.add(new Pair(s5IndicesSecondPossibility[k], s6Indices[k]));
            expectedReverse3SecondPossibility.add(new Pair(s6Indices[k], s5IndicesSecondPossibility[k]));
        }
        solver = new SolverNeedlemanWunsch();
    }

    /** A test to ensure that Solver's getLineUp method returns the expected list of Pairs */
    @Test
    void findSubsequencePositions() {
        ArrayList<Pair> actual1 = solver.getLineUp(s1, s2);
        assertEquals(expected1, actual1);

        ArrayList<Pair> actualReverse1 = solver.getLineUp(s2, s1);
        assertEquals(expectedReverse1, actualReverse1);

        ///////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Pair> actual2 = solver.getLineUp(s3,s4);
        assertEquals(expected2, actual2);

        ArrayList<Pair> actualReverse2 = solver.getLineUp(s4,s3);
        assertEquals(expectedReverse2, actualReverse2);

        ///////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Pair> actual3 = solver.getLineUp(s5,s6);
        assertTrue(expected3.equals(actual3) || expected3SecondPossibility.equals(actual3));

        ArrayList<Pair> actualReverse3 = solver.getLineUp(s6,s5);
        assertTrue(expectedReverse3.equals(actualReverse3) || expectedReverse3SecondPossibility.equals(actualReverse3));

        ///////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Pair> actual4 = solver.getLineUp("","DUMMY");
        assertEquals(new ArrayList<>(), actual4);

        ArrayList<Pair> actualReverse4 = solver.getLineUp("DUMMY","");
        assertEquals(new ArrayList<>(), actualReverse4);

        assertEquals(new ArrayList<>(), solver.getLineUp("",""));


    }

}