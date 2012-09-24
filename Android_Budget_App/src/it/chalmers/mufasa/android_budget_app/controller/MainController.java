package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

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
		
		Category category = new Category("first category");
		Account account = new Account(0,"account",200);
		
		Transaction transaction1 = new Transaction(-200,new Date(2012,9,24),"first transaction",category,account);
		
		da.addTransaction(transaction1);
		
		List<Transaction> list = da.getTransactions(account, SortBy.DATE, SortByOrder.DESC, 0, 1);
		
		for(Transaction t : list) {
			Log.println(9,"MainController",t.getName() + " " + t.getAmount());
		}
	}

	public void setBalance(double balance) {
		this.da.setAccountBalance(balance,0);
		model.setBalance(balance);
	}
	
	public void addTransaction(int amount, Date d, String name, Category c, Account acc){
		da.addTransaction(new Transaction(amount, d, name, c, acc));
	}

}
