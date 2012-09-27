package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.TransactionListModel;

import java.util.Date;
import java.util.List;

import android.content.Context;

public class TransactionListController {
	
	private TransactionListModel model;
	private DataAccessor dataAccessor;
	
	public TransactionListController(Context context, TransactionListModel model) {
		this.model = model;
		this.dataAccessor = new DataAccessor(context);
		initModel();
		
	}
	
	public void initModel(){
		model.setAccount(dataAccessor.getAccount(1));
	}
	public void addTransaction(Double amount, Date date, String name, Category category, Account account){
		dataAccessor.addTransaction(amount, date, name, category, account);
		updateTransactionHistory();
	}
	
	public void updateTransactionHistory(){
		model.updateTransactionHistory(dataAccessor.getTransactions(model.getAccount(), SortBy.DATE, SortByOrder.ASC,0,100));
	}
	
	public void removeTransaction(Transaction t){
		dataAccessor.removeTransaction(t);
		updateTransactionHistory();	
	}
	
	public  List<Transaction> getTransactionHistory(){
		return model.getTransactionHistory();
	}
}
