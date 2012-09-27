package it.chalmers.mufasa.android_budget_app.model;

import java.util.List;

public class BudgetEditModel {

	List<BudgetItem> budgetItems;
	
	public BudgetEditModel() {
	}
	
	public List<BudgetItem> getBudgetItems() {
		return this.budgetItems;
	}
	
	public void setBudgetItems(List<BudgetItem> budgetItems) {
		this.budgetItems = budgetItems;
	}

}
