package it.chalmers.mufasa.android_budget_app.test.model;

import it.chalmers.mufasa.android_budget_app.model.Account;
import android.test.AndroidTestCase;
import junit.framework.TestCase;

public class AccountTest extends AndroidTestCase {

	Account account1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		account1 = new Account(1, "account1", 25.0);

	}

	public void testBalance() {
		if (account1.getBalance() != 25.0) {
			fail("Balance is not equal");
		}
	}

	public void testId() {
		if (account1.getId() != 1) {
			fail("ID is not equal");
		}
	}

	public void testName() {
		if (!(account1.getName().equals("account1"))) {
			fail("Name is not equal");
		}
	}

}
