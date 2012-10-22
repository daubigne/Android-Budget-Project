 /*
  * Copyright © 2012 Mufasa developer unit
  *
  * This file is part of Mufasa Budget.
  *
  *	Mufasa Budget is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Mufasa Budget is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with Mufasa Budget.  If not, see <http://www.gnu.org/licenses/>.
  */
package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import android.content.Context;

/**
 * version 1
 * 
 * @author FannyM A class which contains the current state of the
 *         ManageCategoryActivity.
 */

public class ManageCategoryModel {

	private Account account;
	private PropertyChangeSupport pcs;
	private Category currentParentCategory;
	private boolean editMode = false;

	public ManageCategoryModel(Context context) {
		pcs = new PropertyChangeSupport(this);
		this.account = Account.getInstance(context);
		this.currentParentCategory=account.getCategory(2);
	}

	/**
	 * a method that returns a list of categories based on the parent category
	 * (income or expense)
	 * 
	 * @return
	 */
	public List<Category> getCategoryList() {
		return account.getCategories(currentParentCategory);
	}

	/**
	 * a method to add a category in the account
	 * 
	 * @param name
	 * @param parent
	 */
	public void addCategory(String name, Category parent) {
		account.addCategory(name, parent);
		pcs.firePropertyChange("added_category", null, null);
	}

	/**
	 * a method to remove a category in the account
	 * 
	 * @param category
	 */
	public void removeCategory(Category category) {
		account.removeCategory(category);
		pcs.firePropertyChange("removed_category", null, null);
	}

	/**
	 * a method to edit a category in the account
	 * 
	 * @param category
	 * @param newName
	 */
	public void editCategory(Category category, String newName) {
		account.editCategory(category, newName);
		pcs.firePropertyChange("edited_category", null, null);
	}

	/**
	 * Returns the current parent category.
	 */
	public Category getCurrentParentCategory() {
		return currentParentCategory;
	}

	/**
	 * Sets the current parent category.
	 * @param id - the ID of the parent category.
	 */
	public void setCurrentParentCategory(int id) {
		this.setCurrentParentCategory(account.getCategory(id));
	}

	/**
	 * Sets the current parent category.
	 * @param category - the parent category.
	 */
	public void setCurrentParentCategory(Category category) {
		currentParentCategory = category;
		pcs.firePropertyChange("changed_parent_category", null, null);
	}

	/**
	 * Adds a property change listener to this.
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Removes a property change listener to this.
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Returns true if in edit mode and false if not.
	 * @return
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * A method to change the edit mode of the model
	 * 
	 * @return
	 */
	public void setEditMode(boolean set) {
		if (this.editMode != set) {
			this.editMode = set;
			pcs.firePropertyChange("changed_editmode", null, null);
		}
	}
}
