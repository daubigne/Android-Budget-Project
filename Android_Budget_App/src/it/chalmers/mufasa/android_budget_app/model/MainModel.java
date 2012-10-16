 /*
  * Copyright © 2012 Mufasa developer unit
  *
  * This file is part of Mufasa Budget.
  *
  *	Mufasa Budget is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Mufasa Budget is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with Mufasa Budget.  If not, see <http://www.gnu.org/licenses/>.
  */
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
		//account = new Account(1, "placeholder", balance);
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
