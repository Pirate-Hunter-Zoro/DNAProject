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
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
    public static ArrayList<Pair> findSubsequencePositions(int[][] backTracker, String s1, String s2){
        // traceback the table
        Stack<Character> subsequence = traceBack(backTracker, s1);

        // unwind the stack to get the longest common subsequence
        String lCS = "";
        while (!subsequence.isEmpty())
            lCS += subsequence.pop();

        // to be filled up after we have the 2D back-tracking array
        ArrayList<Pair> pairs = createPairList(lCS, s1 ,s2);

        // the ArrayList of Pairs
        return pairs;
    }

    /**
     * No need for anything besides SubsequenceFinder to use this method
     * Given a String for reference, backtracks along a table to construct the longest common subsequence between the given String and whatever other String produced the table
     * @param backTracker {@link int[][]}
     * @param s1 {@link String}
     * @return {@link Stack<Character>}
     */
    private static Stack<Character> traceBack(int[][] backTracker, String s1){
        // create the stack
        Stack<Character> subsequence = new Stack<>();

        // to help loop effectively
        int j=backTracker.length-1;
        int i=backTracker[j].length-1;

        // backtrack from the bottom right to the top or the left (whichever comes first)
        while (j > 0 && i > 0){
            if (backTracker[j][i] == UP_LEFT){
                j--;
                i--;
                subsequence.push(s1.charAt(j));
            } else if (backTracker[j][i] == LEFT)
                j--;
            else
                i--;
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
     * @param common {@link String}
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return pairs {@link ArrayList<Pair>}
     * All implementers of this interphase receive this method
     */
    public static ArrayList<Pair> createPairList(String common, String s1, String s2){
        // reconstruct the respective indices within s1 and s2 that create the common String
        ArrayList<Pair> pairs = new ArrayList<>();

        // starting positions for searching - store as mutable object so that they can be modified
        int[] lastJ = {-1};
        int[] lastI = {-1};
        for (int k=0; k<common.length(); k++){
            // find the next occurrence of the character in s1 starting at index lastJ (and update lastJ)
            int s1Idx = findIndexAt(s1, common.charAt(k), lastJ);

            // same for s2 and lastI
            int s2Idx = findIndexAt(s2, common.charAt(k), lastI);

            // insert the index pair
            pairs.add(new Pair(s1Idx, s2Idx));
        }

        // return the list of pairs
        return pairs;
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
