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
	

	private void setCurrentMainCategory(int id){
		currentMainCategory = account.getCategory(id);
	}
	
	/**
	 * Sets the current main category to the income category.
	 */
	public void switchToIncome() {
		setCurrentMainCategory(Constants.INCOME_ID);
	}
	
	/**
	 * Sets the current main category to the expense category.
	 */
	public void switchToExpenses() {
		setCurrentMainCategory(Constants.EXPENSE_ID);
	}
	
	/**
	 * Returns the current main category.
	 */
	public Category getCurrentMainCategory(){
		return currentMainCategory;
		
	}
}
