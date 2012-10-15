/*
 * Copyright � 2012 Mufasa developer unit
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
package it.chalmers.mufasa.android_budget_app.test.model.database;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.util.Date;
import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class DatabaseTest extends AndroidTestCase {

	DataAccessor dataAccessor;
	RenamingDelegatingContext context;

	protected void setUp() throws Exception {
		super.setUp();

		context = new RenamingDelegatingContext(getContext(), "_test"); // This
																		// will
																		// start
																		// with
																		// fresh
																		// userdata

		dataAccessor = new DataAccessor(context);
	}

	// public void testAddAccount() {
	//
	// double balance = 10.0;
	//
	// dataAccessor.addAccount("My Account", balance);
	//
	// System.out.println("ACCOUNTS " + dataAccessor.getAccounts().size());
	//
	// for (Account a : dataAccessor.getAccounts()) {
	// System.out.println(a.getName() + " B: " + a.getBalance());
	// }
	//
	// // assertTrue(true);
	//
	// assertTrue(dataAccessor.getAccount(1).getBalance() == balance);
	// }

	public void testAddCategory() {
		dataAccessor.addCategory("1", null);
		dataAccessor.addCategory("2", null);

		List<Category> catList = dataAccessor.getCategories();

		if (catList.size() != 2) {
			fail("Could not add and get two first categories. Size:"
					+ catList.size());
		}
	}

	public void testAddSubCategories() {

		dataAccessor.addCategory("1", null);
		dataAccessor.addCategory("2", null);

		List<Category> catList = dataAccessor.getCategories();

		if (catList.size() != 2) {
			fail("Could not add and get two first categories. Size:"
					+ catList.size());
		}

		for (Category cat : catList) {
			dataAccessor.addCategory(cat.getName() + "-1", cat);
			dataAccessor.addCategory(cat.getName() + "-2", cat);
		}

		catList = dataAccessor.getCategories();

		for (Category cat : catList) {
			if (cat.getParent() != null) {
				dataAccessor.addCategory(cat.getName() + "-1", cat);
				dataAccessor.addCategory(cat.getName() + "-2", cat);
			}
		}

		catList = dataAccessor.getCategories();

		// Print out just for fun
		for (Category cat : catList) {
			String parent = "";
			if (cat.getParent() != null) {
				parent = cat.getParent().getName();
			}
			// System.out.println(cat.getName() + " Parent:" + parent);

		}

		if (catList.size() != (2 + 2 * 2 + 2 * 2 * 2)) {
			fail("Could not add or get subcategories. Size:" + catList.size());
		}
	}

	public void testTransactions() {
		Category incomeCategory = dataAccessor.getCategory(Constants.INCOME_ID);
		Category incomeCategoryChild = new Category("Lon", 5, incomeCategory);

		dataAccessor.addTransaction(100.0, new Date(01, 01, 2012),
				"transaction1", incomeCategoryChild);

		dataAccessor.addTransaction(50.0, new Date(02, 01, 2012),
				"transaction2", incomeCategoryChild);

		List<Transaction> list = dataAccessor.getTransactions(SortBy.DATE,
				SortByOrder.DESC, 0, 10);

		if (list.size() != 2) {
			fail("Size != 2 is " + list.size());
		}

		if (dataAccessor.getAccountBalance() != 150.0) {
			fail("Balance != 150 is " + dataAccessor.getAccountBalance());
		}

		dataAccessor.removeTransaction(list.get(0));

		list = dataAccessor.getTransactions(SortBy.DATE, SortByOrder.DESC, 0,
				10);

		if (list.size() != 1) {
			fail("Size != 1 is " + list.size());
		}
		if (dataAccessor.getAccountBalance() != 100.0) {
			fail("Balance != 100 is " + dataAccessor.getAccountBalance());
		}
	}

	public void testGetTransactionsByCategory() {
		Category incomeCategory = dataAccessor.getCategory(Constants.INCOME_ID);
		Category expensesCategory = dataAccessor
				.getCategory(Constants.EXPENSE_ID);
		Category incomeCategoryChild = new Category("Lon", 9, incomeCategory);
		Category expensesCategoryChild = new Category("Mat", 8,
				expensesCategory);
		dataAccessor.addCategory("Lon",
				dataAccessor.getCategory(Constants.INCOME_ID));

		dataAccessor.addTransaction(200.0, new Date(), "transaction1",
				incomeCategoryChild);

		if (dataAccessor.getAccountBalance() != 200.0) {
			fail("Balance != 200.0 is " + dataAccessor.getAccountBalance());
		}

		dataAccessor.addTransaction(125.0, new Date(), "transaction2",
				expensesCategoryChild);

		if (dataAccessor.getAccountBalance() != 75.0) {
			fail("Balance != 75.0 is " + dataAccessor.getAccountBalance());
		}

		dataAccessor.addTransaction(25.0, new Date(), "transaction3",
				expensesCategory);

		List<Transaction> list = dataAccessor.getTransactions(SortBy.DATE,
				SortByOrder.DESC, 0, 10);

		if (list.size() != 3) {
			fail("Size != 3 is " + list.size());
		}

		if (dataAccessor.getAccountBalance() != 50.0) {
			fail("Balance != 50.0 is " + dataAccessor.getAccountBalance());
		}
		if (list.get(2).getCategory() == null) {
			fail();
		}
		if (list.get(1).getCategory() == null) {
			fail();
		}
		if (list.get(0).getCategory() == null) {
			fail();
		}
		if (list.get(0).getName().equals("transaction1")) {
			dataAccessor.removeTransaction(list.get(0));
		}

		list = dataAccessor.getTransactions(SortBy.DATE, SortByOrder.DESC, 0,
				10);

		if (list.size() != 2) {
			fail("Size != 2 is " + list.size());
		}
		if (dataAccessor.getAccountBalance() != -150) {
			fail("Balance != -150 is " + dataAccessor.getAccountBalance());
		}

		dataAccessor.removeTransaction(list.get(1));

		List<Transaction> expenseList = dataAccessor.getTransactions(
				SortBy.DATE, SortByOrder.DESC, 0, 10,
				dataAccessor.getCategory(Constants.EXPENSE_ID));

		for (Transaction t : expenseList) {
			if (t.getCategory() == null) {
				fail(t.getName() + " doesn't have a category.");
			}
			if (t.getCategory().getParent() != null) {
				if (t.getCategory().getParent().getId() != Constants.EXPENSE_ID) {
					fail("The transaction: " + t.getName()
							+ " doesn't have the right category.");
				}
			}
			if (t.getCategory().getId() != Constants.EXPENSE_ID) {
				fail("The transaction: " + t.getName()
						+ " doesn't have the right category.");
			}

		}
		if (expenseList.size() != 1) {
			fail("The amount of expense transactions != 1 they are: "
					+ expenseList.size());
		}

	}

	public void testAddBudgetItems() {
		List<BudgetItem> list = dataAccessor.getBudgetItems();
		int listSizeBefore = list.size();

		dataAccessor.addCategory("food", null);

		dataAccessor.addBudgetItem(dataAccessor.getCategory(1), 2000.0);

		list = dataAccessor.getBudgetItems();

		// for (BudgetItem item : list) {
		// System.out.println("Category: " + item.getCategory().getName()
		// + " Value: " + item.getValue());
		// }

		boolean condition1 = (list.size() - listSizeBefore) == 1;
		boolean condition2 = list.get(list.size() - 1).getValue() == 2000.0;

		if (!(condition1)) {
			fail("List size isn't 1, it's " + (list.size() - listSizeBefore));
		}

		if (!(condition2))
			fail("Value isn't 2000.0 it's " + list.get(0).getValue());

	}

	public void testEditBudgetItem() {

		dataAccessor.addCategory("food", null);

		dataAccessor.addBudgetItem(dataAccessor.getCategory(1), 2000.0);

		List<BudgetItem> list = dataAccessor.getBudgetItems();

		// for (BudgetItem item : list) {
		// System.out.println("Category: " + item.getCategory().getName()
		// + " Value: " + item.getValue());
		// }

		boolean condition1 = list.size() == 1;
		boolean condition2 = list.get(0).getValue() == 2000.0;

		assertTrue(condition1 && condition2);
	}

	public void testRemoveBudgetItems() {

		dataAccessor.addCategory("food", null);

		dataAccessor.addBudgetItem(dataAccessor.getCategory(1), 2000.0);

		List<BudgetItem> list = dataAccessor.getBudgetItems();

		// for (BudgetItem item : list) {
		// System.out.println("Category: " + item.getCategory().getName()
		// + " Value: " + item.getValue());
		// }

		boolean condition1 = list.size() == 1;
		boolean condition2 = list.get(0).getValue() == 2000.0;

		assertTrue(condition1 && condition2);
	}

}
