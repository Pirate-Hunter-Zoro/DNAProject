package SolvingProblem;

import java.util.ArrayList;
import java.util.Stack;

/** To specify what method all three of our algorithm classes must include */
public interface SubsequenceFinder {

    public static final int UP_LEFT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;

    /**
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * All classes which implement SubsequenceFinder must implement this method
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
    public ArrayList<Pair> getLineUp(String s1, String s2);

    /**
     * All implementors of this Interphase have access to this method
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * @param backTracker {int[][]}
     * @return {@link ArrayList<Pair>}
     */
    public static ArrayList<Pair> findSubsequencePositions(int[][] backTracker){
        // traceback the table
        Stack<Pair> subsequence = traceBack(backTracker);

        // to be filled up after we have the 2D back-tracking array
        ArrayList<Pair> pairs = createPairList(subsequence);

        // the ArrayList of Pairs
        return pairs;
    }

    /**
     * No need for anything besides SubsequenceFinder to use this method
     * Given a String for reference, backtracks along a table to construct the longest common subsequence between the given String and whatever other String produced the table
     * @param backTracker {@link int[][]}
     * @return {@link Stack<Pair>}
     */
    private static Stack<Pair> traceBack(int[][] backTracker){
        // create the stack
        Stack<Pair> subsequence = new Stack<>();

        // to help loop effectively
        int j=backTracker.length-1;
        int i=backTracker[j].length-1;

        // backtrack from the bottom right to the top or the left (whichever comes first)
        while (j > 0 && i > 0){
            if (backTracker[j][i] == UP_LEFT){
                j--;
                i--;
                subsequence.push(new Pair(j, i));
            } else if (backTracker[j][i] == LEFT)
                i--;
            else
                j--;
        }

        // return the stack
        return subsequence;
    }

    /**
     * Actual implementation to create a table out of two strings, required by all sequence aligner algorithms in this project
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link int[][]}
     */
    public int[][] solve(String s1, String s2);

    /**
     * Given the common String between two strings, return the list of pairs of indices that match accordingly
     * @param pairs {@link Stack<Pair>}
     * @return pairList {@link ArrayList<Pair>}
     * All implementers of this interphase receive this method
     */
    public static ArrayList<Pair> createPairList(Stack<Pair> pairs){
        // reconstruct the respective indices within s1 and s2 that create the common String
        ArrayList<Pair> pairList = new ArrayList<>();

        // unwind the stack into a list of Pairs
        while (!pairs.isEmpty())
            pairList.add(pairs.pop());

        // return the list of Pairs
        return pairList;
    }

    /**
     * Given the character to search for in a String, and the starting position (which needs to be changed and therefore comes in an array), find the index in s at which c is found
     * @param s {@link String}
     * @param c {char}
     * @param lastIndex {int[]} (size 1)
     * @return idx {int}
     */
    private static int findIndexAt(String s, Character c, int[] lastIndex){
        int idx = -1;
        // find next occurrence in first string
        for (int i=lastIndex[0]+1; i<s.length(); i++){
            if (s.charAt(i) == c){
                idx = i;
                lastIndex[0] = idx;
                break;
            }
        }

        return idx;
    }

}
