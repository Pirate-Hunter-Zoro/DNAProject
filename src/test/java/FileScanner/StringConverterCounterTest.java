package FileScanner;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StringConverterCounterTest {

    private String x;
    private String y;

    private StringConverterCounter converter = new StringConverterCounter(new File("DNA_query.txt"), new File("DNA_sequences.txt"));

    @Test
    void minDistance() {
        x = "geek";
        y = "gesek";
        assertEquals(1, converter.minDistance(x,y));

        x = "cat";
        y = "cut";
        assertEquals(1, converter.minDistance(x,y));

        x = "sunday";
        y = "saturday";
        assertEquals(3, converter.minDistance(x,y));
    }

}