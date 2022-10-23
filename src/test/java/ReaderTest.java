import FileScanner.Reader;
import SolvingProblem.SolverLCS;
import SolvingProblem.SolverNeedlemanWunsch;
import SolvingProblem.SolverSubstring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    private Reader reader1;
    private Reader reader2;
    private Reader reader3;

    private static final String EXPECTED_QUERY = "GGGGACCCAGTAACCACCAGCCCTAAGTGATCCGCTACAATCAAAAACCATCAGCAAGCAGGAAGGTACT" +
                                                    "CTTCTCAGTGGGCCTGGCTCCCCAGCTAAGACCTCAGGGACTTGAGGTAGGATATAGCCTCCTCTCTTAC" +
                                                    "GTGAAACTTTTGCTATCCTCAACCCAGCCTATCTTCCAGGTTATTGTTTCAACATGGCCCTGTGGATGCG" +
                                                    "CTTCCTGCCCCTGCTGGCCCTGCTCTTCCTCTGGGAGTCCCACCCCACCCAGGCTTTTGTCAAGCAGCAC" +
                                                    "CTTTGTGGTTCCCACCTGGTGGAGGCTCTCTACCTGGTGTGTGGGGAGCGTGGCTTCTTCTACACACCCA" +
                                                    "TGTCCCGCCGTGAAGTGGAGGACCCACAAGGTGAGTTCTGCCACTGAATTCTGTCCCCAGTGCTAACTAC" +
                                                    "CCTGGTTTTCTTCACACTTGGGACATTGTAAATTGTGTCCTAGGTGTGGAGGGTCTCGGGATAACCAGGG" +
                                                    "AGTGGGGACACGTTTCTGGGGGAAGCTAGACATATGTAAACATGGCAGCTGCCAGGAATGAGTAAGAATC" +
                                                    "CTGCCTTAAGGGGTCCTTGGTGGTAGTAACTTGGGACATGTGACTAGATCCCAGGATAGGTACCTATTTA" +
                                                    "GGGCCCTCATAGAGCACTGCACTGACTGAAGATGAGTAGGCTTTAGAGGCCCATGTGTCCATCCATGACC" +
                                                    "AGTGACTTGTCCCACAGGCATGCAACCCCTGCCACCTGCAGGGGTTAAGGGGCGAGAAAACCTGGGGTAG" +
                                                    "TAGGAGGTTGCTCAGCTACTCCTGACTGGATTTTCCTATGTGTCTTTGCTTCTGTGCTGCTGATGCCCTG" +
                                                    "GCCTGCTCTGACACAACCTCCCTGGCAGTGGCACAACTGGAGCTGGGTGGAGGCCCGGGAGCAGGTGACC" +
                                                    "TTCAGACCTTGGCACTGGAGGTGGCCCAGCAGAAGCGTGGCATTGTAGATCAGTGCTGCACCAGCATCTG" +
                                                    "CTCCCTCTACCAGCTGGAGAACTACTGCAACTAGACCCACCACTACCCAGCCTACCCCTCTGCAATGAAT" +
                                                    "AAAACCTTTGAATGAGCACAA";

    /**
     * Setup for each reader
     */
    @BeforeEach
    void setup(){
        reader1 = new Reader(new SolverLCS());
        reader2 = new Reader(new SolverSubstring());
        reader3 = new Reader(new SolverNeedlemanWunsch());
    }

    /**
     * A simple test to ensure that the 'query' member was initialized properly
     */
    @Test
    void testQuery(){
        assertEquals(EXPECTED_QUERY, reader1.getQuery());
        assertEquals(EXPECTED_QUERY, reader2.getQuery());
        assertEquals(EXPECTED_QUERY, reader3.getQuery());
    }

}