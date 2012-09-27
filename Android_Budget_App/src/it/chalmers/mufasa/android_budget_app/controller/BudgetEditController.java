package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.Category;
import android.content.Context;

public class BudgetEditController {
	
	DataAccessor dataAccessor;
	BudgetEditModel model;
	
	public BudgetEditController(Context context, BudgetEditModel model) {
		this.dataAccessor = new DataAccessor(context);
		this.model = model;
		
		initModel();
	}
	
	private void initModel() {
		model.setBudgetItems(dataAccessor.getBudgetItems());
	}
	
	public void addBudgetItem(Category category, Double value) {
		dataAccessor.addBudgetItem(category, value);
		model.setBudgetItems(dataAccessor.getBudgetItems());
	}

}
