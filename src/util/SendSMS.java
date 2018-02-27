/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import models.Member;

/**
 *
 * @author vaider
 */
public class SendSMS {
    public void SendSms(String msg,String number){
        Member m=new Member();
        try {
			// Construct data
			String apiKey = "apikey=" + "7YNDqoPV1S8-mvZcDOz9B1ltvg0qJ9tEE3N0tLkVzK	";
			String message = "&message=" + msg;
			String sender = "&sender=" + "SemiColon";
			String numbers = "&numbers=" + "+216"+ number;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                            JOptionPane.showMessageDialog(null, "message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
	}
    } 
    
