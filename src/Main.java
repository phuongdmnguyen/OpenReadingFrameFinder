import model.ORFFinder;
import model.TxtFileReader;
import model.TxtFileWriter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        TxtFileReader txtFileReader = new TxtFileReader("test.txt","/Users/Phuong/Desktop/tekkie");

        ORFFinder orfFinder = txtFileReader.readFile();
        orfFinder.transcribeDNA();

        TxtFileWriter txtFileWriter = new TxtFileWriter("test.txt","/Users/Phuong/Desktop/tekkie");
        txtFileWriter.writeORFToFile(orfFinder);
    }
}
