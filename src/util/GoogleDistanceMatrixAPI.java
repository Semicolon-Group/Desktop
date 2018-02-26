package util;

import java.io.StringReader;
import models.Address;
import models.PlaceSuggestion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GoogleDistanceMatrixAPI {
    
    private static final String URLString = "https://maps.googleapis.com/maps/api/distancematrix/json?#&key=";
    private static final String KEY = "AIzaSyBwYcSUSj2uRzDIMclaDjzGE3eoHQur64Y";
    
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
    
    public static double getDistance(Address A, Address B){
        String originsString = "origins="+A.getLatitude()+","+A.getLongitude();
        String destinationsString = "destinations="+B.getLatitude()+","+B.getLongitude();
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
                    distance = distance.substring(0, distance.length() - 3);
                    while(distance.contains(","))
                        distance = distance.substring(0, distance.indexOf(",")) + distance.substring(distance.indexOf(",") + 1);
                    return Double.parseDouble(distance);
                }
            }
        }
        return -1;
    }
}
