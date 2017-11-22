package com.aayush.java;
/**
 * Coded by
 * Aayush Sinha and Abhishek Prakash
 * 14-11-2017 22:33 
 */
/*
 * This class parses the html code from the
 * given url. 
 */
public class HTMLParser implements Runnable {
	
	HTTPHelper httpHelper;
	HTMLParser(HTTPHelper httpHelper){
		this.httpHelper = httpHelper;     
		// Starting parsing thread
		new Thread(this, "Parsing").start(); 
	}

	public void run() {
		// TODO Auto-generated method stub
		httpHelper.parseHTML();
	}
	

}
