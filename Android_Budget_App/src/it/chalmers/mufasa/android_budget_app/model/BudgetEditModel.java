package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class BudgetEditModel {

	Account account;
	List<BudgetItem> budgetItems;
	
	PropertyChangeSupport pcs;
	
	Category currentMainCategory;
	
	boolean editMode;
	
	public BudgetEditModel() {
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public List<BudgetItem> getBudgetItems() {
		return this.budgetItems;
	}
	
	public void setCurrentMainCategory(Category cat) {
		this.currentMainCategory = cat;
		pcs.firePropertyChange("updated_current_category", null, null); 
	}
	
	public Category getCurrentMainCategory() {
		return this.currentMainCategory;
	}
	
	public void setBudgetItems(List<BudgetItem> budgetItems) {
		this.budgetItems = budgetItems;
		pcs.firePropertyChange("updated_budgetitem_list", null, null); 
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	
	public void setEditMode(boolean set) {
		this.editMode = set;
		pcs.firePropertyChange("editmode", null, null); 
	}
	
	public boolean isEditMode() {
		return this.editMode;
	}
	
}
