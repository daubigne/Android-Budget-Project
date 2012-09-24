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
	MainModel model;

	DataAccessor da;

	public MainController(Context context, MainModel model) {
		this.model = model;
		this.da = new DataAccessor(context);
		try {
			model.setBalance(this.da.getAccount(0).getBalance());
		} catch (IllegalArgumentException e) { // Account 0 does not exist...
			model.setBalance(0);
		}
	}

	public void setBalance(double balance) {
		this.da.setAccountBalance(balance,0);
		model.setBalance(balance);
	}
	
	public  ArrayList<Transaction> getTransactionHistory(){
		return model.getTransactionHistory();
	}
	
	public void addTransaction(Double amount, Date date, String name, Category category, Account account){
		da.addTransaction(amount, date, name, category, account);
	}

}
