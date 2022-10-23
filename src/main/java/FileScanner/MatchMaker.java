package FileScanner;/* *****************************************
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
 * Class: Aligner
 *
 * Description: Given an ArrayList of Pairs of indices and two Strings, this class counts the number of characters matched
 *
 * *****************************************/

import SolvingProblem.Pair;

import java.util.ArrayList;

public class MatchMaker {

    // two strings that had an algorithm applied to them
    private String s1;
    private String s2;
    private ArrayList<Pair> indexPairs;

    /**
     * Constructor the a {@link MatchMaker}
     * @param s1
     * @param s2
     * @param indexPairs
     */
    public MatchMaker(String s1, String s2, ArrayList<Pair> indexPairs){
        this.s1 = s1;
        this.s2 = s2;
        this.indexPairs = indexPairs;
    }

    /**
     * Simply reconstruct every common character between the two strings according to the indices in indexPairs
     * @return
     */
    public String makeMatch(){
        String match = "";
        for (Pair indexPair : this.indexPairs){
            char c1 = s1.charAt(indexPair.getFirst());
            char c2 = s2.charAt(indexPair.getSecond());
            if (c1 == c2) // not always true for NeedlemanWunsch
                match += c1;
        }
        return match;
    }

}