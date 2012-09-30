/**
 * 
 */
package it.chalmers.mufasa.android_budget_app.test.model;

import it.chalmers.mufasa.android_budget_app.model.Category;
import android.test.AndroidTestCase;
import junit.framework.TestCase;

/**
 * @author marcusisaksson
 * 
 */
public class CategoryTest extends AndroidTestCase {

	Category category1;
	Category category2;

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
