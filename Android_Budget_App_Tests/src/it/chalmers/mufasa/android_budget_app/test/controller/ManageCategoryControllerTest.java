package it.chalmers.mufasa.android_budget_app.test.controller;

import it.chalmers.mufasa.android_budget_app.controller.ManageCategoryController;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;

import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * Runs tests for TransactionController
 * 
 * @author FannyM
 * 
 */
public class ManageCategoryControllerTest extends AndroidTestCase {

	private Context context;
	private ManageCategoryController controller;
	private ManageCategoryModel model;

	protected void setUp() throws Exception {
		super.setUp();
		context = new RenamingDelegatingContext(getContext(), "_test"); // This
																		// will
																		// start
																		// with
																		// fresh
																		// userdata
		model = new ManageCategoryModel(context);
		controller = new ManageCategoryController(model);

	}

	/**
	 * a method to test the addCategory, editCategory and removeCategory methods
	 * in the controller
	 */
	public void testAddRemoveCategory() {
		Category parent = model.getCurrentParentCategory();
		List<Category> categoryList1 = model.getCategoryList();
		if (categoryList1.size() < 1) {
			fail("no categoryList");
		}

		Category category = null;
		int categoryId;
		String name1 = "herpaderp";
		String name2 = "derpaherp";

		if (parent == null) {
			fail("no parent category");
		} else {

			controller.addCategory(name1, parent);
			if (!(model.getCategoryList().size() > categoryList1.size())) {

				fail("category was not added to the list: "
						+ model.getCategoryList().size() + ">"
						+ categoryList1.size());
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
			categoryId = category.getId();
			controller.editCategory(category, name2);
			for (Category cat : model.getCategoryList()) {
				if (cat.getId() == categoryId) {
					category = cat;
				}
			}
			if (!(category.getName().equals(name2))) {
				fail("category was not properly renamed: " + category.getName()
						+ " " + name2);
			}
		}

		if (category == null) {
			fail("could not find category to remove");
		} else {
			controller.removeCategory(category);

			if (!(categoryList2.size() > model.getCategoryList().size())) {
				fail("category was not removed from the list");
			}
		}
	}

	/**
	 * Tests the edit mode functions.
	 */
	public void testEditMode() {
		if (model.isEditMode()) {
			fail("Controller is in edit mode when created");
		}
		controller.setEditMode(true);

		if (!model.isEditMode()) {
			fail("Controller is not in edit mode after setting edit mode to true.");
		}
	}

	/**
	 * Tests the SetParetCategory function.
	 */
	public void testSetParentCategory() {
		if (model.getCurrentParentCategory().getId() == 1) {
			controller.setCurrentParentCategory(2);
			if (!(model.getCurrentParentCategory().getId() == 2)) {
				fail("Parent category was not changed");
			}
		} else if (model.getCurrentParentCategory().getId() == 2) {
			controller.setCurrentParentCategory(1);
			if (!(model.getCurrentParentCategory().getId() == 1)) {
				fail("Parent category was not changed");
			}
		}
	}
}