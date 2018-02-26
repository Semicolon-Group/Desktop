/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Elyes
 */
public class FaceDetection {
    
    /*
    * returns maleConfidence between 0 and 1.
    * throws an exception if there is more than one face in the picture.
    */
    public static double getMaleConfidence(String absolutePath) throws Exception {
        File f = new File(absolutePath);
        String encodstring = encodeFileToBase64Binary(f);
        String rawData = "{\"image\":\"" + encodstring + "\"}";
        String type = "application/json";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://api.kairos.com/detect");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("app_id", "f44fc0ce");
        httpPost.setHeader("app_key", "f1adc68703b0891fcd9af1426c7f3915");
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
        if(array.size() > 1)
            throw new Exception("Too many faces");
        Object conf = ((JSONObject)((JSONObject)((JSONObject)array.get(0)).get("attributes")).get("gender")).get("maleConfidence");
        if(conf instanceof Double)
            return (double)conf;
        if(conf instanceof Long)
            return (long)conf + 0.0d;
        
        return 0;
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
