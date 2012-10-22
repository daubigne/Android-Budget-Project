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

import java.util.Date;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class AccountTest extends AndroidTestCase {

	private Account account1;
	private Category category1;
	private Category category2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test");
		account1 = Account.getInstance(context);
		category1 = new Category("category1", 1, null);
		category2 = new Category("category2", 2, null);

	}

	public void testBalance() {
		account1.setBalance(25.0);
		if (account1.getBalance() != 25.0) {
			fail("Balance is not equal");
		}
	}

	public void testId() {
		if (account1.getId() != 1) {
			fail("ID is not equal");
		}
	}

	public void testTransactions() {
		account1.setBalance(0);
		Transaction transaction1 = new Transaction(1, 3.0, new Date(),
				"transaction1", category1);
		Transaction transaction2 = new Transaction(2, 5.0, new Date(),
				"transaction2", category1);
		Transaction transaction3 = new Transaction(3, 10.0, new Date(),
				"transaction3", category1);

		account1.addTransaction(transaction1.getAmount(),
				transaction1.getDate(), transaction1.getName(),
				transaction1.getCategory());
		account1.addTransaction(transaction2.getAmount(),
				transaction2.getDate(), transaction2.getName(),
				transaction2.getCategory());
		account1.addTransaction(transaction3.getAmount(),
				transaction3.getDate(), transaction3.getName(),
				transaction3.getCategory());
		account1.removeTransaction(transaction3);
		if (account1.getBalance() != 8.0) {
			fail("Balance isn't 8.0 it's " + account1.getBalance());
		}
		if (account1.getTransactions(100, null).size() != 2) {
			fail("The number of transaction isn't 2 it's "
					+ account1.getTransactions(100, null).size());
		}

	}

	public void testBudgetItems() {

		int budgetListSize = account1.getBudgetItems().size();
		BudgetItem budgetItem1 = new BudgetItem(1, category1, 50.0);

		BudgetItem budgetItem2 = new BudgetItem(2, category2, 30.0);

		BudgetItem budgetItem3 = new BudgetItem(3, category1, 100.0);

		account1.addBudgetItem(budgetItem1.getCategory(),
				budgetItem1.getValue());
		account1.addBudgetItem(budgetItem2.getCategory(),
				budgetItem2.getValue());
		account1.addBudgetItem(budgetItem3.getCategory(),
				budgetItem3.getValue());

		account1.removeBudgetItem(budgetItem1);

		if (account1.getBudgetItems().size() - budgetListSize != 2) {
			fail("The number of added budget items isn't 2 it's "
					+ account1.getBudgetItems().size());
		}

	}

	public void tesCategories() {

		Category category3 = new Category("category3", 3, category1);

		account1.addCategory(category1.getName(), category1.getParent());
		account1.addCategory(category2.getName(), category2.getParent());
		account1.addCategory(category3.getName(), category3.getParent());
		account1.removeCategory(category2);

		if (account1.getCategories().size() != 2) {
			fail("The number of categories isn't 2 it's "
					+ account1.getCategories().size());
		}

	}

}
