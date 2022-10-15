package SolvingProblem;

import java.util.ArrayList;
import java.util.Stack;

public class SolverSubstring implements SubsequenceFinder{

    /**
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
    @Override
    public ArrayList<Pair> findSubsequencePositions(String s1, String s2) {
        return null;
    }

    /**
     * Method that, given a String for reference, backtracks along a table to construct the longest common subsequence between the given String and whatever other String produced the table
     * @param backTracker {@link int[][]}
     * @param s1 {@link String}
     * @return {@link Stack<Character>}
     */
    @Override
    public Stack<Character> traceBack(int[][] backTracker, String s1) {
        return null;
    }
}