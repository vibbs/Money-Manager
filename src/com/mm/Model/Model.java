package com.mm.Model;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;

import com.mm.GUI.GUI;

public class Model {
	
	private String cat;
	private float amt;
	private float sav;
	private float remamt;
	private int modeofpay; // 0 credit 1 debit 2 cash
	private int type; // 0-9 diff meaning
	
	private String[] mode = {"credit" , "debit" , "cash"}; 
	/**
	 * @return the cat
	 */
	public String getCat() {
		return cat;
	}




	/**
	 * @return the modeofpay
	 */
	public int getModeofpay() {
		return modeofpay;
	}




	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}




	/**
	 * @return the mode
	 */
	public String[] getMode() {
		return mode;
	}




	/**
	 * @return the typ
	 */
	public String[] getTyp() {
		return typ;
	}


	private String[] typ = {"bill" , "rent" , "groceries"}; // can be extended
	
	static ArrayList<Model> model = new ArrayList<Model>();
	private static PrintWriter fileWriter;
	

	public Model(String ca, float am,float sa,float rem, int m, int t) {
		cat = ca;
		amt = am;
		sav = sa;
		remamt = rem ;
		modeofpay = m;
		type = t;
		
	}




	/**
	 * @return the amt
	 */
	public float getAmt() {
		return amt;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "cat=" + cat + ", amt=" + amt + ", sav=" + sav
				+ ", remamt=" + remamt + ", modeofpay=" + mode[modeofpay] + ", type="
				+ typ[type] ;
	}




	/**
	 * @return the sav
	 */
	public float getSav() {
		return sav;
	}

	/**
	 * @return the remamt
	 */
	public float getRemamt() {
		return remamt;
	}
	
	
public static void main(String[] args) {
		
		Model m ;
		m = new Model("Income", 10000 , 2000 , 8000 , 0 , 0);
		model.add(m);
		m = new Model("Expense", 100 , 2000 , 7900 , 1 , 1);
		model.add(m);
		m = new Model("Expense", 100 , 2000 , 7800 , 0 , 2);
		model.add(m);
		m = new Model("Expense", 100 , 2000 , 7700 , 2 , 0);
		model.add(m);
		read();
	}


public static void read(){
	
   
	
		try{
			fileWriter = new PrintWriter("userFile.txt"); 
			//short for  loop to write the list of user objects 
			for (Model u : model)
			{
				fileWriter.print(u.getCat()+" "+ u.getAmt()+" "+u.getSav()+" "+u.getRemamt()+" "+u.getModeofpay()+" "+u.getType()+" ");
			}
			//closing the file after writing
			fileWriter.close();  }
		catch (FileNotFoundException e)
		{
			System.out.println("Error: File cannot be located!");
			
			//new_User();
			
		}
        catch (IOException e)
		{
			System.out.println("Error:IOException");
			
			//new_User();
			
		}
      
      
	}

	

}
