/**
 * 
 */
package it.chalmers.mufasa.android_budget_app.test.model;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.util.Date;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

/**
 * @author marcusisaksson
 * 
 */
public class TransactionTest extends AndroidTestCase {

	Category category1;
	Account account1;
	Transaction transaction1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		category1 = new Category("category1", 1, null);
		account1 = new Account(1, "acccount1", 100.0);
		transaction1 = new Transaction(1, 20.0, new Date(), "transaction1",
				category1, account1);
	}

	public void testAmount() {
		if (transaction1.getAmount() != 20.0) {
			fail("Amount is not equal");
		}
	}

	public void testId() {
		if (transaction1.getId() != 1) {
			fail("ID is not equal");
		}
	}

	public void testName() {
		if (!(transaction1.getName().equals("transaction1"))) {
			fail("Name is not equal");
		}
	}

	// TODO test date
	
	public void testCategory() {
		if (!(transaction1.getCategory().equals(category1))) {
			fail("Category not equal");
		}
	}

	public void testAccount() {
		if (!(transaction1.getAccount().equals(account1))) {
			fail("Account not equal");
		}
	}
}
