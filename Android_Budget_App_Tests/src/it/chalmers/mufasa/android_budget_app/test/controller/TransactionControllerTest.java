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
package it.chalmers.mufasa.android_budget_app.test.controller;

import java.util.Date;
import java.util.List;

import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * Runs tests for TransactionController
 * @author Slurpo
 * 
 */
public class TransactionControllerTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Tests the addTransaction and the getTransactions methods.
	 */
	public void testAddTransactions() {
		
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		Account account = Account.getInstance(context);
		TransactionController controller = new TransactionController(account);
		
		Category cat = new Category("CatFromTransactionListControllerTest", 1,
				null);

		controller.addTransaction(100.0, new Date(), "transaction1", cat);

		controller.addTransaction(100.0, new Date(), "transaction2", cat);

		List<Transaction> list = controller.getTransactions(100);
		

		if (list.size() != 2) {
			fail("Size != 2 is " + list.size());
		}
	}
	
	/**
	 * Tests the removeTransactions method and getTransactions method.
	 */
	public void testRemoveTransactions() {
		
		
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		Account account = Account.getInstance(context);
		TransactionController controller = new TransactionController(account);


		Category cat = new Category("CatFromTransactionListControllerTest", 1,
				null);
		controller.addTransaction(75.0, new Date(2,1,2012), "transaction1", cat);

		controller.addTransaction(25.0, new Date(3,1,2012), "transaction2", cat);

		controller.addTransaction(100.0, new Date(4,1,2012), "transaction3", cat);

		controller.removeTransaction(controller.getTransactions(100).get(0));

		controller.removeTransaction(controller.getTransactions(100).get(0));


		List<Transaction> list = controller.getTransactions(100);
		
		if (list.size() != 3) {
			fail("Size != 3 is " + list.size());
		}
	}
	
	/**
	 * Tests the edit mode functions.
	 */
	public void testEditMode(){
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		Account account = Account.getInstance(context);
		TransactionController controller = new TransactionController(account);
		
		//Should not be in edit mode when instantiated.
		if(controller.isEditMode()){
			fail("Controller is in edit mode when created");
		}
		
		controller.setEditMode(true);
		
		if(!controller.isEditMode()){
			fail("Controller is not in edit mode after setting edit mode to true.");
		}
	}
	
	/**
	 * Tests the current main category methods.
	 */
	public void testCurrentMainCategory(){
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		Account account = Account.getInstance(context);
		TransactionController controller = new TransactionController(account);
		
		if(controller.getCurrentMainCategory().getId() != Constants.INCOME_ID){
			fail("Current main category is not 'Income' when controller is created.");
		}
		
		controller.switchToExpenses();
		
		if(controller.getCurrentMainCategory().getId() != Constants.EXPENSE_ID){
			fail("Current main category is not 'expenses'.");
		}
		
		controller.switchToIncome();
		
		if(controller.getCurrentMainCategory().getId() != Constants.INCOME_ID){
			fail("Current main category is not 'Income'");
		}
	}
}
