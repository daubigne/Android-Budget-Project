package it.chalmers.mufasa.android_budget_app.test.model;

import java.util.Date;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import junit.framework.TestCase;

public class AccountTest extends AndroidTestCase {

	Account account1;
	Category category1;

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
		if(account1.getBalance() != 8.0){
			fail("Balance isn't 8.0 it's " + account1.getBalance());
		}

	}

	// public void testName() {
	// account1.setName("account1");
	// if (!(account1.getName().equals("account1"))) {
	// fail("Name is not equal");
	// }
	// }

}
