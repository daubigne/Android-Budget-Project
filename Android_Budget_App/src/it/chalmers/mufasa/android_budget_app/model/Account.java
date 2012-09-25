package it.chalmers.mufasa.android_budget_app.model;

/*
 * A class representing a bank account.
 * 
 * @author Slurpo
 */
public class Account {
	
	//The id is generated by the SQLite database.
	private int id;
	private String name;
	private double balance;
	
	public Account(int id, String name, double balance){
		this.id = id;
		this.name = name;
		setBalance(balance);
		
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public double getBalance(){
		return this.balance;
	}

	public String getName(){
		return this.name;
	}
	
	public int getId(){
		return id;
	}
}
