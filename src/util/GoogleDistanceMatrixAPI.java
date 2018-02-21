package util;

import java.io.StringReader;
import models.Address;
import models.PlaceSuggestion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GoogleDistanceMatrixAPI {
    
    private static final String URLString = "https://maps.googleapis.com/maps/api/distancematrix/json?#&key=";
    private static final String KEY = "AIzaSyA2CqTk_LZxl3Rw5Cz-Nav6KlbL0jYwBVA";
    
    public static PlaceSuggestion getDistanceAndDuration(Address memberAddress, PlaceSuggestion suggestion){
        String originsString = "origins="+memberAddress.getLatitude()+","+memberAddress.getLongitude();
        String destinationsString = "destinations="+suggestion.getLat()+","+suggestion.getLng();
        String preparedURL = URLString.replace("#", originsString+"&"+destinationsString)+KEY;
        String content = HTTPConnector.connect(preparedURL);
        if(content != null){
            JSONObject jsonObject = JSONParserUtils.extractor(new StringReader(content.toString()));
            JSONArray jsonRows = (JSONArray) jsonObject.get("rows");
            if(jsonRows != null && jsonRows.size() != 0){
                JSONObject firstRow = (JSONObject) jsonRows.get(0);
                JSONArray jsonElements = (JSONArray)firstRow.get("elements");
                if(jsonElements != null && jsonElements.size() != 0){
                    JSONObject firstElement = (JSONObject)jsonElements.get(0);
                    String distance = (String)((JSONObject)firstElement.get("distance")).get("text");
                    String duration = (String)((JSONObject)firstElement.get("duration")).get("text");
                    suggestion.setDistance(distance);
                    suggestion.setDuration(duration);
                    return suggestion;
                }
            }
        }
        return null;
    }
}
