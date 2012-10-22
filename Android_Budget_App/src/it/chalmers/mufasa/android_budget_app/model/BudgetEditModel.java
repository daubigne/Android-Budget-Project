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
 * Model to represent the state of BudgetEditFragment. Calls appropriate methods
 * in Account to store BudgetItems in database.
 * 
 * @author simphax <sim.nilsson@gmail.com>
 * 
 */
public class BudgetEditModel {

	private Account account;
	private PropertyChangeSupport pcs;
	private Category currentMainCategory;
	private boolean editMode = false;

	public BudgetEditModel(Context context) {
		this.pcs = new PropertyChangeSupport(this);
		this.account = Account.getInstance(context);
		this.currentMainCategory = account.getCategory(1);

	}

	public List<BudgetItem> getBudgetItems() {
		System.out.println("Getting budget items with category"
				+ this.getCurrentMainCategory().getName());
		return account.getBudgetItems(this.getCurrentMainCategory());
	}

	public void setCurrentMainCategory(int i) { // TODO get category from
												// choosecategoryactivity
		this.currentMainCategory = account.getCategory(i);
		pcs.firePropertyChange("updated_current_category", null, null);
	}

	public Category getCurrentMainCategory() {
		return this.currentMainCategory;
	}

	public void newBudgetItem() {
		account.addBudgetItem(this.getCurrentMainCategory(), 0.0);
		pcs.firePropertyChange("new_budgetitem", null, null);
	}

	public void removeBudgetItem(BudgetItem item) {
		account.removeBudgetItem(item);
		pcs.firePropertyChange("removed_budgetitem", null, null);
	}

	public void replaceBudgetItems(List<BudgetItem> budgetItems) {
		List<BudgetItem> removeList = account.getBudgetItems(this
				.getCurrentMainCategory());

		for (BudgetItem item : removeList) {
			account.removeBudgetItem(item);
		}

		for (BudgetItem item : budgetItems) {
			account.addBudgetItem(item.getCategory(), item.getValue());
		}

		pcs.firePropertyChange("replaced_budgetitem_list", null, null);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void setEditMode(boolean set) {
		if (this.editMode != set) {
			this.editMode = set;
			pcs.firePropertyChange("changed_editmode", null, null);
		}
	}

	public boolean isEditMode() {
		return this.editMode;
	}

}
