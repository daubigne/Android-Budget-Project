package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;
import android.content.Context;

/**
 * version 1
 * 
 * @author FannyM A class which updates the model via the database.
 */

public class ManageCategoryController {

	private ManageCategoryModel model;
	private DataAccessor dataAccessor;

	public ManageCategoryController(Context context, ManageCategoryModel model) {
		this.model = model;
		this.dataAccessor = new DataAccessor(context);
		model.setCurrentParentCategory(dataAccessor.getCategory(2));
		model.setEditMode(false);
		model.setList(dataAccessor.getCategories(model
				.getCurrentParentCategory()));
	}

	public void setCurrentParentCategory(int id) {
		model.setCurrentParentCategory(dataAccessor.getCategory(id));
		model.setList(dataAccessor.getCategories(model
				.getCurrentParentCategory()));
	}

	public void addCategory(String name, Category parent) {
		dataAccessor.addCategory(name, parent);
		model.setList(dataAccessor.getCategories(model
				.getCurrentParentCategory()));
	}

	public void removeCategory(Category category) {
		dataAccessor.removeCategory(category);
		model.setList(dataAccessor.getCategories(model
				.getCurrentParentCategory()));
	}

	public void editCategory(Category category, String newName) {
		dataAccessor.editCategory(category, newName);
		model.setList(dataAccessor.getCategories(model
				.getCurrentParentCategory()));
	}

	public void setEditMode(boolean b) {
		model.setEditMode(b);
	}

}
