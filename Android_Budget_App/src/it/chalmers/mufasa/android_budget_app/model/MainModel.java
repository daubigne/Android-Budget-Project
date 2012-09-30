package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MainModel{
	
	private Account account;
	private double balance;
	private ArrayList<Transaction> transactionHistory;
	private PropertyChangeSupport pcs;
	
	public MainModel() {
		account = new Account(1, "placeholder", balance);
		this.balance = 0;
		transactionHistory = new ArrayList<Transaction>();
		
		pcs = new PropertyChangeSupport(this);
	}
	
	public double getBalance() {
	    	return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
		pcs.firePropertyChange("Balance Updated", null, null);
	}
	
	public List<Transaction> getTransactionHistory(){
		return transactionHistory;
	}
	   
	public void updateTransactionHistory(List<Transaction> transactions){
		transactionHistory.clear();
		
		transactionHistory.addAll(transactions);
		pcs.firePropertyChange("Transaction Updated", null, null);
		
	}
	
	public Account getAccount(){
		return account;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
	 	pcs.addPropertyChangeListener(l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l) {
	 	pcs.removePropertyChangeListener(l);
	}
	
}
