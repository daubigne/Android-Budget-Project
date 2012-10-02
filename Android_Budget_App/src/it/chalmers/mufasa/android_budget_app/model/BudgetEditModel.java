package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class BudgetEditModel {

	List<BudgetItem> budgetItems;
	
	PropertyChangeSupport pcs;
	
	Category currentMainCategory;
	
	public BudgetEditModel() {
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public List<BudgetItem> getBudgetItems() {
		return this.budgetItems;
	}
	
	public void setCurrentMainCategory(Category cat) {
		this.currentMainCategory = cat;
	}
	
	public Category getCurrentMainCategory() {
		return this.currentMainCategory;
	}
	
	public void setBudgetItems(List<BudgetItem> budgetItems) {
		this.budgetItems = budgetItems;
		pcs.firePropertyChange("BudgetItemList", null, null); 
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
