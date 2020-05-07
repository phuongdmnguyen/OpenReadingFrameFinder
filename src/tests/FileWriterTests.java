package tests;

import model.ORFFinder;
import model.TxtFileReader;
import model.TxtFileWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileWriterTests {
    private String results;

    @BeforeEach
    public void runBefore() {
        results = "";
    }

    @Test
    public void testWriteORFToFile() throws IOException {
        String originalContent = "TACGGGATC";
        TxtFileReader txtFileReader = new TxtFileReader("test.txt","/Users/Phuong/Desktop/tekkie");
        results = "AUGCCCUAG";
        ORFFinder orfFinder = txtFileReader.readFile();
        orfFinder.transcribeDNA();
        TxtFileWriter txtFileWriter = new TxtFileWriter("test.txt","/Users/Phuong/Desktop/tekkie");
        txtFileWriter.writeORFToFile(orfFinder);

        File file = new File("/Users/Phuong/Desktop/tekkie/test.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String> check = new ArrayList<>();
        while(scanner.hasNextLine()) {
            check.add(scanner.nextLine());
        }

        assertEquals("noSkip: AUGCCCUAG", check.get(1));
        assertEquals("oneSkip: ",check.get(2));
        assertEquals("TACGGGATC",check.get(0));


    }
}
