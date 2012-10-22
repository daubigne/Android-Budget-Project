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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	Date date1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Calendar cal = new GregorianCalendar();
		cal.set(2012, 0, 0);
		date1 = cal.getTime();
		category1 = new Category("category1", 1, null);
		transaction1 = new Transaction(1, 20.0, date1, "transaction1",
				category1);
	}

	/**
	 * Test the getAmount method.
	 */
	public void testAmount() {
		if (transaction1.getAmount() != 20.0) {
			fail("Amount is not not '20.0', it's " + transaction1.getAmount());
		}
	}

	/**
	 * Tests the getId method.
	 */
	public void testId() {
		if (transaction1.getId() != 1) {
			fail("ID is not '1', it's " + transaction1.getId());
		}
	}

	/**
	 * Tests the getName method.
	 */
	public void testName() {
		if (!(transaction1.getName().equals("transaction1"))) {
			fail("Name is not 'transaction1, it's " + transaction1.getName());
		}
	}

	/**
	 * Test the getDate method.
	 */
	public void testDate() {
		if(!(transaction1.getDate().equals(date1))){
			fail("Date not equal");
		}
	}
	
	/**
	 * Tests the getCategory method.
	 */
	public void testCategory() {
		if (!(transaction1.getCategory().equals(category1))) {
			fail("Category not equal");
		}
	}
	
	/**
	 * Tests the equals method.
	 */
	public void testEquals() {
		Transaction transaction2 = new Transaction(1, 20.0, date1, "transaction1",
				category1);
		if(!transaction1.equals(transaction2)){
			fail("Transaction is not equals");
		}
	}
}
