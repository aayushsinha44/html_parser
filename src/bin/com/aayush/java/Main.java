package com.aayush.java;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 

/**
 * Coded by
 * Aayush Sinha and Abhishek Prakash
 * 14-11-2017 22:33 
 */ 

/*
 * It is the main class containing main method
 * and SWING components. 
 */
public class Main  implements ActionListener{

	/**
	 * @param args
	 */
	public static JTextField tf;
	public static JTextArea l;
	public static JButton b;
	public static JScrollPane scroll;
	// Constructor of Main Class
	Main(){  
		// Frame
		JFrame f= new JFrame("HTML Parser");  
        tf=new JTextField();  
        tf.setBounds(50,50, 250,20);  
        
        l = new JTextArea("");
        l.setWrapStyleWord(true);
        l.setLineWrap(true);
        l.setOpaque(false);
        l.setEditable(false);
        l.setFocusable(false);
        l.setBackground(UIManager.getColor("Label.background"));
        l.setFont(UIManager.getFont("Label.font"));
        l.setBorder(UIManager.getBorder("Label.border"));

        
        scroll = new JScrollPane (l, 
        		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        		   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(50,100, 470,400);   
        scroll.setVisible(false);
        b=new JButton("Parse HTML");  
        b.setBounds(350,45,135,30);  
        b.addActionListener(this);    
        f.add(b);f.add(tf);f.add(scroll);    
        f.setSize(600,600);  
        f.setLayout(null);  
        f.setVisible(true);  
    }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Main();
		
		System.out.println("Started");
		
		
	}
	
	// Onclick listener
	public void actionPerformed(ActionEvent e) {  
		String url = tf.getText().toString();
		HTTPHelper httpHelper = new HTTPHelper();
		GetHTMLData gethtmldata = new GetHTMLData(httpHelper,url);
		HTMLParser htmlparser = new HTMLParser(httpHelper);
    }  

}
