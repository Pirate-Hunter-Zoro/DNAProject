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

import SolvingProblem.Pair;
import SolvingProblem.SubsequenceFinder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Reader {

    // what files are we reading?
    private String queryFile;
    private String databaseFile;

    // what regular expression are we using to look through a file for DNA sequences?
    public static final String DNA_REGEX = "(?<![^atgcATGC\\n])(([atgcATGC]+[\\n]?){2,})(?![^atgcATGC\\n])";
    public static final String LINE_OF_DNA_REGEX = "[atgcATGC]+(?=\\n)";

    // what regular expression are we using to look through a file for DNA descriptions?
    public static final String DNA_DESCRIPTION = ">[^\\n]+";

    // after construction, the algorithmic method is set and done
    private final SubsequenceFinder solver;

    // after construction, the string to sweep through and look for close matches is set and done
    private final String query;

    private HashMap<String,String> descriptionToDNAMap;

    private int countOfClosestMatch;

    /**
     * Constructor for the Reader - it will need to call some helper methods
     * @param solver
     */
    public Reader(SubsequenceFinder solver, String queryFile, String databaseFile){
        this.solver = solver;
        this.queryFile = queryFile;
        this.databaseFile = databaseFile;
        this.query = readForQuery();
        this.descriptionToDNAMap = readInSequences();
        this.countOfClosestMatch = 0;
    }

    /**
     * Simple getter for the 'query' member
     * @return {@link String}
     */
    public String getQuery(){
        return query;
    }

    /**
     * Simple getter for the 'countOfClosestMatch' member
     * @return countOfClosestMatch
     */
    public int getCountOfClosestMatch(){
        return countOfClosestMatch;
    }

    /**
     * Simple getter for a DNA sequence, given a description
     * @param key
     * @return {@link String}
     */
    public String getDNASequence(String key){
        return descriptionToDNAMap.get(key);
    }

    /**
     * Opens the query file and gets a hold of the query via regular expressions
     * @return {@link String}
     */
    private String readForQuery(){
        try (FileInputStream inStream = new FileInputStream(this.queryFile)){
            return this.scanStreamForQuery(inStream);
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
        return getBlockAsDNASequence(DNABlock);
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
        if (looker.findWithinHorizon(pattern, 0) != null) {
            MatchResult matchResult = looker.match();
            DNABlock += matchResult.group(1);
        }
        return DNABlock + "\n"; // add a new line to make the getStringAsDNASequence method easier
    }

    /**
     * Once a block of DNA is found within a file (including the new lines), break it up and combine line by line to form one long string of only A, T, G, or C
     * @param DNABlock {@link String}
     * @return DNASequence {@link String}
     */
    private static String getBlockAsDNASequence(String DNABlock) {
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

    /**
     * Obtain all the DNA descriptions and map them to their DNA chains
     * @return {@link HashMap<String,String>}
     */
    private HashMap<String,String> readInSequences(){
        HashMap<String,String> DNAMap = new HashMap<>();

        // scan to create lists of sequences and descriptions
        ArrayList<String> DNASequences = this.scanForDNASequences();
        ArrayList<String> DNADescriptions = this.scanForDNADescriptions();

        // now just construct the map
        for (int i=0; i<DNASequences.size(); i++)
            DNAMap.put(DNADescriptions.get(i), DNASequences.get(i));

        return DNAMap;
    }

    /**
     * Scan the relevant static file for all the DNA sequences
     * @return {@link ArrayList<String>}
     */
    private ArrayList<String> scanForDNASequences(){
        ArrayList<String> sequences = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(this.databaseFile)){
            // goes through the file and adds all the matches to 'sequences'
            getAllDNASequences(sequences, inputStream);
            return sequences;
        } catch (IOException e){
            return sequences;
        }
    }

    /**
     * Scan the relevant static file for all the DNA descriptions
     * @return {@link ArrayList<String>}
     */
    private ArrayList<String> scanForDNADescriptions(){
        ArrayList<String> descriptions = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(this.databaseFile)){
            // goes through the file and adds all the matches to 'descriptions'
            getAllDNADescriptions(descriptions, inputStream);
            return descriptions;
        } catch (IOException e){
            return descriptions;
        }
    }

    /**
     * Adds all the DNA sequence matches to 'sequences'
     * @param sequences
     * @param inputStream
     */
    private static void getAllDNASequences(ArrayList<String> sequences, FileInputStream inputStream){
        Pattern pattern = Pattern.compile(DNA_REGEX);
        Scanner matcher = new Scanner(inputStream);

        // now match until we run out of matches
        while (matcher.findWithinHorizon(pattern, 0) != null){
            String blockOfDNA = matcher.match().group(1).toUpperCase();
            // unwrap the block into one long string
            sequences.add(getBlockAsDNASequence(blockOfDNA));
        }
    }

    /**
     * Adds all the DNA description matches to 'descriptions'
     * @param descriptions
     * @param inputStream
     */
    private static void getAllDNADescriptions(ArrayList<String> descriptions, FileInputStream inputStream){
        Pattern pattern = Pattern.compile(DNA_DESCRIPTION);
        Scanner matcher = new Scanner(inputStream);

        // now match until we run out of matches
        while (matcher.findWithinHorizon(pattern, 0) != null){
            String description = matcher.match().group();
            descriptions.add(description);
        }
    }

    /**
     * Implements the solver member to return the description of the DNA which is the closest match to the query member
     * @return {@link String}
     */
    public String findBestMatch(){
        String recordDescription = "";
        int recordMatchCount = 0;

        // changes throughout the loop
        MatchMaker matchMaker;

        // look at every sequence available and compare the match
        for (String key : descriptionToDNAMap.keySet()){
            // calculate how good the match is
            String sequence = descriptionToDNAMap.get(key);
            ArrayList<Pair> pairs = solver.getLineUp(this.query, sequence);
            matchMaker = new MatchMaker(this.query.toUpperCase(), sequence, pairs);
            String match = matchMaker.makeMatch();

            // set the new record
            if (match.length() > recordMatchCount){
                recordMatchCount = match.length();
                recordDescription = key;
            }
        }

        // record the record match length in the countOfClosestMatch attribute
        this.countOfClosestMatch = recordMatchCount;

        // return the description of the closest match
        return recordDescription;
    }

}