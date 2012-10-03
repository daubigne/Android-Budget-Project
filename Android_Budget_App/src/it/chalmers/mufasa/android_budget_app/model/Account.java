package it.chalmers.mufasa.android_budget_app.model;

import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;

/*
 * A class representing a bank account.
 * 
 * @author Slurpo
 */
public class Account {

	private int id;
	private String name;
	private double balance;
	private List<Category> categoryList;
	private List<Transaction> transactionList;
	private List<BudgetItem> budgetItemList;
	private DataAccessor dataAccessor;
	private int nbrOfTransactions;

	private static Account instance = null;

	private PropertyChangeSupport pcs;

	private Account(Context context) {
		dataAccessor = new DataAccessor(context);
		if (dataAccessor.accountExists()) {
			setId(dataAccessor.getAccountId());
			setName(dataAccessor.getAccountName());
			setBalance(dataAccessor.getAccountBalance());
			
		} else {
			setId(Constants.ACCOUNT_ID);
			setName("account");
			setBalance(0.0);
			dataAccessor.addAccount(getName(),getBalance());
			categoryList = new ArrayList<Category>();
			transactionList = new ArrayList<Transaction>();
			budgetItemList = new ArrayList<BudgetItem>();
		}
		

		pcs = new PropertyChangeSupport(this);

	}

	public static Account getInstance(Context context) {
		if (instance == null) {
			instance = new Account(context);
		}
		return instance;
	}

	private void setId(int id) {
		this.id = id;
		if (dataAccessor.accountExists()) {
			dataAccessor.updateAccount(this);
		}

	}

	private void setName(String name) {
		this.name = name;
		if (dataAccessor.accountExists()) {
			dataAccessor.updateAccount(this);
		}
	}

	public void setBalance(double balance) {
		this.balance = balance;
		if (dataAccessor.accountExists()) {
			dataAccessor.updateAccount(this);
		}
	}

	public double getBalance() {
		return this.balance;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public List<BudgetItem> getBudgetItems() {
		updateBudgetItemList();
		return budgetItemList;
	}

	private void updateBudgetItemList() {
		budgetItemList.clear();
		budgetItemList.addAll(dataAccessor.getBudgetItems());
		pcs.firePropertyChange("BudgetItems Updated", null, null);
	}

	public void addBudgetItem(Category category, Double value) {
		dataAccessor.addBudgetItem(category, value);
	}

	public void removeBudgetItem(BudgetItem budgetItem) {
		dataAccessor.removeBudgetItem(budgetItem);
	}

	public List<Category> getCategories() {
		updateCategoryList();
		return categoryList;
	}

	private void updateCategoryList() {
		categoryList.clear();
		categoryList.addAll(dataAccessor.getCategories());
		pcs.firePropertyChange("Categories Updated", null, null);

	}

	public void addCategory(String name, Category parent) {
		dataAccessor.addCategory(name, parent);
		updateCategoryList();
	}

	public void removeCategory(Category category) {
		dataAccessor.removeCategory(category);
	}

	public List<Transaction> getTransactions(int nbrOfTransactions) {
		this.nbrOfTransactions = nbrOfTransactions;
		updateTransactionList();
		return transactionList;
	}

	private void updateTransactionList() {
		transactionList.clear();
		transactionList.addAll(dataAccessor.getTransactions(this, SortBy.DATE,
				SortByOrder.DESC, 0, nbrOfTransactions));
		pcs.firePropertyChange("Transactions Updated", null, null);
	}

	public void addTransaction(double amount, Date date, String name,
			Category category) {
		dataAccessor.addTransaction(amount, date, name, category);
		setBalance(dataAccessor.getAccountBalance());
	}

	public void removeTransaction(Transaction transaction) {
		dataAccessor.removeTransaction(transaction);
		setBalance(dataAccessor.getAccountBalance());
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(l);
	}

}
