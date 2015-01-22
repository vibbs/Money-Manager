package com.mm.Controller;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.BorderFactory; 
import javax.swing.border.Border;

import com.mm.Date.DatePicker;
import com.mm.Model.Model;


public class Income extends JFrame  implements ActionListener{
	ArrayList<Model> model = new ArrayList<Model>();
	
	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("Cancel");
	ImageIcon img = new ImageIcon("MMICON.png");
	JTextField income = new JTextField(" ", 20);
	JTextField saving = new JTextField(" ", 20);
	
	
	//model
	private String cat;
	private float amt;
	private float sav;
	private float remamt;
	private int modeofpay; // 0 credit 1 debit 2 cash
	private int type; // 0-9 diff meaning
	
	private Scanner fileScanner;
    private PrintWriter fileWriter;

	public Income() {
		// TODO Auto-generated constructor stub
		setBounds(100, 100, 450, 350); //window location, window size
		
		setIconImage(img.getImage());
		  //Date--------------------------------------------------------------
		  JLabel label = new JLabel("Selected Date:");
		  final JTextField text = new JTextField(10);
		  
	      JButton pop = new JButton("popup");
	      JPanel p = new JPanel();
	      p.setMaximumSize( new Dimension(  450, 50) );
	      p.add(label);
	      p.add(text);
	      p.add(pop);
	      final JFrame f = new JFrame();
	      f.getContentPane().add(p);
	      f.pack();
	      
	      
	      pop.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
                      text.setText(new DatePicker(f).setPickedDate());
              }
      });
		
//end date----------------------------------------------------	
	      
		   JPanel a = new JPanel();
		   JPanel b = new JPanel();
		   JPanel c = new JPanel();
		   JPanel d = new JPanel();
		   JLabel picLabel = new JLabel(new ImageIcon("MMICON.png"));
		   d.add(picLabel);
		   
		   a.setMaximumSize( new Dimension(  450, 50) );
		   b.setMaximumSize( new Dimension(  450, 50) );
		   c.setMaximumSize( new Dimension(  450, 50) );
		   d.setMaximumSize( new Dimension(  450, 100) );

		   
		   	a.add(new JLabel("    Income Amount: "));
	        a.add(income);
	        a.add(new JLabel(" "));
	        
	        b.add(new JLabel("     Amount To save: "));
	        b.add(saving);
	        b.add(new JLabel(" "));
	        
	        c.add(submitButton);
	        c.add(cancelButton);
		   
		   JPanel panel = new JPanel();
		   panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		   panel.add(d);
		   panel.add(p);
		   panel.add(a);
		   panel.add(b);
		   panel.add(c); 

		   //int result = JOptionPane.showConfirmDialog(null, panel, "Please enter values.", JOptionPane.OK_CANCEL_OPTION);
		   Color lightBlue= new Color(52428);  
		   panel.setBackground(lightBlue);
		   a.setBackground(lightBlue);
		   b.setBackground(lightBlue);
		   c.setBackground(lightBlue);
		   d.setBackground(lightBlue);
		   
		   f.setBackground(lightBlue);
		   p.setBackground(lightBlue);
		   Container pane = this.getContentPane();
	        pane.add(panel, BorderLayout.CENTER);
	        
	        submitButton.addActionListener(this);
	        cancelButton.addActionListener(this);
	        
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
				catch (NoSuchElementException e) 
				{	//error handling in case file is tampered 
					//or not correctly written into!
					System.out.println("file not matches the object."+
										"Exiting");
					e.printStackTrace();
					System.exit(0); //Bad exit
				}
				//Creating a new user object instance with the new information given
				Model u = new Model(cat,amt,sav,remamt,modeofpay,type);
				model.add(u);
			}
			//closing the file after reading
			fileScanner.close(); 
		}	
		catch (FileNotFoundException e)
		{
			System.out.println("Error: File cannot be located!");
		}
		
		
		
		
	}
	
	
	//filewrite
		public void fileW()
		{
			try
			{   //writing the data to the file entered by the user
				fileWriter = new PrintWriter("userFile.txt"); 
				//short for  loop to write the list of user objects 
				for (Model u : model)
				{
					fileWriter.print(u.getCat()+" "+u.getAmt()+" "+u.getSav()+" "+u.getRemamt()+" "+u.getModeofpay()+" "+u.getType()+" ");
				}
				//closing the file after writing
				fileWriter.close(); 
			}
			catch (IOException e)
			{
				System.out.println("Error occured while writing to the file");
				//throwing the exception for the main to catch
				
			}
        
			
		}
		
		 public void actionPerformed( ActionEvent e) {
			 Object source = e.getSource();
			 if(source == submitButton)
			 {
		        System.out.println("Button pressed.");
		        try{
		        Model m1 = model.get(model.size()-1);
		        float sav = m1.getSav()+ Float.parseFloat(saving.getText());
		        float rem = Float.parseFloat(income.getText())-Float.parseFloat(saving.getText())+m1.getRemamt();
		        Model m2 = new Model("Income", 0, sav, rem, 0, 0);
		        model.add(m2);
		        JOptionPane.showMessageDialog(null, "Income added to file");
		        fileW();
		        }
		        catch(IndexOutOfBoundsException ev){
		        	System.out.println("Something Went wrong");
		        	JOptionPane.showMessageDialog(null, "Something Went wrong");
		        }
		        income.setText("");
		        saving.setText("");
		        repaint();
		      }
			 else if(source == cancelButton){
				 System.out.println("Button pressed.");
				 super.dispose();
			 }
		    }   
		 public static void main(String[] args) {
		        Income demo = new Income();demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
		 
		private void addButton(Container parent, String name) 
		{ 
			 JButton but = new JButton(name); 
			 but.setActionCommand(name); 
			 parent.add(but); 
		}

}
