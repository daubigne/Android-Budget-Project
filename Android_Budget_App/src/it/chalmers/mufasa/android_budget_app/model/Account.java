/*
 * Copyright © 2012 Mufasa developer unit
 *
 * This file is part of Mufasa Budget.
 *
 *	Mufasa Budget is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Mufasa Budget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Mufasa Budget.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.chalmers.mufasa.android_budget_app.model;

import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.AccountDay;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;

/**
 * A class that holds almost all data that user has stored in the app. This
 * class keeps the database updated. 
 * This is a singleton class.
 * 
 * @author Slurpo
 */
public final class Account {

	private int id;
	private String name;
	private double balance;
	private List<Category> categoryList;
	private List<Transaction> transactionList;
	private List<BudgetItem> budgetItemList;
	private DataAccessor dataAccessor;
	// The number of transactions that is to be retrieved from the database.
	private int nbrOfTransactions;

	private static Account instance = null;

	private PropertyChangeSupport pcs;

	private Account(Context context) {
		dataAccessor = new DataAccessor(context);

		// If the database has an account we retrieve the data from that
		// account.
		if (dataAccessor.accountExists()) {
			this.id = dataAccessor.getAccountId();
			this.name = dataAccessor.getAccountName(Constants.ACCOUNT_ID);
			this.balance = dataAccessor.getAccountBalance();
			// If it doesn't a new account is stored in the database.
		} else {
			setId(Constants.ACCOUNT_ID);
			setName("account");
			setBalance(0.0);
			dataAccessor.addAccount(getName(), getBalance());
		}
		categoryList = new ArrayList<Category>();
		transactionList = new ArrayList<Transaction>();
		budgetItemList = new ArrayList<BudgetItem>();

		pcs = new PropertyChangeSupport(this);

	}

	/**
	 * Returns the only instance of this class.
	 */
	public static Account getInstance(Context context) {
		if (instance == null) {
			instance = new Account(context);
		}
		return instance;
	}

	/**
	 * Sets the id of the account.
	 */
	private void setId(int id) {
		this.id = id;
		if (dataAccessor.accountExists()) {
			dataAccessor.updateAccount(this);
		}

	}

	/**
	 * Sets the name of the account.
	 */
	private void setName(String name) {
		this.name = name;
		if (dataAccessor.accountExists()) {
			dataAccessor.updateAccount(this);
		}
	}
	
	/**
	 * Returns the name of the account.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the id of the account.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets balance to the given amount.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
		if (dataAccessor.accountExists()) {
			dataAccessor.updateAccount(this);
		}
	}
	
	/**
	 * Fetches current account balance from the database.
	 */
	private void updateBalance() {
		setBalance(dataAccessor.getAccountBalance());
	}

	/**
	 * Returns current balance.
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Returns a list of AccountDays from the given date to today.
	 * @param from - The start date.
	 */
	public List<AccountDay> getAccountBalanceForEachDay(Date from) {
		return dataAccessor.getAccountBalanceForEachDay(from);
	}
	

	/**
	 * Returns the budget item that the user have saved.
	 */
	public List<BudgetItem> getBudgetItems() {
		return getBudgetItems(null);
	}

	/**
	 * Returns all budgetItems under a certain category,
	 */
	public List<BudgetItem> getBudgetItems(Category parent) {
		updateBudgetItemList(parent);
		return budgetItemList;
	}

	/**
	 * Returns the sum of all budget items under a specific categoryId.
	 * @param categoryId - The category to get budgetItems from.
	 */
	public Double getBudgetItemsSum(int categoryId) {
		return dataAccessor.getBudgetItemsSum(dataAccessor
				.getCategory(categoryId));
	}

	/**
	 * Updates the budgetItemList with data from the dataAccessor.
	 * @param parent - The category which the budget items are placed under.
	 */
	private void updateBudgetItemList(Category parent) {
		budgetItemList.clear();
		budgetItemList.addAll(dataAccessor.getBudgetItems(parent));
	}

	/**
	 * Stores a budget item in the list of budget items.
	 */
	public void addBudgetItem(Category category, Double value) {
		dataAccessor.addBudgetItem(category, value);
		pcs.firePropertyChange("BudgetItems Updated", null, null);
	}

	/**
	 * Removes the given budget item from the list of budget items.
	 */
	public void removeBudgetItem(BudgetItem budgetItem) {
		dataAccessor.removeBudgetItem(budgetItem);
		pcs.firePropertyChange("BudgetItems Updated", null, null);
	}
	
	/**
	 * Removes all budget items
	 */
	public void removeBudget() {
		dataAccessor.removeAllBudgetItems();
	}

	/**
	 * Returns a single saved category.
	 */
	public Category getCategory(int id) {
		return dataAccessor.getCategory(id);
	}

	/**
	 * Returns the categories that the user have saved.
	 */
	public List<Category> getCategories() {
		updateCategoryList();
		return new ArrayList<Category>(categoryList);
	}

	/**
	 * Returns the categories which has a certain parent category.
	 */
	public List<Category> getCategories(Category currentParentCategory) {
		categoryList.clear();
		categoryList.addAll(dataAccessor.getCategories(currentParentCategory));
		return new ArrayList<Category>(categoryList);
	}

	/**
	 * Updates the categoryList with data from the dataAccessor.
	 */
	private void updateCategoryList() {
		categoryList.clear();
		categoryList.addAll(dataAccessor.getCategories());
		pcs.firePropertyChange("Categories Updated", null, null);

	}

	/**
	 * Adds a category to the list of categories.
	 */
	public void addCategory(String name, Category parent) {
		dataAccessor.addCategory(name, parent);
		updateCategoryList();
	}

	/**
	 * Renames a category in the list of categories.
	 */
	public void editCategory(Category category, String newName) {
		dataAccessor.editCategory(category, newName);
		updateCategoryList();
	}

	/**
	 * Removes the given category from the list of categories.
	 */
	public void removeCategory(Category category) {
		dataAccessor.removeCategory(category);
		updateCategoryList();
	}

	/**
	 * Returns the transactions that the users have saved. Set parent to null if
	 * you want all transactions.
	 */
	public List<Transaction> getTransactions(int nbrOfTransactions,
			Category parent) {
		this.nbrOfTransactions = nbrOfTransactions;
		updateTransactionList(parent);
		return transactionList;
	}

	public List<Transaction> getTransactions(SortBy sortBy,
			SortByOrder sortByOrder, Category parent, Date from, Date to) {
		return dataAccessor.getTransactions(sortBy, sortByOrder, 0, Constants.NUMBER_OF_TRANSACTIONS,
				parent, from, to);
	}

	/**
	 * Returns the sum of all transactions in a specific category during a specific time span.
	 * @param from - The start date of the time span.
	 * @param to - The end date of the time span.
	 * @param categoryId - The id of the category which the transactions are placed in.
	 * @return
	 */
	public double getTransactionsSum(Date from, Date to, int categoryId) {

		double sum = 0.0;

		for (Transaction transaction : dataAccessor.getTransactions(
				SortBy.DATE, SortByOrder.DESC, 0, 10000,
				dataAccessor.getCategory(categoryId), from, to)) {
			sum += transaction.getAmount();
		}
		return sum;
	}

	/**
	 *  Updates the transaction list from the database.
	 * @param parent The category which to get transactions from.
	 */
	private void updateTransactionList(Category parent) {
		transactionList.clear();
		if (parent == null) {
			transactionList.addAll(dataAccessor.getTransactions(SortBy.DATE,
					SortByOrder.DESC, 0, nbrOfTransactions));
		} else {
			transactionList.addAll(dataAccessor.getTransactions(SortBy.DATE,
					SortByOrder.DESC, 0, nbrOfTransactions, parent));
		}
	}

	/**
	 * Adds a transaction to the list of transactions.
	 */
	public void addTransaction(double amount, Date date, String name,
			Category category) {
		dataAccessor.addTransaction(amount, date, name, category);
		updateBalance();
		pcs.firePropertyChange("Transactions Updated", null, null);

	}

	/**
	 * Removes the given transaction from the list of transactions.
	 */
	public void removeTransaction(Transaction transaction) {
		dataAccessor.removeTransaction(transaction);
		updateBalance();
		pcs.firePropertyChange("Transactions Updated", null, null);
	}

	/**
	 * Removes all transactions from the database.
	 */
	public void removeAllTransactions() {
		dataAccessor.removeAllTransactions();
	}

	/**
	 * Adds a property change listener to this.
	 * @param l- the listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

	/**
	 * Removes a property change listener from this.
	 * @param l- the listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(l);
	}

}
