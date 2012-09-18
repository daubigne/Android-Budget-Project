package it.chalmers.mufasa.android_budget_app.model;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
	
	List<ModelListener> listeners;
	
	int balance;
	
	public MainModel() {
		this.setBalance(0);
		this.listeners = new ArrayList<ModelListener>();
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
		this.notifyChangeListeners();
		//TODO: Save in database
	}
	
	public void addChangeListener(ModelListener listener) {
		
	}
	
	public void notifyChangeListeners() {
		for(ModelListener l : listeners) {
			l.onChange(this);
		}
	}
	
}
