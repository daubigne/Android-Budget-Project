package it.chalmers.mufasa.android_budget_app.model;

public class BudgetItem {

	Category category;
	Double value;
	
	public BudgetItem(Category category, Double value) {
		this.category = category;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public Double getValue() {
		return this.value;
	}
	
}
