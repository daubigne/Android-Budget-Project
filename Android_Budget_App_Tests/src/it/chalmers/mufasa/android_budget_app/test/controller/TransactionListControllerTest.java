/**
 * 
 */
package it.chalmers.mufasa.android_budget_app.test.controller;

import java.util.Date;
import java.util.List;

import it.chalmers.mufasa.android_budget_app.controller.TransactionListController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.TransactionListModel;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * @author Slurpo
 * 
 */
public class TransactionListControllerTest extends AndroidTestCase {

	private int listSize = 0;

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testAddTransactions() {
		
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		Account account = Account.getInstance(context);
		TransactionListController controller = new TransactionListController(account);
		
		Category cat = new Category("CatFromTransactionListControllerTest", 1,
				null);

		controller.addTransaction(100.0, new Date(), "transaction1", cat);

		controller.addTransaction(100.0, new Date(), "transaction2", cat);

		List<Transaction> list = controller.getTransactions(100);
		

		if (list.size() != 2) {
			fail("Size != 2 is " + list.size());
		}
		listSize = list.size();
	}
	
	public void testRemoveTransactions() {
		
		
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		Account account = Account.getInstance(context);
		TransactionListController controller = new TransactionListController(account);


		Category cat = new Category("CatFromTransactionListControllerTest", 1,
				null);
		controller.addTransaction(75.0, new Date(2,1,2012), "transaction1", cat);

		controller.addTransaction(25.0, new Date(3,1,2012), "transaction2", cat);

		controller.addTransaction(100.0, new Date(4,1,2012), "transaction3", cat);

		controller.removeTransaction(controller.getTransactions(100).get(0));

		controller.removeTransaction(controller.getTransactions(100).get(0));


		List<Transaction> list = controller.getTransactions(100);
		System.out.println(listSize);
		
		if (list.size() != 3) {
			fail("Size != 3 is " + list.size());
		}
		listSize = list.size();
	}
}
