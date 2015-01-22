package com.mm.Graph;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import org.jfree.data.xy.*;
import org.jfree.chart.renderer.xy.*;

import com.mm.Model.Model;

public class Graph extends JFrame {
	
	ArrayList<Model> model = new ArrayList<Model>();
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
    
    
	public Graph() {
		
		 super("Money Manager");
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
        
        //-----------------------graph----------------------------------
       
		 
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
      				
      				
	}
	
	 private JPanel createChartPanel() {
		 Model m = model.get(model.size()-1);
		 String chartTitle = "Savings: "+m.getSav()+ "$, Remaining Amount: " + m.getRemamt()+"$";
		
		    String xAxisLabel = "X";
		    String yAxisLabel = "Y";
		 
		    XYDataset dataset = createDataset();
		 
		    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
		            xAxisLabel, yAxisLabel, dataset);
		 
		    return new ChartPanel(chart);
	    }
	 
	    private XYDataset createDataset() {
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        XYSeries series1 = new XYSeries("Expense");//red
	        XYSeries series2 = new XYSeries("Remaining Amt");//blue
	        XYSeries series3 = new XYSeries("Savings");//green
	        
	        
	        float i1 = 0;
	     
	        System.out.println(model.size());
	        for(Model k : model)
	        {
	        	series1.add(i1 , k.getAmt() );
	        	series2.add(i1 , k.getRemamt() );
	        	series3.add(i1 , k.getSav() );
	        	
	        	i1++;
	        	
	        }
	       
	       
	     
	        dataset.addSeries(series1);
	        dataset.addSeries(series2);
	        dataset.addSeries(series3);
	     
	        return dataset;
	    }
	
	
	  public static void main(String[] args) {
	        Graph demo = new Graph();
	    }

}
