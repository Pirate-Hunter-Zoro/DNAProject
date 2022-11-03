package SolvingProblem;

import java.util.ArrayList;
import java.util.Stack;

/** To specify what method all three of our algorithm classes must include */
public interface SubsequenceFinder {

    int UP_LEFT = 0;
    int LEFT = 1;
    int UP = 2;

    /**
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * All classes which implement SubsequenceFinder must implement this method
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
     ArrayList<Pair> getLineUp(String s1, String s2);

    /**
     * Actual implementation to create a table out of two strings, required by all sequence aligner algorithms in this project
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link int[][]}
     */
     int[][] solve(String s1, String s2);

    /**
     * Required method for implementation - reconstruct the solution
     * @param backTracker
     * @param s1
     * @param s2
     * @return {@link Stack<Pair>}
     */
     Stack<Pair> traceBack(int[][] backTracker, String s1, String s2);

    /**
     * All implementors of this Interphase have access to this method
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * @param result {@link Stack<Pair>}
     * @return {@link ArrayList<Pair>}
     */
    static ArrayList<Pair> findSolutionPositions(Stack<Pair> result){

        // reconstruct the respective indices within s1 and s2 that create the common String
        ArrayList<Pair> pairList = new ArrayList<>();

        // unwind the stack into a list of Pairs
        while (!result.isEmpty())
            pairList.add(result.pop());

        // return the list of Pairs
        return pairList;

    }

}
