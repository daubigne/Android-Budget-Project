package it.chalmers.mufasa.android_budget_app.test.controller;

import it.chalmers.mufasa.android_budget_app.controller.HomeScreenController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.HomeScreenModel;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.util.Date;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class HomeScreenControllerTest extends AndroidTestCase {
	Account account;
	HomeScreenController controller;
	
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test");
		controller = new HomeScreenController(new HomeScreenModel(context));
		account = Account.getInstance(context);

	}
	public void testCalculatePercentage() {
		account.addBudgetItem(account.getCategory(Constants.EXPENSE_ID), 10.0);
		account.addTransaction(9.0, new Date(),"test", account.getCategory(Constants.EXPENSE_ID));
		controller.calculatePercentage();
		if(controller.getModel().getPercentage() != 90.0){
			fail("Calculate percantage doesn't work");
		}
	}

}
