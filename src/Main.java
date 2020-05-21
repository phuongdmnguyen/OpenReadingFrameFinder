import model.ORFFinder;
import model.TxtFileReader;
import model.TxtFileWriter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        System.out.println("Insert file name (including .txt): ");
        String fileName = scanner.next();
        System.out.println("Insert file path: ");
        String filePath = scanner.next();

        TxtFileReader txtFileReader = new TxtFileReader(fileName,filePath);

        ORFFinder orfFinder = txtFileReader.readFile();
        orfFinder.transcribeDNA();

        TxtFileWriter txtFileWriter = new TxtFileWriter(fileName,filePath);
        txtFileWriter.writeORFToFile(orfFinder);
    }
}
