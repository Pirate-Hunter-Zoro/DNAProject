package SolvingProblem;

import java.util.ArrayList;
import java.util.Stack;

public class SolverSubstring implements SubsequenceFinder{

    public final int BACKTRACK_START_INDICATOR = 1;

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

        // this algorithm cannot use the traditional traceback
        return this.obtainPairs(backTracker);
    }

    /**
     * Class method to perform the Bottom-Up algorithm needed to compute the 2D array to backtrack the longest common subsequence of two strings
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link int[][]}
     */
    @Override
    public int[][] solve(String s1, String s2){
        // variable setup
        int maxRecord = 0;
        int oldRecordI = 0;
        int oldRecordJ = 0;
        int[][] records = new int[s1.length()+1][s2.length()+1];
        int[][] backTracker = new int[s1.length()+1][s2.length()+1];

        // traversal through entire table will yield the longest common substring
        for (int i=1; i<=s1.length(); i++){
            char c1 = s1.charAt(i-1);
            for (int j=1; j<=s2.length(); j++){
                if (c1 == s2.charAt(j-1)) {
                    // once we find a match, consider if that breaks a record, and update the backtracking accordingly
                    records[i][j] = records[i - 1][j - 1] + 1;
                    if (records[i][j] > maxRecord){
                        maxRecord = records[i][j];
                        backTracker[oldRecordI][oldRecordJ] = 0;
                        oldRecordJ = j;
                        oldRecordI = i;
                        backTracker[i][j] = BACKTRACK_START_INDICATOR;
                    }
                }
            }
        }

        // we will need to remember the length of the maximum substring
        backTracker[0][0] = maxRecord;

        // return the table for backtracking
        return backTracker;
    }

    /**
     * Given a backtracking table, obtain the indices that correspond to the longest common substring between the two strings that led to this backtracking table
     * @param backTracker {int[][]}
     * @return {@link ArrayList<Pair>}
     */
    public ArrayList<Pair> obtainPairs(int[][] backTracker){
        // to be returned
        ArrayList<Pair> indexPairs = new ArrayList<>();

        // we know this is true because of the solve method
        int maxSubstringLength = backTracker[0][0];

        // traverse along the table until the start of the longest substring is found
        Stack<Pair> pairStack = new Stack<>();
        for (int i=backTracker.length-1; i>0; i--){
            for (int j=backTracker[i].length-1; j>0; j--){
                if (backTracker[i][j]==BACKTRACK_START_INDICATOR){
                    while (pairStack.size() < maxSubstringLength){
                        // keep track of the string indices
                        pairStack.push(new Pair(i-1, j-1));
                        i--;
                        j--;
                    }
                }
            }
        }

        // move from the Stack to the list of pairs
        while (!pairStack.empty())
            indexPairs.add(pairStack.pop());

        // return the list of pairs
        return indexPairs;

    }

}