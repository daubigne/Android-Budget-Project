package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class TransactionListModel {
	private Account account;
	private ArrayList<Transaction> transactionHistory;
	private PropertyChangeSupport pcs;
	
	public TransactionListModel(Account account) {
		this.account = new Account(1, "placeholder", account.getBalance());
		transactionHistory = new ArrayList<Transaction>();
		
		pcs = new PropertyChangeSupport(this);
	}
	
	public List<Transaction> getTransactionHistory(){
		return transactionHistory;
	}
	
	public void updateTransactionHistory(List<Transaction> transactions){
		transactionHistory.clear();
		
		transactionHistory.addAll(transactions);
		pcs.firePropertyChange("Transaction Updated", null, null);
		
	}
	
	public void setAccount(Account account){
		this.account = account;
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
