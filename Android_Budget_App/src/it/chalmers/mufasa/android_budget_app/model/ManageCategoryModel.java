package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * version 1
 * 
 * @author FannyM A class which contains the current state of the
 *         ManageCategoryActivity.
 */

public class ManageCategoryModel {

	private PropertyChangeSupport pcs;
	private List<Category> categoryList;
	private Category currentParentCategory;
	private boolean editMode;

	public ManageCategoryModel() {
		pcs = new PropertyChangeSupport(this);
	}

	public void setList(List<Category> categoryList) {
		this.categoryList = categoryList;
		pcs.firePropertyChange("categoryList", null, null);
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public Category getCurrentParentCategory() {
		return currentParentCategory;
	}

	public void setCurrentParentCategory(Category category) {
		currentParentCategory = category;
		pcs.firePropertyChange("categoryList", null, null);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public boolean isEditMode() {
		return editMode;

	}

	public void setEditMode(boolean b) {
		editMode = b;
		pcs.firePropertyChange("EditSaveMode", null, null);
	}
}
