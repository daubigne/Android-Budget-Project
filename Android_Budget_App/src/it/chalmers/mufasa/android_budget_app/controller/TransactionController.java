package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class for saving and updating the users transactions.
 * 
 * @author: slurpo
 */
public class TransactionController {
	
	boolean editMode = false;
	private Account account;
	Category currentMainCategory;
	

	public TransactionController(Account account) {
		this.account = account;
		this.currentMainCategory = account.getCategory(1);


	}

	/**
	 * Stores the given data as a transaction in the model and the database.
	 */
	public void addTransaction(Double amount, Date date, String name,
			Category category) {

		account.addTransaction(amount, date, name, category);
	}

	public void saveAllTransactions(List<Transaction> list) {
		replaceTransactions(list);
	}
	
	public void replaceTransactions(List<Transaction> transactions) {
		ArrayList<Transaction> removeList = (ArrayList<Transaction>)getTransactions(Constants.NUMBER_OF_TRANSACTIONS);
		
		for(Transaction item : removeList) {
			removeTransaction(item);
		}
		
		for(Transaction item : transactions) {
			addTransaction(item.getAmount(), item.getDate(), item.getName(), item.getCategory());
		}
	}
	
	public void newTransaction(){
		account.addTransaction(0.0, new Date(), "", this.getCurrentMainCategory());
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
		return account.getTransactions(nbrOfTransactions, getCurrentMainCategory());
	}
		
	public void setEditMode(boolean set) {
		if(this.editMode != set) {
			this.editMode = set;
		}
	}
	
	public boolean isEditMode() {
		return this.editMode;
	}
	
	public void switchToIncome() {
		setCurrentMainCategory(Constants.INCOME_ID);
	}
	
	public void switchToExpenses() {
		setCurrentMainCategory(Constants.EXPENSE_ID);
	}
	
	private void setCurrentMainCategory(int id){
		currentMainCategory = account.getCategory(id);
	}
	
	public Category getCurrentMainCategory(){
		return currentMainCategory;
	}
}
