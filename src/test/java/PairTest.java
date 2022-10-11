import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(1, testPair.getFirst());
    }

    /** A simple test to ensure getSecond() getter works properly */
    @Test
    void getSecond() {
        assertEquals(2, testPair.getSecond());
    }

    /** A simple tests to ensure two Pair objects are compared correctly */
    @Test
    void equals() {
        assertEquals(new Pair(1,2), testPair);
        assertNotEquals(new Pair(1,-2), testPair);
    }

    /** A simple test to ensure that two Pair objects' hashCodes compare as expected */
    @Test
    void hash() {
        assertEquals((new Pair(1,2)).hashCode(), testPair.hashCode());
        assertNotEquals((new Pair(1,-2)).hashCode(), testPair.hashCode());
    }

}