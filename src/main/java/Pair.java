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
 * Class: Pair
 *
 * Description: Stores a pair of integers which corresponds to the index of one String, and the index of another
 *
 * *****************************************/

public class Pair {

    /** First and second coordinates */
    private int first;
    private int second;

    /**
     * Constructor for a Pair
     * @param first {@link int}
     * @param second {@link int}
     */
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }

    /** Simple getter for the first element of a Pair */
    public int getFirst() {
        return first;
    }

    /** Simple getter for the second element of a Pair */
    public int getSecond() {
        return second;
    }
}