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

import java.util.Objects;

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

    /**
     * Method for determining if two Pair objects are equal
     * @param obj {@link Object}
     * @return {@link boolean}
     */
    @Override
    public boolean equals(Object obj) {
        // null?
        if (obj == null)
            return false;
        // not a Pair?
        else if (!(obj instanceof Pair))
            return false;

        // cast obj to a Pair and compare
        Pair other = (Pair)obj;
        return other.first == this.first && other.second == this.second;
    }

    /**
     * Method for generating a hash code for a Pair instance
     * @return {@link int}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }
}