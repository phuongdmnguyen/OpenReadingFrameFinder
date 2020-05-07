package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class TxtFileWriter {
    private String fileName;
    private String filePath;

    public TxtFileWriter(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * write possible ORFs into file that we read DNA sequence from
     * @param orfFinder
     * @throws IOException
     */
    public void writeORFToFile(ORFFinder orfFinder) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath + "/" + fileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        HashMap<String, String> results = orfFinder.findORF();
        printWriter.print("\r\n");
        for (String key: results.keySet()) {
            printWriter.println(key + ": " + results.get(key));
        }

        printWriter.close();
    }
}
