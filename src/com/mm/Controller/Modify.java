package com.mm.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mm.Date.DatePicker;
import com.mm.Model.Model;

public class Modify extends JFrame  implements ActionListener{
	ArrayList<Model> model = new ArrayList<Model>();
	
	JButton submitButton = new JButton("Submit");
	JButton cancelButton = new JButton("Cancel");
	ImageIcon img = new ImageIcon("MMICON.png");
	StringBuilder S = new StringBuilder("Listing of all inputs : \n");
	JTextArea textArea = new JTextArea(40, 50);
    JScrollPane userPane = new JScrollPane(textArea);

	public Modify() {
		
		 
	   
			
			
	
				setBounds(100, 100, 450, 350); //window location, window size
				
				
				   JPanel a = new JPanel();
				   JPanel b = new JPanel();
				   JPanel c = new JPanel();
				   JPanel d = new JPanel();
				   
					JLabel picLabel = new JLabel(new ImageIcon("MMICON.png"));
				   d.add(picLabel);
				 
				   a.setMaximumSize( new Dimension(  450, 50) );
				   b.setMaximumSize( new Dimension(  450, 50) );
				   c.setMaximumSize( new Dimension(  450, 50) );
				   d.setMaximumSize( new Dimension(  450, 80) );

				  
			        
			        c.add(submitButton);
				   
				   JPanel panel = new JPanel();
				   panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				   panel.add(d);
				   panel.add(a);
				   panel.add(b);
				   panel.add(c); 
				   
				   Color lightBlue= new Color(52428);  
				   panel.setBackground(lightBlue);
				   a.setBackground(lightBlue);
				   b.setBackground(lightBlue);
				   c.setBackground(lightBlue);
				   d.setBackground(lightBlue);
				   
				   

				   //int result = JOptionPane.showConfirmDialog(null, panel, "Please enter values.", JOptionPane.OK_CANCEL_OPTION);
				   Container pane = getContentPane();
			        pane.add(panel, BorderLayout.CENTER);
		        setVisible(true);
		        setResizable(false); 
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        
		        
		        
		        
//-------------------------------------file read---------------------------------------------
		        
		        try
				{//to read from the file and check if it exist or not!
					FileInputStream file = new FileInputStream("userFile.dat"); 
			        ObjectInputStream obj = new ObjectInputStream(file); 
			        model = (ArrayList<Model>) obj.readObject();
			        file.close(); 
			        // Listing the users on the text area.
			        
					
				}	
				catch (FileNotFoundException ev)
				{
					System.out.println("Error: File cannot be located!");
					
					//new_User();
					
				}
		        catch (IOException ev)
				{
					System.out.println("Error:IOException");
					
					//new_User();
					
				}
		        catch (ClassNotFoundException ev)
				{
					System.out.println("Error:Incorrect File format");
					
					//new_User();
					
				}
				
				/** The main reason for the below try and block is to create the file if it doesn't exist */
				try
				{   //writing the data to the file entered by the user
					FileOutputStream file = new FileOutputStream("userFile.dat"); 
			        ObjectOutputStream obj  = new ObjectOutputStream(file); 
			        obj.writeObject(model); 
			        file.close(); 
			        S.append("---------------------\n");
			        for(Model u: model)
					{
			        	S.append(u.toString()); //appending user details to string builder
			        	S.append("\n");
					} 
			        textArea.append(new String(S));
			       
			      
				}
				catch (FileNotFoundException ev)
				{
					System.out.println("Error: File cannot be located!");
					
					//new_User();
					
				}
		        catch (IOException ev)
				{
					System.out.println("Error:IOException");
					
					//new_User();
					
				}
		       
				finally
				{
					try{FileOutputStream file = new FileOutputStream("userFile.dat"); 
			        ObjectOutputStream obj  = new ObjectOutputStream(file); 
			        obj.writeObject(model); 
			        file.close(); }
					catch (FileNotFoundException ev)
					{
						System.out.println("Error: File cannot be located!");
						
						//new_User();
						
					}
			        catch (IOException ev)
					{
						System.out.println("Error:IOException");
						
						//new_User();
						
					}
			      
			      
				}       
	}
	//modify
		public void modify()
		{
			//will have to see how to get this functionality working
		}
		
		 public void actionPerformed( ActionEvent e) {
		        System.out.println("Button pressed.");
		        repaint();
		    }   
		 public static void main(String[] args) {
		        Modify demo = new Modify();
		        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }
		 
		private void addButton(Container parent, String name) 
		{ 
			 JButton but = new JButton(name); 
			 but.setActionCommand(name); 
			 parent.add(but); 
		}
		
		

}
