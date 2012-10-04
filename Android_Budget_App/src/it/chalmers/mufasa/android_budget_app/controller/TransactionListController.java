package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.TransactionListModel;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortByOrder;

import java.util.Date;
import java.util.List;

import android.content.Context;

/**
 * A class for saving and updating the users transactions.
 * 
 * @author: slurpo
 */
public class TransactionListController {

	private Account account;

	public TransactionListController(Account account) {
		this.account = account;

	}

	/**
	 * Stores the given data as a transaction in the model and the database.
	 */
	public void addTransaction(Double amount, Date date, String name,
			Category category) {

		account.addTransaction(amount, date, name, category);
	}

	/**
	 * Removes the given transaction.
	 */
	public void removeTransaction(Transaction transaction) {
		account.removeTransaction(transaction);
	}
	
	/**
	 * Returns a list of transactions.
	 * @param nbrOfTransactions. The number of transactions to be retrieved.
	 */
	public List<Transaction> getTransactions(int nbrOfTransactions){
		return account.getTransactions(nbrOfTransactions);
	}
}
