/* *****************************************
 * CSCI311 - Design and Analysis of Algorithms
 * Fall2022
 * Instructor: Prof. Edward Talmage
 *
 * Group: 7
 * Date: 10/23/22
 * Time: 3:48PM
 *
 * Project: DNAProject
 * Package: FileScanner
 * Class: Reader
 *
 * Description: Class responsible for reading from the actual text files for matching
 *
 * *****************************************/

package FileScanner;

import SolvingProblem.SubsequenceFinder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Reader {

    // what files are we reading?
    public static final String QUERY_FILE = "DNA_query.txt";
    public static final String DATABASE_FILE = "DNA_sequences.txt";

    // what regular expression are we using to look through a file for DNA sequences?
    public static final String DNA_REGEX = "(([ATGC]+[\\n]?){2,})";
    public static final String LINE_OF_DNA_REGEX = "[ATGC]+(?=\\n)";

    // what regular expression are we using to look through a file for DNA descriptions?
    public static final String DNA_DESCRIPTION = ">[^\\n]+";

    // after construction, the algorithmic method is set and done
    private final SubsequenceFinder solver;

    // after construction, the string to sweep through and look for close matches is set and done
    private final String query;

    /**
     * Constructor for the Reader - it will need to call some helper methods
     * @param solver
     */
    public Reader(SubsequenceFinder solver){
        this.solver = solver;
        this.query = readForQuery();
    }

    /**
     * Simple getter for the 'query' member
     * @return {@link String}
     */
    public String getQuery(){
        return query;
    }

    /**
     * Opens the query file and gets a hold of the query via regular expressions
     * @return {@link String}
     */
    private static String readForQuery(){
        try (FileInputStream inStream = new FileInputStream(QUERY_FILE)){
            return scanStreamForQuery(inStream);
        } catch (IOException e){
            return "This should never happen";
        }
    }

    /**
     * Scans an input stream for a regular expression match according to the static pattern
     * @param inStream
     * @return
     */
    private static String scanStreamForQuery(FileInputStream inStream){
        // first get a block of DNA (separated by a bunch of '\n' characters)
        String DNABlock = getDNABlock(inStream);

        // now break it up line by line and combine each line into one String - a DNA sequence
        return getStringAsDNASequence(DNABlock);
    }

    /**
     * Helper method to obtain the block of DNA as a {@link String}, with '\n' characters included
     * @param inStream {@link String}
     * @return DNABlock {@link String}
     */
    private static String getDNABlock(FileInputStream inStream) {
        Pattern pattern = Pattern.compile(DNA_REGEX);
        Scanner looker = new Scanner(inStream);
        String DNABlock = "";
        while (looker.findWithinHorizon(pattern, 0) != null) {
            MatchResult matchResult = looker.match();
            DNABlock += matchResult.group(1);
        }
        return DNABlock + "\n"; // add a new line to make the getStringAsDNASequence method easier
    }

    /**
     * One a block of DNA is found within a file (including the new lines), break it up and combine line by line to form one long string of only A, T, G, or C
     * @param DNABlock {@link String}
     * @return DNASequence {@link String}
     */
    private static String getStringAsDNASequence(String DNABlock) {
        Pattern pattern;
        Scanner looker;
        // now look through each DNABlock to get the actual DNA sequence
        String DNASequence = "";
        pattern = Pattern.compile(LINE_OF_DNA_REGEX);
        looker = new Scanner(DNABlock);
        while (looker.findWithinHorizon(pattern, 0) != null) {
            MatchResult matchResult = looker.match();
            // turn the match (each line) into a string
            DNASequence += matchResult.group();
        }

        return DNASequence;
    }

}