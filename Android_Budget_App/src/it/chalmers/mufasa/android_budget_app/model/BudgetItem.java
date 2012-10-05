package it.chalmers.mufasa.android_budget_app.model;

public class BudgetItem {

	private int id;
	private Category category;
	private Double value;
	
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
	
	@Override
	public String toString() {
		return "Category: "+this.category.getName()+" Value: "+this.value;
	}
	
}
