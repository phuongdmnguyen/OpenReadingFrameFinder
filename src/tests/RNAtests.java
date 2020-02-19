package tests;

import model.RNA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RNAtests {
    private RNA rnaTest;
    private ArrayList<String> modelRNA;

    @BeforeEach
    public void runBefore() {
        rnaTest = new RNA();
        modelRNA = new ArrayList<>();
    }

    @Test
    public void testGroupCodon6Long() {
        for (int i = 0; i < 6; i++) {
            rnaTest.addBase("A");
        }
        modelRNA.add("AAA");
        modelRNA.add("AAA");
        rnaTest.groupCodon();
        assertEquals(modelRNA, rnaTest.getCodons());
    }

    @Test
    public void testGroupCodon30Long() {
        for (int i = 0; i < 30; i++) {
            rnaTest.addBase("G");
        }
        for (int i = 0; i < 10; i++) {
            modelRNA.add("GGG");
        }
        rnaTest.groupCodon();
        assertEquals(modelRNA,rnaTest.getCodons());
    }

    @Test
    public void testGroupCodon66Long() {
        for (int i = 0; i < 66; i++) {
            rnaTest.addBase("G");
        }
        for (int i = 0; i < 22; i++) {
            modelRNA.add("GGG");
        }
        rnaTest.groupCodon();
        assertEquals(modelRNA,rnaTest.getCodons());
    }

    @Test
    public void testGetFirstBaseIndex() {
        String sample = "UCG";
        rnaTest.addCodons(sample);
        assertEquals(0, rnaTest.getIndexFirstBase(sample));
        String sample2 = "GAA";
        rnaTest.addCodons(sample2);
        assertEquals(3, rnaTest.getIndexFirstBase(sample2));
    }

    @Test
    public void testGetSecondBaseIndex() {
        String sample = "UCG";
        String sample2 = "AGC";
        rnaTest.addCodons(sample);
        rnaTest.addCodons(sample2);
        assertEquals(2, rnaTest.getIndexSecondBase(sample));
        assertEquals(4, rnaTest.getIndexSecondBase(sample2));
    }

    @Test
    public void testTranslateCodon() {
        String sample = "UCG";
        rnaTest.addCodons(sample);
        assertEquals("Ser", rnaTest.translateCodon(sample));
    }

    @Test
    public void testTranslateCodons() {
            for (int i = 0; i < 66; i++) {
                rnaTest.addBase("G");
            }
            for (int i = 0; i < 22; i++) {
                modelRNA.add("Gly");
            }
            rnaTest.groupCodon();
            rnaTest.translateCodons();
            assertEquals(modelRNA,rnaTest.getAminoacidsequence());
    }

}
