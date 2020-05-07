package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TxtFileReader {
    private String fileName;
    private String filePath;
    private File file;

    public TxtFileReader(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.file = new File(filePath+"/"+fileName);
    }

    /**Create a new ORFFinder from a text file
     * REQUIRES: file must contain a valid DNA sequence in a form of a string
     * @return ORFFinder with
     * @throws FileNotFoundException
     */
    public ORFFinder readFile() throws FileNotFoundException {
        ORFFinder orfFinder = new ORFFinder();
        Scanner scanner = new Scanner(file);

        String fileContent = "";
        while (scanner.hasNextLine()) {
            fileContent = fileContent.concat(scanner.nextLine());
        }

        orfFinder.setDnaSequence(fileContent);
        return orfFinder;
    }
}
