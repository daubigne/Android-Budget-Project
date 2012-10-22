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
package it.chalmers.mufasa.android_budget_app.test.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import junit.framework.TestCase;

/**
 * A class that tests the Account class.
 * 
 * @author marcusisaksson
 * 
 */
public class AccountTest extends AndroidTestCase {

	private Account account;
	private Category category1;
	private Category category2;
	private Date date1;
	private Date date2;
	private Date date3;
	private Calendar cal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test");
		account = Account.getInstance(context);
		category1 = new Category("category1", 1,
				account.getCategory(Constants.INCOME_ID));
		category2 = new Category("category2", 2,
				account.getCategory(Constants.EXPENSE_ID));

	}

	/**
	 * Tests setters and getters for the account balance.
	 */
	public void testBalance() {
		account.setBalance(25.0);
		if (account.getBalance() != 25.0) {
			fail("Balance is not equal");
		}
	}

	/**
	 * Tests the getId mehtod.
	 */
	public void testId() {
		if (account.getId() != 1) {
			fail("ID is not equal");
		}
	}

	/**
	 * Tests add transaction, getTransactions and removeAllTransactions.
	 */
	public void testTransactions() {
		cal = new GregorianCalendar();
		cal.set(2012, 1, 1);
		date1 = cal.getTime();
		cal.set(2012, 1, 3);
		date2 = cal.getTime();
		cal.set(2012, 1, 5);
		date3 = cal.getTime();

		account.setBalance(0);

		account.removeAllTransactions();

		Transaction transaction1 = new Transaction(1, 3.0, date2,
				"transaction1", category1);
		Transaction transaction2 = new Transaction(2, 5.0, date2,
				"transaction2", category1);
		Transaction transaction3 = new Transaction(3, 10.0, date2,
				"transaction3", category2);

		account.addTransaction(transaction1.getAmount(),
				transaction1.getDate(), transaction1.getName(),
				transaction1.getCategory());
		account.addTransaction(transaction2.getAmount(),
				transaction2.getDate(), transaction2.getName(),
				transaction2.getCategory());
		account.addTransaction(transaction3.getAmount(),
				transaction3.getDate(), transaction3.getName(),
				transaction3.getCategory());

		if (account.getBalance() != -2.0) {
			fail("Balance isn't -2.0 it's " + account.getBalance());
		}

		if (account.getTransactions(10, null).size() != 3) {
			fail("The number of transaction isn't 3, it's "
					+ account.getTransactions(10, null).size());
		}

		getTransactionsByCategory();

		transactionsSum();

		account.removeTransaction(transaction3);

		if (account.getBalance() != 8.0) {
			fail("Balance isn't 8.0 it's " + account.getBalance());
		}
		if (account.getTransactions(100, null).size() != 2) {
			fail("The number of transaction isn't 2 it's "
					+ account.getTransactions(100, null).size());
		}

		account.removeAllTransactions();

		if (account.getTransactions(100, null).size() != 0) {
			fail("The number of transaction isn't 0 it's "
					+ account.getTransactions(100, null).size());
		}
	}

	/**
	 * Tests the getTransactionsByCategory method.
	 */
	private void getTransactionsByCategory() {
		if (account.getTransactions(10,
				account.getCategory(Constants.EXPENSE_ID)).size() != 1) {
			fail("The number of expense transactions isn't 1, it's "
					+ account.getTransactions(10,
							account.getCategory(Constants.EXPENSE_ID)).size());
		}

		if (account.getTransactions(10,
				account.getCategory(Constants.INCOME_ID)).size() != 2) {
			fail("The number of income transactions isn't 2, it's "
					+ account.getTransactions(10,
							account.getCategory(Constants.INCOME_ID)).size());
		}
	}

	/**
	 * Tests the getTransactionSum method.
	 */
	private void transactionsSum() {

		Double incomeSum = account.getTransactionsSum(date1, date3,
				Constants.INCOME_ID);

		assertEquals("The sum of income transactions is wrong", 8.0, incomeSum);
		if (incomeSum != 8.0) {
			fail("The sum of income transactions isn't 8.0, it's " + incomeSum);
		}

		Double expenseSum = account.getTransactionsSum(date1, date3,
				Constants.EXPENSE_ID);

		if (expenseSum != 10.0) {
			fail("The sum of expense transactions isn't 10.0, it's "
					+ expenseSum);
		}
	}

	/**
	 * 
	 */
	public void testBudgetItems() {

		int budgetListSize = account.getBudgetItems().size();
		double budgetStartSum = account.getBudgetItemsSum(Constants.INCOME_ID);
		
		BudgetItem budgetItem1 = new BudgetItem(1, category1, 50.0);

		BudgetItem budgetItem2 = new BudgetItem(2, category2, 30.0);

		BudgetItem budgetItem3 = new BudgetItem(3, category1, 100.0);

		account.addBudgetItem(budgetItem1.getCategory(), budgetItem1.getValue());
		account.addBudgetItem(budgetItem2.getCategory(), budgetItem2.getValue());
		account.addBudgetItem(budgetItem3.getCategory(), budgetItem3.getValue());

		if(account.getBudgetItemsSum(Constants.INCOME_ID) - budgetStartSum != 150.0){
			fail("The income budget sum isn't 150.0, it's " + account.getBudgetItemsSum(Constants.INCOME_ID));
		}
		account.removeBudgetItem(budgetItem1);

		if (account.getBudgetItems().size() - budgetListSize != 2) {
			fail("The number of added budget items isn't 2 it's "
					+ account.getBudgetItems().size());
		}

	}

	public void tesCategories() {

		int budgetListSize = account.getBudgetItems().size();
		Category category3 = new Category("category3", 3, account.getCategory(Constants.INCOME_ID));

		account.addCategory(category1.getName(), category1.getParent());
		account.addCategory(category2.getName(), category2.getParent());
		account.addCategory(category3.getName(), category3.getParent());

		if(account.getCategories(account.getCategory(Constants.INCOME_ID)).size() != 2){
			fail("The number of income transactions isn't 2, it's " + account.getCategories(account.getCategory(Constants.INCOME_ID)).size());
		}
		
		account.removeCategory(category2);

		if (account.getCategories().size() != 2) {
			fail("The number of categories isn't 2 it's "
					+ account.getCategories().size());
		}
		
		

	}
}
