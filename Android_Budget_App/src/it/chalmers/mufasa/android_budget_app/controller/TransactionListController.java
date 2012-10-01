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
/**
 * A class for saving and updating the users transactions.
 * @author: slurpo
 */
public class TransactionListController {
	
	private static final int nrOfTransactionsInList = 100;

	private TransactionListModel model;
	private DataAccessor dataAccessor;

	public TransactionListController(Context context, TransactionListModel model) {
		this.model = model;
		this.dataAccessor = new DataAccessor(context);
		updateAccount();
		updateTransactionHistory();

	}

	/**
	 * Stores the given data as a transaction in the model and the database.
	 */
	public void addTransaction(Double amount, Date date, String name,
			Category category, Account account) {
		dataAccessor.addTransaction(amount, date, name, category, account);
		updateAccount();
		updateTransactionHistory();
	}

	/**
	 * Updates the account in the model.
	 */
	private void updateAccount() {
		model.setAccount(dataAccessor.getAccount(dataAccessor.getSettings()
				.getCurrentAccountId()));
	}

	/**
	 * Updates the transaction history in the model.
	 */
	private void updateTransactionHistory() {
		model.updateTransactionHistory(dataAccessor.getTransactions(
				model.getAccount(), SortBy.DATE, SortByOrder.DESC, 0, nrOfTransactionsInList));
	}

	/**
	 * Removes the given transaction from the database and the model
	 */
	public void removeTransaction(Transaction transaction) {
		dataAccessor.removeTransaction(transaction);
		updateAccount();
		updateTransactionHistory();
	}
}
