package it.chalmers.mufasa.android_budget_app.test.model;

import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;

public class ManageCategoryTest extends AndroidTestCase {

	private ManageCategoryModel model;

	protected void setUp() throws Exception {
		super.setUp();

		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		model = new ManageCategoryModel(context);
	}

	/**
	 * a method to test the addCategory, editCategory and removeCategory methods in the model
	 */
	public void testAddRemoveCategory() {
		Category parent = model.getCurrentParentCategory();
		List<Category> categoryList1 = model.getCategoryList();
		if (categoryList1.size()<1) {
			fail("no categoryList");
		}
		
		Category category = null;
		int categoryId;
		String name1 = "herpaderp";
		String name2 = "derpaherp";
		
		if (parent == null) {
			fail("no parent category");
		} else {
			
			model.addCategory(name1, parent);
			if (!(model.getCategoryList().size() > categoryList1.size())) {
				
				fail("category was not added to the list: "+ model.getCategoryList().size() +">"+ categoryList1.size());
			}
		}

		List<Category> categoryList2 = model.getCategoryList();

		for (Category cat : categoryList2) {
			if (cat.getName().equals(name1)) {
				category = cat;
			}
		}
		
		if (category == null) {
			fail("could not find category to rename");
		} else {
			categoryId=category.getId();
			model.editCategory(category, name2);
			for (Category cat : model.getCategoryList()) {
				if (cat.getId()==categoryId) {
					category = cat;
				}
			}
			if (!(category.getName().equals(name2))) {
				fail("category was not properly renamed: "+category.getName()+ " " +name2);
			}
		}

		if (category == null) {
			fail("could not find category to remove");
		} else {
			model.removeCategory(category);

			if (!(categoryList2.size() > model.getCategoryList().size())) {
				fail("category was not removed from the list");
			}
		}
	}
}
