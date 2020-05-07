package tests;

import model.ORFFinder;
import model.TxtFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTests {
    private String results;

    @BeforeEach
    public void runBefore() {
        results = "";
    }

    @Test
    public void testReadFile() throws FileNotFoundException {
        TxtFileReader txtFileReader = new TxtFileReader("test.txt","/Users/Phuong/Desktop/tekkie");
        results = "AUGCCCUAG";
        ORFFinder orfFinder = txtFileReader.readFile();
        assertEquals("TACGGGATC", orfFinder.getDnaSequence());
        orfFinder.transcribeDNA();

        HashMap<String, String> map = orfFinder.findORF();
        assertEquals(results, map.get("noSkip"));
        assertEquals("", map.get("oneSkip"));

    }
}
