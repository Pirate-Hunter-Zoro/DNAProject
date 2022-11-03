/* *****************************************
 * CSCI311 -Design and Analysis of Algorithms
 * Fall2022
 * Instructor: Prof. Edward Talmage
 *
 * Group: 8
 * Date: 11/3/22
 * Time: 8:11 AM
 *
 * Project: DNAProject
 * Package: FileScanner
 * Class: StringConverterCounter
 *
 * Description: Scans all files just like the Reader class, but instead of counting matches, this Reader considers how many changes it takes to convert the query sequence into the database sequence
 *
 * *****************************************/

package FileScanner;
import SolvingProblem.SolverSubstring;

import java.io.File;
import java.util.HashMap;

public class StringConverterCounter {

    /** A {@link Reader} (doesn't matter what type) is implemented to create this */
    private HashMap<String, String> descriptionToDNAMap;

    /** How many operations did it take to turn the query sequence into the database sequence? */
    private int numConversionsForClosestMatch;

    /** The query DNA sequence - a dummy Reader will take care of finding this for us */
    private String query;

    /**
     * Constructor for a StringCoverterCounter
     * @param queryFile
     * @param databaseFile
     */
    public StringConverterCounter(File queryFile, File databaseFile) {
        // how can we read in the sequences?
        // Reader class already takes care of this for us
        Reader dummyReader = new Reader(new SolverSubstring(), queryFile, databaseFile);
        this.descriptionToDNAMap = dummyReader.descriptionToDNAMap;
        this.query = dummyReader.getQuery();
    }

    /**
     * Simple getter for the private numConversionsForClosestMatch member
     * @return int
     */
    public int getNumConversionsForClosestMatch(){
        return numConversionsForClosestMatch;
    }

    /**
     * Look through all database sequences and see which one takes the least amount of steps to convert into a query
     * @return
     */
    public String findBestMatch(){
        // surely this record will be beaten
        int recordSwitchCount = Integer.MAX_VALUE;
        String recordDescription = "";

        // look at every sequence available and compare the match
        for (String key : descriptionToDNAMap.keySet()){
            // calculate how good the match is
            String sequence = descriptionToDNAMap.get(key);
            int numOperationsForSwitch = this.minDistance(query, sequence);

            // set the new record
            if (numOperationsForSwitch < recordSwitchCount){
                recordSwitchCount = numOperationsForSwitch;
                recordDescription = key;
            }
        }

        // record the record match length in the countOfClosestMatch attribute
        this.numConversionsForClosestMatch = recordSwitchCount;

        // return the description of the closest match
        return recordDescription;
    }

    /**
     * Implement dynamic programming to find the minimum number of add/remove/replace operations it takes to convert one string into another
     * Source for understanding of the problem solution: https://www.youtube.com/watch?v=We3YDTzNXEk&t=206s
     * @param word1
     * @param word2
     * @return int
     */
    public int minDistance(String word1, String word2) {

        int[][] counts = new int[word1.length()+1][word2.length()+1];

        for (int i=0; i<=word1.length(); i++){
            counts[i][0] = i;
        }
        for (int j=0; j<=word2.length(); j++){
            counts[0][j] = j;
        }

        for (int i=1; i<=word1.length(); i++){
            for (int j=1; j<=word2.length(); j++){
                int candidate1 = counts[i-1][j-1]; // consider sub problem a character earlier in both strings
                int candidate2 = counts[i-1][j]; // delete from s1
                int candidate3 = counts[i][j-1]; // delete from s2
                int best = Math.min(candidate1, Math.min(candidate2, candidate3));

                if (word1.charAt(i-1)==word2.charAt(j-1) && best == candidate1){
                    // no additional cost because we can just consider the character matching
                    counts[i][j] = best;
                } else {
                    // one additional cost for what represents either insertion, replacement, or deletion depending on which candidate won (but we don't care)
                    counts[i][j] = best + 1;
                }

            }
        }

        return counts[word1.length()][word2.length()];

    }

}