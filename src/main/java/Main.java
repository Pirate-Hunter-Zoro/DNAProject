/* *****************************************
 * CSCI311 - Design and Analysis of Algorithms
 * Fall2022
 * Instructor: Prof. Edward Talmage
 *
 * Group: 7
 * Date: 10/23/22
 * Time: 6:02PM
 *
 * Project: DNAProject
 * Package: PACKAGE_NAME
 * Class: Main
 *
 * Description: Main method as the user for an algorithm to implement, and perform DNA matching with said algorithm
 *
 * *****************************************/

import FileScanner.Reader;
import SolvingProblem.SolverLCS;
import SolvingProblem.SolverNeedlemanWunsch;
import SolvingProblem.SolverSubstring;

public class Main {

    public static void main(String[] args) {

        System.out.println("Needleman Wunsch:");
        Reader readerNeedle = new Reader(new SolverNeedlemanWunsch());
        String bestMatch = readerNeedle.findBestMatch();
        System.out.println(bestMatch);
        System.out.println(readerNeedle.getCountOfClosestMatch());
        System.out.println();

        System.out.println("Longest Common Subsequence:");
        Reader readerLCS = new Reader(new SolverLCS());
        bestMatch = readerLCS.findBestMatch();
        System.out.println(bestMatch);
        System.out.println(readerLCS.getCountOfClosestMatch());
        System.out.println();

        System.out.println("Longest Common Substring:");
        Reader readerSubstring = new Reader(new SolverSubstring());
        bestMatch = readerSubstring.findBestMatch();
        System.out.println(bestMatch);
        System.out.println(readerSubstring.getCountOfClosestMatch());
        System.out.println();

    }

}