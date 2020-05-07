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
    public void testORFNoSkipSmall() {
        model.setRnaSequence("CCCAUGCCCUAG");
        assertEquals(12, model.getRnaSequence().length());
        HashMap<String, String> resultsMap = model.findORF();
        results = "AUGCCCUAG";
        assertEquals("", resultsMap.get("oneSkip"));
        assertEquals(results, resultsMap.get("noSkip"));
        assertEquals("", resultsMap.get("twoSkip"));
    }

    @Test
    public void testORFNoSkip() {
        model.setRnaSequence("AUGCCCUAG");
        assertEquals(9, model.getRnaSequence().length());
        HashMap<String, String> resultsMap = model.findORF();
        results = "AUGCCCUAG";
        assertEquals("", resultsMap.get("oneSkip"));
        assertEquals(results, resultsMap.get("noSkip"));
        assertEquals("", resultsMap.get("twoSkip"));
    }

    @Test
    public void testORF1SkipSmall() {
        model.setRnaSequence("CCCCAUGCCCUAG");
        HashMap<String, String> resultsMap = model.findORF();
        results = "AUGCCCUAG";
        assertEquals(results, resultsMap.get("oneSkip"));
        assertEquals("", resultsMap.get("noSkip"));
        assertEquals("", resultsMap.get("twoSkip"));
    }

    @Test
    public void testORF2SkipSmall() {
        model.setRnaSequence("CCCCCAUGCCCUAG");
        HashMap<String, String> resultsMap = model.findORF();
        results = "AUGCCCUAG";
        assertEquals("", resultsMap.get("oneSkip"));
        assertEquals("", resultsMap.get("noSkip"));
        assertEquals(results, resultsMap.get("twoSkip"));
    }
}
