package network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DataParser {
    protected HashMap<String, String> searchResults;

    public DataParser() {
        searchResults = new HashMap<>();
    }
    /**
     *Grab each JSON Object from array
     * @param data
     * @throws JSONException
     */
    public void grabJSONArray(String data) throws JSONException {
        JSONArray results = new JSONArray(data);
        for (int index = 0; index < results.length(); index++) {
            JSONObject object = results.getJSONObject(index);
            parseObject(object);
        }
    }

    public void parseObject(JSONObject object) throws JSONException {
        String proteinSeq = object.getString("aaSeq");
        String virusSpecies = object.getString("virusSpecies");
        String virusFamily = object.getString("virusFamily");
        String virusStrainName = object.getString("strainname");


    }
}
