package com.mm.Controller;





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import com.mm.Model.Model;
import com.mm.Graph.*;

public class View extends JFrame  implements ActionListener{
	ArrayList<Model> model = new ArrayList<Model>();
	
	
	JButton cancelButton = new JButton("Cancel");
	JButton listButton = new JButton("List all");
	JButton rangeButton = new JButton("Range");
	JButton graphButton = new JButton("Graph");
	ImageIcon img = new ImageIcon("MMICON.png");
	
	StringBuilder S = new StringBuilder("Listing of all inputs : \n");
	JTextArea textArea = new JTextArea(40, 50);
    JScrollPane userPane = new JScrollPane(textArea);
    
    private Scanner fileScanner;
    private PrintWriter fileWriter;
    
  //model
  	private String cat;
  	private float amt;
  	private float sav;
  	private float remamt;
  	private int modeofpay; // 0 credit 1 debit 2 cash
  	private int type; // 0-9 diff meaning

	public View() {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
				setBounds(100, 100, 450, 350); //window location, window size
				
				
				   JPanel a = new JPanel();
				   JPanel b = new JPanel();
				   JPanel c = new JPanel();
				   JPanel e = new JPanel();
				   JPanel d = new JPanel();
				 
				   a.setMaximumSize( new Dimension(  450, 50) );
				   b.setMaximumSize( new Dimension(  450, 50) );
				   c.setMaximumSize( new Dimension(  450, 50) );
				   d.setMaximumSize( new Dimension(  450, 80) );
				   
				   JLabel picLabel = new JLabel(new ImageIcon("MMICON.png"));
				   d.add(picLabel);

				  
				   	a.add(listButton);
			        b.add(graphButton);
			        c.add(cancelButton);
			        e.add(rangeButton);
			        
				   
				   JPanel panel = new JPanel();
				   panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				   panel.add(d);
				   panel.add(a);
				   panel.add(b);
				   panel.add(c); 
				   listButton.addActionListener(this);
				   graphButton.addActionListener(this);
				   cancelButton.addActionListener(this);
				   rangeButton.addActionListener(this);
				   
				   Color lightBlue= new Color(52428);  
				   panel.setBackground(lightBlue);
				   a.setBackground(lightBlue);
				   b.setBackground(lightBlue);
				   c.setBackground(lightBlue);
				   d.setBackground(lightBlue);
				   e.setBackground(lightBlue);
				   

				   //int result = JOptionPane.showConfirmDialog(null, panel, "Please enter values.", JOptionPane.OK_CANCEL_OPTION);
				   Container pane = getContentPane();
			        pane.add(panel, BorderLayout.CENTER);
			        
		        setVisible(true);
		        setResizable(false); 
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        
		        
//-------------------------------------file read---------------------------------------------
		        try
				{//to read from the file and check if it exist or not!
					File userFile = new File("userFile.txt");
					fileScanner = new Scanner(userFile); 
					while (fileScanner.hasNext())
					{
						
						try
						{//loading the local variables with the content of the file
							cat = fileScanner.next(); 
							amt = Float.parseFloat(fileScanner.next());
							sav = Float.parseFloat(fileScanner.next()); 
							remamt = Float.parseFloat(fileScanner.next()); 
							modeofpay = Integer.parseInt(fileScanner.next());
							type = Integer.parseInt(fileScanner.next());
						}
						catch (NoSuchElementException ev) 
						{	//error handling in case file is tampered 
							//or not correctly written into!
							System.out.println("file not matches the object."+
												"Exiting");
							ev.printStackTrace();
							System.exit(0); //Bad exit
						}
						//Creating a new user object instance with the new information given
						Model u = new Model(cat,amt,sav,remamt,modeofpay,type);
						model.add(u);
					}
					//closing the file after reading
					fileScanner.close(); 
				}	
				catch (FileNotFoundException ev)
				{
					System.out.println("Error: File cannot be located!");
				}
		        S.append("---------------------\n");
		        for(Model u: model)
				{
		        	S.append(u.toString()); //appending user details to string builder
		        	S.append("\n");
				} 
		        textArea.append(new String(S));
	}
	

	//view
	public void view()
	{
		//will have to see how to get this functionality working
	}
	
	 public void actionPerformed( ActionEvent e) {
		 System.out.println("Button pressed.");
	        Object source = e.getSource();
			if(source == listButton)
			{
				
				JPanel p = new JPanel();
				final JFrame f = new JFrame();
				f.getContentPane().add(p);
				p.add(userPane,"Center");
				f.getContentPane().add(p);
			      f.pack();
			      f.setVisible(true);
				//repaint(); 
	        }
			else if(source == graphButton)
			{
				Graph g = new Graph();
		        repaint();
		         
			}
			else if(source == rangeButton)
			{
				//Graph g = new Graph();
		        repaint();
		         
			}
			 else if(source == cancelButton){
				 System.out.println("Button pressed.");
				 super.dispose();
			 } 
	    }   
	 public static void main(String[] args) {
	        View demo = new View();
	        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	 
	private void addButton(Container parent, String name) 
	{ 
		 JButton but = new JButton(name); 
		 but.setActionCommand(name); 
		 parent.add(but); 
	}
	
	

}
