package com.mm.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.mm.Date.DatePicker;
import com.mm.Model.Model;

public class Expense extends JFrame  implements ActionListener{
	ArrayList<Model> model = new ArrayList<Model>();
	
	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("Cancel");
	private String[] mode = {"credit" , "debit" , "cash"}; 
	private String[] typ = {"bill" , "rent" , "groceries"}; // can be extended
	private JComboBox m = new JComboBox();
	private JComboBox t = new JComboBox();
	ImageIcon img = new ImageIcon("MMICON.png");
	
	JTextField amount = new JTextField(" ", 20);
	private Scanner fileScanner;
    private PrintWriter fileWriter;
    
  //model
  	private String cat;
  	private float amt;
  	private float sav;
  	private float remamt;
  	private int modeofpay; // 0 credit 1 debit 2 cash
  	private int type; // 0-9 diff meaning

	public Expense() {

		//Date--------------------------------------------------------------
		  JLabel label = new JLabel("Selected Date:");
		  final JTextField text = new JTextField(10);
	      JButton pop = new JButton("popup");
	      JPanel p = new JPanel();
	      p.setMaximumSize( new Dimension(  450, 50) );
	      p.add(label);
	      p.add(text);
	      p.add(pop);
	      final JFrame j = new JFrame();
	      j.getContentPane().add(p);
	      j.pack();
	      
	      
	      pop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                    text.setText(new DatePicker(j).setPickedDate());
            }
    });
		
//end date----------------------------------------------------	
				setBounds(100, 100, 450, 350); //window location, window size
				
				for(int i = 0 ; i<3 ; i++) m.addItem(mode[i]);
				for(int i = 0 ; i<3 ; i++) t.addItem(typ[i]);	
				
				   JPanel a = new JPanel();
				   JPanel b = new JPanel();
				   JPanel c = new JPanel();
				   JPanel e = new JPanel();
				   JPanel f = new JPanel();
				   JPanel d = new JPanel();
				   JLabel picLabel = new JLabel(new ImageIcon("MMICON.png"));
				   d.add(picLabel);
				 
				   a.setMaximumSize( new Dimension(  450, 50) );
				   b.setMaximumSize( new Dimension(  450, 50) );
				   c.setMaximumSize( new Dimension(  450, 50) );
				   d.setMaximumSize( new Dimension(  450, 70) );
				   e.setMaximumSize( new Dimension(  450, 50) );
				   f.setMaximumSize( new Dimension(  450, 50) );

				    a.add(new JLabel("    Amount Spent: "));
			        a.add(amount);
			        a.add(new JLabel(" 			 "));
			        
			       /* b.add(new JLabel("     Description: "));
			        b.add(new JTextField(" ", 20));
			        b.add(new JLabel(" 			"));*/
			        
			        e.add(new JLabel("     Mode: "));
			        e.add(m);
			        e.add(new JLabel("			 "));
			        
			        f.add(new JLabel("     Type: "));
			        f.add(t);
			        f.add(new JLabel(" 			"));
			        
			        c.add(submitButton);
			        c.add(cancelButton);
				   
				   JPanel panel = new JPanel();
				   panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				   panel.add(d);
				   panel.add(p);
				   panel.add(a);
				   panel.add(b); 
				   panel.add(e); 
				   panel.add(f); 
				   panel.add(c);
				   
				   submitButton.addActionListener(this);
				   cancelButton.addActionListener(this);
				   m.addActionListener(this);
				   t.addActionListener(this);
				   
				   Color lightBlue= new Color(52428);  
				   panel.setBackground(lightBlue);
				   a.setBackground(lightBlue);
				   b.setBackground(lightBlue);
				   c.setBackground(lightBlue);
				   d.setBackground(lightBlue);
				   e.setBackground(lightBlue);
				   f.setBackground(lightBlue);
				   p.setBackground(lightBlue);
				   //int result = JOptionPane.showConfirmDialog(null, panel, "Please enter values.", JOptionPane.OK_CANCEL_OPTION);
				   Container pane = getContentPane();
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
				
	}
	//add expenditure
		public void expense()
		{
			
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
		        System.out.println("Button pressed.");
		        Object source = e.getSource();
				 if(source == submitButton){
		        try{
			        Model m1 = model.get(model.size()-1);
			        float sav = m1.getSav();
			        float amt = Float.parseFloat(amount.getText());
			        float rem = m1.getRemamt()-Float.parseFloat(amount.getText());
			        int mode= m.getSelectedIndex();
			        int type= t.getSelectedIndex();
			        //Integer.parseInt(income.getText())-Integer.parseInt(saving.getText())+
			        Model m2 = new Model("Expense", amt, sav, rem, mode, type);
			        model.add(m2);
			        amount.setText("");
			        JOptionPane.showMessageDialog(null, "Expense Added to file!");
			        fileW();
		        }
			        catch(IndexOutOfBoundsException ev){
			        	System.out.println("Something Went wrong");
			        	JOptionPane.showMessageDialog(null, "Something Went wrong");
			       }}
				 else if(source == cancelButton){
					 System.out.println("Button pressed.");
					 super.dispose();
				 } 
		        
		        
		        
		        repaint();
		    }   
		 public static void main(String[] args) {
		       Expense demo = new Expense();demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
		 
		private void addButton(Container parent, String name) 
		{ 
			 JButton but = new JButton(name); 
			 but.setActionCommand(name); 
			 parent.add(but); 
		}

}
