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
package it.chalmers.mufasa.android_budget_app.test.model.database;

import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.AccountDay;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class DatabaseTest extends AndroidTestCase {

	private DataAccessor dataAccessor;
	private RenamingDelegatingContext context;

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

	public void testCategories() {
		List<Category> catList = dataAccessor.getCategories();
		int catListSizeStart = catList.size();
		dataAccessor.addCategory("1", dataAccessor.getCategory(Constants.EXPENSE_ID));
		dataAccessor.addCategory("2", dataAccessor.getCategory(Constants.INCOME_ID));
		
		catList = dataAccessor.getCategories();
		int catListSize = catList.size() - catListSizeStart;

		if (catListSize != 2) {
			fail("Could not add and get two first categories. Size:"
					+ catListSize);
		}
		
		Category cat1 = catList.get(catList.size() - 2);
		Category cat2 = catList.get(catList.size() - 1);
		
		if(!cat1.getName().equals("1")){
			fail("Wrong category name, is: " + cat1.getName());
		}
		if(!cat2.getName().equals("2")){
			fail("Wrong category name, is: " + cat2.getName());
		}
		if(cat1.getParent().getId() != Constants.EXPENSE_ID){
			fail("Wrong parent, is: " + cat1.getParent().getId());
		}
		if(cat2.getParent().getId() != 1){
			fail("Wrong parent, is: " + cat2.getParent().getId());
		}
	}

	public void testTransactions() {
		Category incomeCategory = dataAccessor.getCategory(Constants.INCOME_ID);
		Category incomeCategoryChild = dataAccessor.addCategory("incomeCategoryChild", incomeCategory);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = new GregorianCalendar();
		cal.set(2012, 0, 0);
		Date date1 = cal.getTime();
		cal.set(2012, 1, 0);
		Date date2 = cal.getTime();
		
		double value1 = 100.0;
		double value2 = 50.0;
		
		dataAccessor.addTransaction(value1, date1,
				"transaction1", incomeCategoryChild);

		dataAccessor.addTransaction(value2, date2,
				"transaction2", incomeCategoryChild);

		List<Transaction> list = dataAccessor.getTransactions(SortBy.DATE,
				SortByOrder.DESC, 0, 10);

		if (list.size() != 2) {
			fail("Size != 2 is " + list.size());
		}
		
		if (!list.get(0).getName().equals("transaction2")) {
			fail("Name != transaction2 is "+list.get(0).getName());
		}
		
		if (list.get(0).getAmount() != value2) {
			fail("Value != "+value2+" is "+list.get(0).getAmount());
		}
		
		if (!list.get(0).getCategory().equals(incomeCategoryChild)) {
			fail("Category != "+list.get(0).getCategory().toString()+" is "+list.get(0).getCategory().toString());
		}
		
		if (!dateFormat.format(list.get(0).getDate()).equals(dateFormat.format(date2))) {
			fail("Date != "+dateFormat.format(date2) +" is "+dateFormat.format(list.get(0).getDate()));
		}

		if (dataAccessor.getAccountBalance() != 150.0) {
			fail("Account balance != 150 is " + dataAccessor.getAccountBalance());
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


		
		Category incomeCategoryChild = dataAccessor.addCategory("Lon", incomeCategory);
		Category expensesCategoryChild = dataAccessor.addCategory("Mat", expensesCategory);
		

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
				
		dataAccessor.removeTransaction(list.get(0));
		

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
			else if (t.getCategory().getId() != Constants.EXPENSE_ID) {
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

		boolean condition1 = (list.size() - listSizeBefore) == 1;
		boolean condition2 = list.get(list.size() - 1).getValue() == 2000.0;

		if (!(condition1)) {
			fail("List size isn't 1, it's " + (list.size() - listSizeBefore));
		}

		if (!(condition2)){
			fail("Value isn't 2000.0 it's " + list.get(0).getValue());
		}

	}

	public void testEditBudgetItem() {
		List<BudgetItem> listStart = dataAccessor.getBudgetItems();
		dataAccessor.addCategory("food", null);

		dataAccessor.addBudgetItem(dataAccessor.getCategory(1), 2000.0);

		List<BudgetItem> list = dataAccessor.getBudgetItems();

		if(list.size() - listStart.size() != 1){
			fail();
		}
		if(list.get(list.size() - 1).getValue() != 2000.0){
			fail();
		}
		
		dataAccessor.removeBudgetItem(list.get(list.size() - 1));
		
		list = dataAccessor.getBudgetItems();
		
		if(list.size() - listStart.size() != 0){
			fail();
		}
	}
	
	public void testGetBudgetItemsSum() {
		double startExpenseSum = dataAccessor.getBudgetItemsSum(dataAccessor.getCategory(Constants.EXPENSE_ID));
		double startIncomeSum = dataAccessor.getBudgetItemsSum(dataAccessor.getCategory(Constants.INCOME_ID));

		
		dataAccessor.addBudgetItem(dataAccessor.getCategory(Constants.EXPENSE_ID), 2000.0);
		dataAccessor.addBudgetItem(dataAccessor.getCategory(Constants.INCOME_ID), 1000.0);
		
		double expenseSum = dataAccessor.getBudgetItemsSum(dataAccessor.getCategory(Constants.EXPENSE_ID));
		double incomeSum = dataAccessor.getBudgetItemsSum(dataAccessor.getCategory(Constants.INCOME_ID));

		
		if(expenseSum - startExpenseSum != 2000.0){
			fail(Double.toString(expenseSum - startExpenseSum));
		}
		
		if(incomeSum - startIncomeSum != 1000.0){
			fail(Double.toString(incomeSum - startIncomeSum));
		}
	}
	
	public void testGetTransactionsByDates() {
		for(int i=0; i<20; i++) {
			dataAccessor.addTransaction(200.0*i, (new GregorianCalendar(2012,9,12,15,00,00)).getTime(), "Mat"+i, dataAccessor.getCategory(2));
		}
		
	}
	public void testGetAccountBalanceForEachDay() {
		
		dataAccessor.addTransaction(2000.0, (new GregorianCalendar(2012,8,0,15,00,00)).getTime(), "Income 1", dataAccessor.getCategory(1));
		dataAccessor.addTransaction(1000.0, (new GregorianCalendar(2012,8,19,15,00,00)).getTime(), "Income 2", dataAccessor.getCategory(1));
		
		dataAccessor.addTransaction(200.0, (new GregorianCalendar(2012,8,5,15,00,00)).getTime(), "Expense 1", dataAccessor.getCategory(2));
		dataAccessor.addTransaction(200.0, (new GregorianCalendar(2012,8,25,15,00,00)).getTime(), "Expense 2", dataAccessor.getCategory(2));
		dataAccessor.addTransaction(200.0, (new GregorianCalendar(2012,8,25,15,00,00)).getTime(), "Expense 3", dataAccessor.getCategory(2));
		
//		for(int i=0; i<20; i++) {
//			dataAccessor.addTransaction(200.0*i, (new GregorianCalendar(2012,9,i,15,00,00)).getTime(), "Expense "+i, dataAccessor.getCategory(2));
//		}
//		for(int i=0; i<5; i++) {
//			dataAccessor.addTransaction(1000.0*i, (new GregorianCalendar(2012,9,i,15,00,00)).getTime(), "Income "+i, dataAccessor.getCategory(1));
//		}
		
		for(AccountDay accountDay : dataAccessor.getAccountBalanceForEachDay((new GregorianCalendar(2012,8,1)).getTime())) {
			//System.out.println(accountDay.toString());
		}
	}
}
