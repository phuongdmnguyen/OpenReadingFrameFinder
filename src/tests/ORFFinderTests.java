package tests;

import model.ORFFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ORFFinderTests {
    private String results;
    private ORFFinder model;

    @BeforeEach
    public void runBefore() {
        results = "";
        model = new ORFFinder();
    }

    @Test
    public void testTranscribe() {
        model.setDnaSequence("ATGCTG");
        results = "UACGAC";
        model.transcribeDNA();
        assertEquals(results, model.getRnaSequence());
    }

    @Test
    public void testTranslate() {
        model.setRnaSequence("AUACGC");
        results = "IR";
        model.translateRNA();
        assertEquals(results, model.getAminoAcid());
    }

    @Test
    public void testORFNoSkip() {
        model.setRnaSequence("CCCAUGCCCUAG");
        HashMap<String, String> resultsMap = model.findORF();
        results = "AUGCCCUAG";
        assertEquals("", resultsMap.get("oneSkip"));
        assertEquals(results, resultsMap.get("noSkip"));
        assertEquals("", resultsMap.get("twoSkip"));
    }
}
