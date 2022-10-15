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
    public ArrayList<Pair> findSubsequencePositions(String s1, String s2){
        // fill out a 2D array containing all the backtracking necessary to reconstruct the longest common subsequence of the two strings
        int[][] backTracker = LCS(s1, s2);

        // now progress in order from start to finish to reconstruct the longest common subsequence
        Stack<Character> subsequence = this.traceBack(backTracker, s1);

        // unwind the stack to get the longest common subsequence
        String lCS = "";
        while (!subsequence.isEmpty())
            lCS += subsequence.pop();

        // to be filled up after we have the 2D back-tracking array
        ArrayList<Pair> pairs = SubsequenceFinder.createPairList(lCS, s1 ,s2);

        // the ArrayList of Pairs
        return pairs;
    }

    /**
     * Method that, given a String for reference, backtracks along a table to construct the longest common subsequence between the given String and whatever other String produced the table
     * @param backTracker {@link int[][]}
     * @param s1 {@link String}
     * @return {@link Stack<Character>}
     */
    @Override
    public Stack<Character> traceBack(int[][] backTracker, String s1){
        // create the stack
        Stack<Character> subsequence = new Stack<>();

        // to help loop effectively
        int j=backTracker.length-1;
        int i=backTracker[j].length-1;

        // backtrack from the bottom right to the top or the left (whichever comes first)
        while (j > 0 && i > 0){
            if (backTracker[j][i] == 2){
                j--;
                i--;
                subsequence.push(s1.charAt(j));
            } else if (backTracker[j][i] == 1)
                j--;
            else
                i--;
        }

        // return the stack
        return subsequence;
    }

    /**
     * Class method to perform the Bottom-Up algorithm needed to compute the 2D array to backtrack the longest common subsequence of two strings
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link int[][]}
     */
    public static int[][] LCS(String s1, String s2){
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
                    backTracker[j][i] = 2;
                }
                // if not take the better option between top and left
                else{
                    if (counter[j][i-1] >= counter[j-1][i]) {
                        counter[j][i] = counter[j][i-1];
                        backTracker[j][i] = 0;
                    } else {
                        counter[j][i] = counter[j-1][i];
                        backTracker[j][i] = 1;
                    }
                }
            }
        }

        // return the 2D array
        return backTracker;
    }

}