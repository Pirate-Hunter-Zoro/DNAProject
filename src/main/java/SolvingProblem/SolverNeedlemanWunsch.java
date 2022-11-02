package SolvingProblem;

import java.util.ArrayList;

public class SolverNeedlemanWunsch implements SubsequenceFinder {
    // source - https://www.youtube.com/watch?v=ipp-pNRIp4g

    // Come back and change this class depending on what you determine GAP, MATCH, and MISMATCH values should be
    public static final int GAP_PUNISHMENT = -2;
    public static final int MATCH_REWARD = 1;
    public static final int MISMATCH_PUNISHMENT = -1;

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
        return SubsequenceFinder.findSubsequencePositions(backTracker);
    }

    /**
     * Class method to perform the Bottom-Up algorithm needed to compute the 2D array to backtrack the longest common subsequence of two strings
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link int[][]}
     */
    @Override
    public int[][] solve(String s1, String s2){
        // create empty table
        int[][] scores = new int[s1.length()+1][s2.length()+1];
        int[][] backTracker = new int[s1.length()+1][s2.length()+1];

        // start with base cases
        for (int i=0; i<=s1.length(); i++){
            scores[i][0] = GAP_PUNISHMENT * i;
        }
        for (int j=0; j<=s2.length(); j++){
            scores[0][j] = GAP_PUNISHMENT * j;
        }

        // perform the bottom up traversal
        for (int i=1; i<scores.length; i++){
            for (int j=1; j<scores[i].length; j++){

                // from top left
                int candidate0 = scores[i-1][j-1];
                // do strings match at this position (reward)?
                if (s1.charAt(i-1)==s2.charAt(j-1))
                    candidate0 += MATCH_REWARD;
                else{ // then this choice corresponds with a mismatch alignment
                    candidate0 += MISMATCH_PUNISHMENT;
                }

                // the other two choices are to just consider a gap in one of the two strings

                // from directly left
                int candidate1 = scores[i][j-1] + GAP_PUNISHMENT; // automatically a gap in s2

                // from directly above
                int candidate2 = scores[i-1][j] + GAP_PUNISHMENT; // automatically a gap in s1

                // find best, record, and add to traceBack
                int best = Math.max(candidate0, Math.max(candidate1, candidate2));
                scores[i][j] = best;
                if (best == candidate0)
                    backTracker[i][j] = SubsequenceFinder.UP_LEFT;
                else if (best == candidate1)
                    backTracker[i][j] = SubsequenceFinder.LEFT;
                else
                    backTracker[i][j] = SubsequenceFinder.UP;

            }
        }

        // return the means to backTrack to get the original solution
        return backTracker;
    }

}