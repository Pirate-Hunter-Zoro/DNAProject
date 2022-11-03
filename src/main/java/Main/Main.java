/* *****************************************
 * CSCI311 -Design and Analysis of Algorithms
 * Fall2022
 * Instructor: Prof. Edward Talmage
 *
 * Group: 7
 * Date: 11/3/22
 * Time: 1:58 PM
 *
 * Project: DNAProject
 * Package: Main
 * Class: Main
 *
 * Description: Run and compile all files, including this class, to test our algorithms
 *
 * *****************************************/

package Main;

import FileScanner.Reader;
import FileScanner.StringConverterCounter;
import SolvingProblem.SolverLCS;
import SolvingProblem.SolverNeedlemanWunsch;
import SolvingProblem.SolverSubstring;

import java.io.File;
import java.util.Scanner;

public class Main {

    /**
     * Main method to allow the user to see algorithm results
     * @param args
     */
    public static void main(String[] args) {

        // ask for that once
        File databaseFile = getDatabaseFileFromUser();

        // keep taking in queries until the user is done
        boolean done = false;
        while (!done) {
            File queryFile = getQueryFileFromUser();
            printAlgorithmResults(databaseFile, queryFile);
            done = !goAgain();
        }

    }

    /**
     * Have the user specify the database file
     * @return File
     */
    private static File getDatabaseFileFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the database file: ");
        String fileName = scanner.next();
        scanner.nextLine();
        return new File(fileName);
    }

    /**
     * Have the user specify the query file
     * @return File
     */
    private static File getQueryFileFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the query file: ");
        String fileName = scanner.next();
        scanner.nextLine();
        return new File(fileName);
    }

    /**
     * Given the database and query files, run the algorithms
     * @param database
     * @param query
     */
    private static void printAlgorithmResults(File database, File query){
        Reader reader1 = new Reader(new SolverSubstring(),  query, database);
        Reader reader2 = new Reader(new SolverLCS(), query, database);
        Reader reader3 = new Reader(new SolverNeedlemanWunsch(), query, database);
        StringConverterCounter counter = new StringConverterCounter(query, database);

        System.out.println();

        System.out.println("Longest Common Substring:");
        System.out.println("Best Match: " +  reader1.findBestMatch());
        System.out.println("Match Length: " + reader1.getCountOfClosestMatch());

        System.out.println();

        System.out.println("Longest Common Subsequence:");
        System.out.println("Best Match: " +  reader2.findBestMatch());
        System.out.println("Match Length: " + reader2.getCountOfClosestMatch());

        System.out.println();

        System.out.println("Needleman-Wunsch:");
        System.out.println("Best Match: " +  reader3.findBestMatch());
        System.out.println("Match Length: " + reader3.getCountOfClosestMatch());

        System.out.println();

        System.out.println("Minimal Edit Distance:");
        System.out.println("Best Match: " + counter.findBestMatch());
        System.out.println("Shortest Edit Distance Between Query and Best Match: " + counter.getNumConversionsForClosestMatch());

        System.out.println();

    }

    /**
     * Allow the user to decide if they want to continue searching for queries
     */
    private static boolean goAgain(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to search for another query? Type 'NO' if not - any other response will assume you want another query: ");
        String result = scan.next();
        scan.nextLine();
        return !result.equals("NO");
    }

}