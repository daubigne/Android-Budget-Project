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

import it.chalmers.mufasa.android_budget_app.model.Category;
import android.test.AndroidTestCase;

/**
 * @author marcusisaksson
 * 
 */
public class CategoryTest extends AndroidTestCase {

	private Category category1;
	private Category category2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

		category2 = new Category("category2", 2, null);

		category1 = new Category("category1", 1, category2);
	}

	public void testName() {
		if (!(category1.getName().equals("category1"))) {
			fail("Name is not equal");
		}
	}

	public void testId() {
		if (category1.getId() != 1) {
			fail("ID is not equal");
		}
	}

	public void testParent() {
		if (!(category1.getParent().equals(category2))) {
			fail("Parent is not equal");
		}
	}

}
