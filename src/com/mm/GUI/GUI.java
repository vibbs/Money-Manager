package com.mm.GUI;
import java.awt.event.*;

import javax.swing.*;

import com.mm.Controller.*;
import com.mm.Model.Model;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame  implements ActionListener{
	ArrayList<Model> model = new ArrayList<Model>();
	JTabbedPane tabbedPane = new JTabbedPane();
    
    JPanel loginCard = new JPanel(new FlowLayout());
    JButton submitButton = new JButton("Submit");
    JButton regButton = new JButton("Register");
    JButton forgotButton = new JButton("Forgot Password");
    
    //JPanel home = new JPanel(new BorderLayout());
    JFrame home = new JFrame("Home");
    
    JPanel buttonPanel = new JPanel( new GridLayout(5, 1));
    
    JButton incomeButton = new JButton("Enter Income");
    JButton expenseButton = new JButton("Enter Expense");
    JButton modButton = new JButton("Modify");
    JButton viewButton = new JButton("View");
    JButton logout = new JButton("Logout");
    JButton logout2 = new JButton("Logout");
    JTextField nam = new JTextField("", 10);
    JTextField pas = new JTextField("", 10);
    JTextField pas1 = new JTextField("", 10);
    
    JTextField namA = new JTextField("", 10);
    JTextField pasA = new JTextField("", 10);
   
    
    JTextArea data = new JTextArea("Registered Users\n1\n2\n3\n4\n5\n6\n7\n8\n9\n1\n2\n3\n4\n5\n6\n7\n8\n9\n0\n", 3, 40);
    JScrollPane userPane = new JScrollPane( data );
    
    JPanel name = new JPanel( new GridLayout(1, 3));
    JPanel pword = new JPanel( new GridLayout(1, 3));

	public GUI() {
		super("Money Manager Login");
        setBounds(200, 200, 300, 250);
        
        //-- The Login Screen --------------------------------------------------
        
        name.add(new JLabel("              "));
        name.add(nam);
        name.add(new JLabel(" Username"));
        
        
        pword.add(new JLabel("               "));
        pword.add(pas);
        pword.add(new JLabel(" Password"));
        
        loginCard.add(name);
        loginCard.add(pword);
        loginCard.add( submitButton);
        loginCard.add(logout2);
        loginCard.add(regButton);
        loginCard.add(forgotButton);
        logout2.setVisible(false);
        //-- The home Screen --------------------------------------------------
        buttonPanel.add( incomeButton );
        buttonPanel.add( expenseButton );
        buttonPanel.add( modButton );
        buttonPanel.add( viewButton );
        buttonPanel.add( logout );
        home.setMaximumSize( new Dimension(  600, 50) );
		  
        home.add(buttonPanel, "South");
        JPanel d = new JPanel();
        JLabel picLabel = new JLabel(new ImageIcon("MMICON.png"));
		   d.add(picLabel);
		   d.setMaximumSize( new Dimension(  600, 80) );
		   JPanel a = new JPanel();
		   a.add(d,"North");
        //a.add(home,"Center");
       home.add(a);
       home.setBounds(getBounds());
        
        home.setVisible(false);
        //-- The three tabs and the content pane -------------------------------
        tabbedPane.addTab( "Log In", loginCard);
        //tabbedPane.addTab( "Home", a);
        
        Container pane = getContentPane();
        pane.add(tabbedPane, BorderLayout.CENTER);
        
        incomeButton.addActionListener(this);
        expenseButton.addActionListener(this);
        modButton.addActionListener(this);
        viewButton.addActionListener(this);
        logout.addActionListener(this);
        logout2.addActionListener(this);
        regButton.addActionListener(this);
        
        Color lightBlue= new Color(52428);  
		   pane.setBackground(lightBlue);
		   home.setBackground(lightBlue);
		   loginCard.setBackground(lightBlue);
		   buttonPanel.setBackground(lightBlue);
		   d.setBackground(lightBlue);
		   name.setBackground(lightBlue);
		   pword.setBackground(lightBlue);
		   a.setBackground(lightBlue);
        
        submitButton.addActionListener(this);
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed( ActionEvent e) {
        System.out.println("Button pressed.");
        Object source = e.getSource();
        if(source == submitButton)
		{
        	String tmp = nam.getText();
        	String tmp2 = "****";
        	//System.out.println("tmp"+tmp+"name"+nam.getText());
        	if(nam.getText().equals(tmp)&&pas.getText().equals(tmp2)){
			home.setVisible(true);
			loginCard.setVisible(false);
			name.setVisible(false);
			pword.setVisible(false);
			submitButton.setVisible(false);
			logout2.setVisible(true);
			JOptionPane.showMessageDialog(null, "Login Successful");
			
			System.out.println("Login Successful");
			repaint(); }
        	else
		{JOptionPane.showMessageDialog(null, "Incorrect User Name or Password");
        	System.out.println("Login Failed");}
        		
        }
		else
		if(source == incomeButton)
		{
			Income demo = new Income();
			demo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			System.out.println("Income button pressed.");
			repaint(); 
        }
		else if(source == expenseButton)
		{
			Expense demo = new Expense();
			demo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			System.out.println("Expense button pressed.");
	        repaint();
	         
		}
		else if(source == modButton)
		{
			Modify demo = new Modify();
			demo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			System.out.println("Modify button pressed.");
	        repaint();
	        
		}
		else if(source == viewButton)
		{
			View v = new View();
			
			System.out.println("View button pressed.");
	        repaint();
	        
		}
		else if(source == logout||source == logout2)
		{
			System.out.println("logout button pressed.");
			super.dispose();
			//JFrame.ABORT;
			System.exit(0);
	        
		}
		else if(source == regButton)
		{
			JFrame reg = new JFrame("Registration");
			reg.setBounds(getBounds());
			JPanel a = new JPanel( new GridLayout(1, 3));
		    JPanel b = new JPanel( new GridLayout(2, 6));
		    Color lightBlue= new Color(52428);  
		    JButton submit = new JButton("Submit");
		    
			
			a.add(new JLabel("              "));
	        a.add(namA);
	        a.add(new JLabel(" Username"));
	        
	        
	        b.add(new JLabel("               "));
	        b.add(pasA);
	        b.add(new JLabel(" Password"));
	        
	        b.add(new JLabel("               Re-enter : "));
	        b.add(pas1);
	        b.add(new JLabel(" Password"));
	        
	        JPanel re = new JPanel(new FlowLayout());
	        a.setBackground(lightBlue);
	        b.setBackground(lightBlue);
			re.add(a);
			re.add(b);
			
			re.add(submit);
			re.setBounds(getBounds());
			
			re.setBackground(lightBlue);
			reg.add(re);
			reg.setVisible(true);
			
			submit.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e){
		            	if(pas.getText().equals(pas1.getText())){
		            	JOptionPane.showMessageDialog(null, "Registration Succefull");}
		            	else
		            		JOptionPane.showMessageDialog(null, "Passwords don't match!");
		            }
		        });
			
			loginCard.setVisible(true);
			name.setVisible(true);
			pword.setVisible(true);
			System.out.println("Registration button pressed.");
	        repaint();
	        
		}
		
    }   

}
