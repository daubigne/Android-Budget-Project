package it.chalmers.mufasa.android_budget_app.model;

public class BudgetItem {

	int id;
	Category category;
	Double value;
	
	public BudgetItem(int id, Category category, Double value) {
		this.id = id;
		this.category = category;
		this.value = value;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public Double getValue() {
		return this.value;
	}
	
	public int getId() {
		return this.id;
	}
	
}
