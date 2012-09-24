package it.chalmers.mufasa.android_budget_app.model;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
	
	List<ModelListener> listeners;

	double balance;
	
	public MainModel() {
		this.listeners = new ArrayList<ModelListener>();
		this.balance = 0;
	}
	
	public double getBalance() {
	    	return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
		this.notifyChangeListeners();
	}
	
	public void addChangeListener(ModelListener listener) {
		listeners.add(listener);
		listener.onChange(this);
	}
	
	public void notifyChangeListeners() {
		for(ModelListener l : listeners) {
			l.onChange(this);
		}
	}
	
}
