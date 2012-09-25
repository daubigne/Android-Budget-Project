package it.chalmers.mufasa.android_budget_app.model;

import java.util.Date;
/**
 * version 1
 * @author daubigne
 * 
 * A class that is a transaction for the given account
 *
 */
public class Transactions {
	private double amount;
	private Date date;
	private String name;
	private Category cat;
	private Account ac;
		
	public void Transaction(int a, Date d, String name, Category c, Account acc){
		this.amount = a;
		this.date = d;
		this.name = name;
		this.cat = c;
		this.ac = acc;
			
	}
	public double getAmount(){
		return this.amount;
	}
	public String getName(){
		return this.name;
	}
	public Date getDate(){
		Date temp = this.date;
		return temp;
	}
	public Category getCatergory(){
		Category temp = this.cat;
		return temp;
	}
	public Account getAccount(){
		Account temp = this.ac;
		return temp;
	}

}
