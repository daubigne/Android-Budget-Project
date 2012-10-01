package it.chalmers.mufasa.android_budget_app.controller;

import android.content.Context;
import android.view.View;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;

public class ManageCategoryController {
	
	private ManageCategoryModel model;
	private DataAccessor dataAccessor;
	
	public ManageCategoryController(Context context, ManageCategoryModel model) {
		this.model = model;
		this.dataAccessor = new DataAccessor(context);
	}
	
	
	public void addCategory(String name, Category parent) {
    	dataAccessor.addCategory(name, parent);
    }
	
	public void removeCategory(Category category) {
    	dataAccessor.removeCategory(category);
    }
	
	public void editCategory(Category category, String newName) {
    	dataAccessor.editCategory(category, newName);
    }
	
	
}
