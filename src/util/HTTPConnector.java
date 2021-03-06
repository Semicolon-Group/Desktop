package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class HTTPConnector {
    
    public static String connect(String preparedURL){
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
            java.util.logging.Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
