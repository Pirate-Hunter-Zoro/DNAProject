package SolvingProblem;

import java.util.ArrayList;
import java.util.Stack;

/** To specify what method all three of our algorithm classes must include */
public interface SubsequenceFinder {

    /**
     * All implementors of this Interphase must implement this method
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
    public ArrayList<Pair> findSubsequencePositions(String s1, String s2);

    /**
     * All implementors of this Interphase must implement this method
     * Given a String for reference, backtracks along a table to construct the longest common subsequence between the given String and whatever other String produced the table
     * @param backTracker {@link int[][]}
     * @param s1 {@link String}
     * @return {@link Stack<Character>}
     */
    public Stack<Character> traceBack(int[][] backTracker, String s1);

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
