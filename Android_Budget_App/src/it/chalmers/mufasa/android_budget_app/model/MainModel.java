package it.chalmers.mufasa.android_budget_app.model;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class MainModel {
	
	List<ModelListener> listeners;
	
	DataAccessor da;
	
	int balance;
	
	public MainModel(Context context) {
		this.listeners = new ArrayList<ModelListener>();
		this.da = new DataAccessor(context);
		this.balance = 0;
	}
	
	public int getBalance() {
	    	this.balance = this.da.getAccountBalance(0);
	    	return this.balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
		this.da.setAccountBalance(balance, 0);
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
