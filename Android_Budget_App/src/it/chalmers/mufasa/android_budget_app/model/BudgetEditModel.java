package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class BudgetEditModel {

	Account account;
	List<BudgetItem> budgetItems;
	
	PropertyChangeSupport pcs;
	
	public BudgetEditModel() {
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public List<BudgetItem> getBudgetItems() {
		return this.budgetItems;
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
