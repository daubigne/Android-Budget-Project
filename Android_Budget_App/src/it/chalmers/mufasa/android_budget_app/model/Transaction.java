package it.chalmers.mufasa.android_budget_app.model;

import java.util.Date;
/**
 * version 1
 * @author daubigne
 * 
 * A class that is a transaction for the given account
 *
 */
public class Transaction {
	private double amount;
	private Date date;
	private String name;
	private Category category;
	private Account account;
	private int id;
		
	public Transaction(int id, double amount, Date date, String name, Category category, Account account){
		this.amount = amount;
		this.date = date;
		this.name = name;
		this.category = category;
		this.account = account;
		this.id = id;
			
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
	public Category getCategory(){
		Category temp = this.category;
		return temp;
	}
	public Account getAccount(){
		Account temp = this.account;
		return temp;
	}
	public int getId(){
		return id;
	}

}
