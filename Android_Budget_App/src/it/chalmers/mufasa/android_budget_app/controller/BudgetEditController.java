package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.util.List;

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
		model.setCurrentMainCategory(dataAccessor.getCategory(1));
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
	
	public void saveAllBudgetItems(List<BudgetItem> list) {
		
		for(BudgetItem item : dataAccessor.getBudgetItems(model.getCurrentMainCategory())) {
			dataAccessor.removeBudgetItem(item);
		}
		
		for(BudgetItem item : list) {
			dataAccessor.addBudgetItem(item.getCategory(), item.getValue());
		}
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
	
	public void addBudgetItem(Category category, Double value) {
		dataAccessor.addBudgetItem(category, value);
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
	
	public void editBudgetItem(BudgetItem item, Double value) {
		dataAccessor.editBudgetItem(item, value);
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
	
	public void removeBudgetItem(BudgetItem item) {
		dataAccessor.removeBudgetItem(item);
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
	
	public void setEditMode(boolean set) {
		model.setEditMode(set);
	}

	public void switchToIncome() {
		model.setCurrentMainCategory(dataAccessor.getCategory(1));
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
	
	public void switchToExpenses() {
		model.setCurrentMainCategory(dataAccessor.getCategory(2));
		model.setBudgetItems(dataAccessor.getBudgetItems(model.getCurrentMainCategory()));
	}
}
