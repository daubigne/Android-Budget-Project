package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.util.List;

/**
 * Controller for BudgetEditFragment. Calls appropriate methods in BudgetEditModel.
 * 
 * @author simphax <sim.nilsson@gmail.com>
 *
 */

public class BudgetEditController {
	
	BudgetEditModel model;
	
	public BudgetEditController(BudgetEditModel model) {
		this.model = model;
	}
	
	public void newBudgetItem() {
		model.newBudgetItem();
	}
	
	public void removeBudgetItem(BudgetItem bi) {
		model.removeBudgetItem(bi);
	}
	
	public void saveAllBudgetItems(List<BudgetItem> list) {
		model.replaceBudgetItems(list);
	}
	
	public void setEditMode(boolean set) {
		model.setEditMode(set);
	}

	public void switchToIncome() {
		model.setCurrentMainCategory(1);
	}
	
	public void switchToExpenses() {
		model.setCurrentMainCategory(2);
	}
}
