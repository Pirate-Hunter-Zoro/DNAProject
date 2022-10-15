package SolvingProblem;

import java.util.ArrayList;
import java.util.Stack;

public class SolverNeedlemanWunsch implements SubsequenceFinder {
    // source - https://www.youtube.com/watch?v=b6xBvl0yPAY&list=WL&index=1

    /**
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
    @Override
    public ArrayList<Pair> getLineUp(String s1, String s2){
        // fill out a 2D array containing all the backtracking necessary to reconstruct the longest common subsequence of the two strings
        int[][] backTracker = this.solve(s1, s2);

        // call the static List of Pairs constructor
        return SubsequenceFinder.findSubsequencePositions(backTracker, s1, s2);
    }

    /**
     * Class method to perform the Bottom-Up algorithm needed to compute the 2D array to backtrack the longest common subsequence of two strings
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link int[][]}
     */
    @Override
    public int[][] solve(String s1, String s2){
        return null;
    }

}