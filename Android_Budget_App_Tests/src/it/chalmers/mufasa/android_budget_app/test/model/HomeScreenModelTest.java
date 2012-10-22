package it.chalmers.mufasa.android_budget_app.test.model;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.HomeScreenModel;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class HomeScreenModelTest extends AndroidTestCase {
	Account account;
	HomeScreenModel model;
	
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test");
		model = new HomeScreenModel(context);
		account = Account.getInstance(context);

	}

	public void testGetBalance() {
		account.setBalance(10.0);
		if(model.getBalance() != 10.0){
			fail("Model doesn't return the correct balance");
		}
		
	}

	public void testGetPercentage() {
		if(model.getPercentage() != 0.0){
			fail("Failed to recieve the percentage");
		}
		
	}

	public void testCalculatePercentage() {
		this.testGetPercentage();
		Calendar c = new GregorianCalendar();
		Date to = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, 0);
		Date from = c.getTime();
		c.add(Calendar.MONTH, Calendar.MONTH - 1);
		Date transDate = c.getTime();
		
		account.addTransaction(100.0,transDate, "test1", account.getCategory(Constants.EXPENSE_ID));
		account.addTransaction(90.0, from, "test2",account.getCategory(Constants.EXPENSE_ID));
		//minor test: check that one gets the correct sum of transactions for the current month:
		if(account.getTransactionsSum(from, to, Constants.EXPENSE_ID) != 90.0){
			fail("getTransactionsSum doesn't return the correct sum");
		}
		//clears the default budget.
		account.removeBudget();
		//adds a new budget item which is easy to manually calculate percentage on.
		account.addBudgetItem(account.getCategory(Constants.EXPENSE_ID), 100.0);
		//minor test: check that one gets the correct sum of budget:
		if(account.getBudgetItemsSum(Constants.EXPENSE_ID) != 100.0){
			fail("getBudgetItemsSum doesn't return the correct sum");
		}
		//after the calculatePercantage() call the percentage should be 90.0 (90%)
		model.calculatePercentage();
		if(model.getPercentage() != 90.0){
			fail("The calculation for percentage is faulty");
		}
		
	}

}
