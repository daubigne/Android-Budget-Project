 /*
  * Copyright © 2012-2015 Mufasa developer unit
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
package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.util.List;

public class BudgetEditController {
	
	BudgetEditModel model;
	
	public BudgetEditController(BudgetEditModel model) {
		this.model = model;
	}
	
	public void newBudgetItem() {
		model.newBudgetItem();
	}
	
	public void removeBudgetItem(BudgetItem bi) {
		model.removeBudgetItem(bi);
	}
	
	public void saveAllBudgetItems(List<BudgetItem> list) {
		model.replaceBudgetItems(list);
	}
	
	public void setEditMode(boolean set) {
		model.setEditMode(set);
	}

	public void switchToIncome() {
		model.setCurrentMainCategory(1);
	}
	
	public void switchToExpenses() {
		model.setCurrentMainCategory(2);
	}
}
