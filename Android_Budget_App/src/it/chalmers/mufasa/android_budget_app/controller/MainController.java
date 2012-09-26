package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class MainController {
	
	private MainModel model;
	private DataAccessor dataAccessor;

	public MainController(Context context, MainModel model) {
		this.model = model;
		this.dataAccessor = new DataAccessor(context);
		try {
			model.setBalance(this.dataAccessor.getAccount(1).getBalance());
		} catch (IllegalArgumentException e) { // Account 0 does not exist...
			this.dataAccessor.addAccount("My Account",0);
			model.setBalance(dataAccessor.getAccount(1).getBalance());
		}
	}

	public void setBalance(double balance) {
		this.dataAccessor.setAccountBalance(dataAccessor.getAccount(1),balance);
		model.setBalance(balance);
	}
	
	public  ArrayList<Transaction> getTransactionHistory(){
		return model.getTransactionHistory();
	}
	
	public void addTransaction(Double amount, Date date, String name, Category category, Account account){
		dataAccessor.addTransaction(amount, date, name, category, account);
	}

}
