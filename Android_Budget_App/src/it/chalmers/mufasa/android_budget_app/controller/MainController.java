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
		
		da.addCategory("first category", null);
		da.addCategory("second category", null);
		
		List<Category> catList = da.getCategories();
		
		Log.println(9,"MainController","Categories:");
		for(Category cat : catList) {
			Log.println(9,"MainController",cat.getName());
		}
		
		Account account = new Account(0,"account",2000);
		
		Transaction transaction1 = new Transaction(-200,new Date(2012,9,24),"first transaction",catList.get(0),account);
		Transaction transaction2 = new Transaction(-750,new Date(2012,9,23),"first transaction",catList.get(0),account);
		
		da.addTransaction(transaction1);
		da.addTransaction(transaction2);
		
		List<Transaction> list = da.getTransactions(account, SortBy.DATE, SortByOrder.DESC, 0, 2);
		
		Log.println(9,"MainController","Transactions:");
		for(Transaction t : list) {
			Log.println(9,"MainController",t.getName() + " " + t.getAmount());
		}
	}

	public void setBalance(double balance) {
		this.da.setAccountBalance(balance,0);
		model.setBalance(balance);
	}
	
	public  ArrayList<Transaction> getTransactionHistory(){
		return model.getTransactionHistory();
	}
	
	public void addTransaction(int amount, Date d, String name, Category c, Account acc){
		da.addTransaction(new Transaction(amount, d, name, c, acc));
	}

}
