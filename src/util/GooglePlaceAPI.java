package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Address;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GooglePlaceAPI {
    private static final String URLString = "https://maps.googleapis.com/maps/api/place/#&language=fr&key=";
    private static final String KEY = "AIzaSyBEMYjEYCOujIR7uzIX6t-fO1m0ZjpC0Wc";
    
    public static List<Address> autoCompleteAddress(String input){
        input = input.replace(" ", "+");
        String preparedURL = URLString.replace("#", "autocomplete/json?input="+input+"&types=(regions)")+KEY;
        String content = connect(preparedURL);
        if(content != null){
            JSONObject jsonObject = JSONParserUtils.extractor(new StringReader(content.toString()));
            JSONArray addressArray = (JSONArray) jsonObject.get("predictions");
            List<Address> addresses = new ArrayList<>();
            for(int i=0; i<addressArray.size(); i++){
                Address address = new Address();
                
                JSONObject jsonAddress = (JSONObject) addressArray.get(i);
                address.setPlaceId((String)jsonAddress.get("place_id"));
                
                JSONArray addressTerms = (JSONArray)jsonAddress.get("terms");
                address.setCity((String)((JSONObject)addressTerms.get(0)).get("value"));
                address.setCountry((String)((JSONObject)addressTerms.get(addressTerms.size()-1)).get("value"));
                addresses.add(address);
            }
            return addresses;
        }
        return null;
    }
    
    public static Address getPlaceDetails(Address address){
        String preparedURL = URLString.replace("#", "details/json?placeid="+address.getPlaceId())+KEY;
        String content = connect(preparedURL);
        if(content!=null){
            JSONObject jsonObject = JSONParserUtils.extractor(new StringReader(content.toString()));
            JSONObject jsonAddress = (JSONObject) jsonObject.get("result");
            JSONObject jsonLocation = (JSONObject) ((JSONObject)jsonAddress.get("geometry")).get("location");
            address.setLatitude((double)jsonLocation.get("lat"));
            address.setLongitude((double)jsonLocation.get("lng"));
            return address;
        }
        return null;
    }

    private static String connect(String preparedURL){
        try {
            URL url = new URL(preparedURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                return content.toString();
            }
            return null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlaceAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlaceAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
