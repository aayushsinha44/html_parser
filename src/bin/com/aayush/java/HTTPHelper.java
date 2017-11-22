package com.aayush.java;
/**
 * Coded by
 * Aayush Sinha and Abhishek Prakash
 * 14-11-2017 22:33 
 */

/*
 * This is the hepler class containing synchronized
 * methods for getting html data and parsing it.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.*;  
public class HTTPHelper {
	
	public static String data;
	public static boolean dataRecieved = false;
	private final int TIMEOUT = 15000;
	// Fetches html data
	synchronized public void getHTMLData(String url){
		HttpURLConnection conn = null;
	    try {
	    	System.out.println("Getting Data");
	        URL u = new URL(url);
	        conn = (HttpURLConnection) u.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(TIMEOUT);
	        conn.setReadTimeout(TIMEOUT);
	        conn.connect();
	        int status = conn.getResponseCode();
	        System.out.println("Status Code: "+ status);
	        switch (status) {
	            case 200:
	            case 201:
	                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    sb.append(line+"\n");
	                }
	                br.close();
	                dataRecieved = true;
	                data=  sb.toString();
	                notify();
	        }

	    } catch (MalformedURLException ex) {
	    	dataRecieved = true;
	    	ex.printStackTrace();
	        System.out.println(ex.toString());
	        Main.scroll.setVisible(true);
	        Main.l.setText(ex.toString());
	    } catch (IOException ex) {
	    	dataRecieved = true;
	    	ex.printStackTrace();
	    	System.out.println(ex.toString());
	    	Main.scroll.setVisible(true);
	    	Main.l.setText(ex.toString());
	    } finally {
	    	// Disconnect connection finally
	       if (conn != null) {
	          try {
	              conn.disconnect();
	          } catch (Exception ex) {
	        	  ex.printStackTrace();
	        	  Main.scroll.setVisible(true);
	        	  Main.l.setText(ex.toString());
	  	          System.out.println(ex.toString());
	          }
	       }
	    }
	}
	
	// Parses and displays html data
	synchronized public void parseHTML()
	{
		String parsed="";
		if(dataRecieved == true)
		{
			System.out.println(data);
			Main.scroll.setVisible(true);
			data = data.replace("\n"," ");
			int len=data.length();
			for(int i=0;i<len;++i)
			{
				if(data.charAt(i)=='<')
				{
					while(data.charAt(i)!='>') i++;
					if(data.charAt(i)=='<'){
						while(data.charAt(i)!='>') i++;
						i++;
					}
					parsed=parsed+"\n";
					
				}
				parsed=parsed+data.charAt(i);
			}
			parsed=parsed.replaceAll("(?m)^[ \t]*\r?>", "");
			parsed=parsed.replaceAll("(?m)^[ \t]*\r?\n", "");
		    Main.l.setText(parsed);
		    dataRecieved = false;
		    notify();
		}
	}
	
	
}
