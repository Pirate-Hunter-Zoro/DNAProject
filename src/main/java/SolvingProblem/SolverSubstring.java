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

        if (backTracker.length > 1) {
            // this algorithm cannot use the traditional traceback
            Pair substringStart = this.obtainStart(backTracker);
            // now construct the alignment
            return this.constructAlignment(substringStart, s1, s2);
        }

        // otherwise, the query was the empty string
        return new ArrayList<>();
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
     * Given a backtracking table, obtain the indices that correspond to the start of the longest common substring between the two strings
     * There should be no need for an outside class to call this method
     * @param backTracker {int[][]}
     * @return {@link Pair}
     */
    private Pair obtainStart(int[][] backTracker){

        // we know this is true because of the solve method
        int maxSubstringLength = backTracker[0][0];

        // traverse along the table until the start of the longest substring is found
        Stack<Pair> pairStack = new Stack<>();
        for (int i=backTracker.length-1; i>0; i--){
            for (int j=backTracker[i].length-1; j>0; j--){
                if (backTracker[i][j]==BACKTRACK_START_INDICATOR){
                    while (pairStack.size() < maxSubstringLength){
                        // keep track of the string indices
                        Pair thePair = new Pair(i-1,j-1);
                        pairStack.push(thePair);
                        i--;
                        j--;
                    }
                }
            }
        }

        return pairStack.pop();

    }

    /**
     * Given the two strings and the starting point in each string of the common substring, construct the alignment
     * @param substringStart
     * @param s1
     * @param s2
     * @return {@link ArrayList<Pair>}
     */
    private ArrayList<Pair> constructAlignment(Pair substringStart, String s1, String s2){
        // now that we have the starting point in each string of the longest common substring, we need to figure out how to line up the strings
        int offset = substringStart.getFirst() - substringStart.getSecond();
        int s1Start = -1;
        int s2Start = -1;
        if (offset > 0){
            s1Start = offset;
            s2Start = 0;
        } else {
            s1Start = 0;
            s2Start = -offset;
        }

        // to contain the pairs of our total alignment
        ArrayList<Pair> pairs = new ArrayList<>();

        // given the starts, line up until we run out of characters in either string
        int s1Idx = s1Start;
        int s2Idx = s2Start;
        while (s1Idx < s1.length() && s2Idx < s2.length()) {
            pairs.add(new Pair(s1Idx, s2Idx));
            s1Idx++;
            s2Idx++;
        }

        // return the list of pairs
        return pairs;

    }

}