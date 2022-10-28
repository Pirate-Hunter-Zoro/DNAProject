package SolvingProblem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PairTest {

    /** A pair to perform tests on */
    static Pair testPair;

    /** Pre-test setup of testPair */
    @BeforeEach
    void setup(){
        testPair = new Pair(1,2);
    }

    /** A simple test to ensure getFirst() getter works properly */
    @Test
    void getFirst() {
        Assertions.assertEquals(1, testPair.getFirst());
    }

    /** A simple test to ensure getSecond() getter works properly */
    @Test
    void getSecond() {
        Assertions.assertEquals(2, testPair.getSecond());
    }

    /** A simple tests to ensure two SolvingProblem.Pair objects are compared correctly */
    @Test
    void equals() {
        Assertions.assertEquals(new Pair(1,2), testPair);
        Assertions.assertNotEquals(new Pair(1,-2), testPair);
    }

    /** A simple test to ensure that two SolvingProblem.Pair objects' hashCodes compare as expected */
    @Test
    void hash() {
        Assertions.assertEquals((new Pair(1,2)).hashCode(), testPair.hashCode());
        Assertions.assertNotEquals((new Pair(1,-2)).hashCode(), testPair.hashCode());
    }

}