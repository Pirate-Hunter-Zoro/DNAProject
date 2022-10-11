/* *****************************************
 * CSCI311 - Design and Analysis of Algorithms
 * Fall2022
 * Instructor: Prof. Edward Talmage
 *
 * Group: 7
 * Date: 10/9/22
 * Time: 7:51PM
 *
 * Project: DNAProject
 * Package: SequenceAligner
 * Class: Solver
 *
 * Description: Given two strings, find the indeces of each String that map to the longest common subsequence
 *
 * *****************************************/


import java.util.ArrayList;

public class Solver {

    /**
     * Class method to find the respective indices from each string of the two strings' longest common subsequence
     * @param s1 {@link String}
     * @param s2 {@link String}
     * @return {@link ArrayList<Pair>}
     */
    public static ArrayList<Pair> findSubsequencePositions(String s1, String s2){
        // to be filled up after we have the 2D back-tracking array
        ArrayList<Pair> pairs = new ArrayList<>();

        // fill out a 2D array containing all the backtracking necessary to reconstruct the longest common subsequence of the two strings
        int[][] backTracker = LCS(s1, s2);

        // now progress in order from start to finish to reconstruct the longest common subsequence
        int i=1, j=1;
        int lastJ = j;
        while (i < backTracker.length){
            while (j < backTracker[i].length) {
                if (backTracker[i][j] == 2) {
                    pairs.add(new Pair(i - 1, j - 1));
                    i++;
                    lastJ = j;
                    j++;
                    break;
                } else {
                    j++;
                    if (j == backTracker[i].length) {
                        i++;
                        j = lastJ+1;
                    }
                }
            }
        }

        return pairs;
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