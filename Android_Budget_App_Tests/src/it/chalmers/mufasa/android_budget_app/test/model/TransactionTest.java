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
package it.chalmers.mufasa.android_budget_app.test.model;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.util.Date;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
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
		transaction1 = new Transaction(1, 20.0, new Date(), "transaction1",
				category1);
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
}
