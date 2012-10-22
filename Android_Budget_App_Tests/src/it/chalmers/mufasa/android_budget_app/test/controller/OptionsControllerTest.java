package it.chalmers.mufasa.android_budget_app.test.controller;

import it.chalmers.mufasa.android_budget_app.controller.OptionsController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.util.Date;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class OptionsControllerTest extends AndroidTestCase {
	Account account;
	OptionsController controller;

	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test");
		controller = new OptionsController(context);
		account = Account.getInstance(context);

	}

	public void testClearTransactions() {
		account.addTransaction(10.0, new Date(), "test", new Category("test",
				0, account.getCategory(Constants.EXPENSE_ID)));
		controller.clearTransactions();
		if (account.getTransactions(2,
				account.getCategory(Constants.EXPENSE_ID)).size() != 0) {
			fail("Did not clear transactions");
		}

	}

	public void testClearBudget() {
		account.addBudgetItem(account.getCategory(Constants.EXPENSE_ID), 300.0);
		controller.clearBudget();
		if(account.getBudgetItems().size() != 0){
			fail("Did not clear Budget");
		}
	}

	public void testAddBalance() {
		controller.addBalance(12.0);
		if(account.getBalance() != 12.0){
			fail("Didn't add balance");
		}
	}
	

}
