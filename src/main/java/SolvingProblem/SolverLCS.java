package SolvingProblem;

import java.util.*;

public class SolverLCS implements SubsequenceFinder{

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
        // initialization
        int[][] counter = new int[s1.length()+1][s2.length()+1];
        int[][] backTracker = new int[s1.length()+1][s2.length()+1];

        // we only need to start from indices 1 and over
        for (int j=1; j<=s1.length(); j++){
            for (int i=1; i<=s2.length(); i++){
                // check if the characters match
                char c1 = s1.charAt(j-1);
                char c2 = s2.charAt(i-1);
                if (c1 == c2) {
                    counter[j][i] = counter[j - 1][i - 1] + 1;
                    backTracker[j][i] = SubsequenceFinder.UP_LEFT;
                }
                // if not take the better option between top and left
                else{
                    if (counter[j][i-1] >= counter[j-1][i]) {
                        counter[j][i] = counter[j][i-1];
                        backTracker[j][i] = SubsequenceFinder.LEFT;
                    } else {
                        counter[j][i] = counter[j-1][i];
                        backTracker[j][i] = SubsequenceFinder.UP;
                    }
                }
            }
        }

        // return the 2D array
        return backTracker;
    }

}