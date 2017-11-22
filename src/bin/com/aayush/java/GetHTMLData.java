package com.aayush.java;
/**
 * Coded by
 * Aayush Sinha and Abhishek Prakash
 * 14-11-2017 22:33 
 */

/*
 * This class fetches the source code from the
 * given url. 
 */
public class GetHTMLData implements Runnable{
	HTTPHelper httpHelper;
	String url;
	GetHTMLData(HTTPHelper httpHelper,String url){
		this.httpHelper = httpHelper;     
		this.url = url;
		// Start fetching thread
		new Thread(this, "Fetching").start(); 
	}

	public void run() {
		// TODO Auto-generated method stub
		httpHelper.getHTMLData(url);
	}
}
