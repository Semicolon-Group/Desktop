
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Collection;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.FaceDetection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Elyes
 */
public class NewClass1 {

    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, ProtocolException, IOException, Exception {
        /*File f = new File("D:\\HeadQuarters\\Esprit\\3ème Année\\PI-DEV\\javafx\\MySoulMate\\test\\girl.png");
        String encodstring = encodeFileToBase64Binary(f);
        String rawData = "{\"image\":\"" + encodstring + "\"}";
        String type = "application/json";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://api.kairos.com/detect");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("app_id", "f44fc0ce");
        httpPost.setHeader("app_key", "f1adc68703b0891fcd9af1426c7f3915");
        try {
            StringEntity stringEntity = new StringEntity(rawData);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);
            InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new Exception(response.getStatusLine().getReasonPhrase());
            }
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0) {
                    break;
                }
                sb.append(s);

            }
            buf.close();
            ips.close();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(sb.toString());
            JSONArray array = (JSONArray)json.values().stream().findFirst().get();
            json = (JSONObject)array.stream().findFirst().get();
            array = (JSONArray)((JSONArray)json.get("faces"));
            Object conf = ((JSONObject)((JSONObject)((JSONObject)array.get(0)).get("attributes")).get("gender")).get("maleConfidence");
            if(conf instanceof Double)
                System.out.println((double)conf);
            if(conf instanceof Long)
                System.out.println((long)conf + 0.0d);
            System.out.println(conf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        double d = FaceDetection.getMaleConfidence("D:\\HeadQuarters\\Esprit\\3ème Année\\PI-DEV\\javafx\\MySoulMate\\test\\girl.png");
        System.out.println(d);
    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
}
