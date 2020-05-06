package network;

import net.arnx.jsonic.JSONException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SequenceSearch {
    private static final String baseURL = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
    private static final String baseURLVIRUS = "https://www.viprbrc.org/brc/api/sequence?";
    private static String data;


    public static void performESearchForWebKey() throws IOException {
        String url = "https://www.viprbrc.org/brc/api/sequence?datatype=genome&family=influenza&country=China&fromyear=2016&toyear=2016&subtype=H3N2&segment=4&output=json";
        URL eUtil = new URL(url);

        HttpsURLConnection eUtilConnection = (HttpsURLConnection) eUtil.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(eUtilConnection.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            data = inputLine;
        }
        in.close();
    }

    private void parse() {
        try {
            DataParser dataParser = new DataParser();
        } catch (JSONException e) {
            System.out.println("Error parsing JSON data");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        performESearchForWebKey();
    }
}
