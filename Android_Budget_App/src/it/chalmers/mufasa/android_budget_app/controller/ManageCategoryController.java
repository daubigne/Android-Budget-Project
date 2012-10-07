package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;
import android.content.Context;

/**
 * version 1
 * 
 * @author FannyM A class which updates the model.
 */

public class ManageCategoryController {

	private ManageCategoryModel model;

	public ManageCategoryController(ManageCategoryModel model) {
		this.model = model;
	}

	public void setCurrentParentCategory(int id) {
		model.setCurrentParentCategory(id);
	}

	public void addCategory(String name, Category parent) {
		model.addCategory(name, parent);
	}

	public void removeCategory(Category category) {
		model.removeCategory(category);
	}

	public void editCategory(Category category, String newName) {
		model.editCategory(category, newName);
	}

	public void setEditMode(boolean b) {
		model.setEditMode(b);
	}

}
