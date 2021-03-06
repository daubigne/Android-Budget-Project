 /*
  * Copyright � 2012 Mufasa developer unit
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

import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;

/**
 * version 1
 * 
 * @author FannyM 
 * A class which updates the model.
 */

public class ManageCategoryController {

	private ManageCategoryModel model;

	public ManageCategoryController(ManageCategoryModel model) {
		this.model = model;
	}

	public void setCurrentParentCategory(int id) {
		model.setCurrentParentCategory(id);
	}

	public void addCategory(String name, Category parent) {
		model.addCategory(name, parent);
	}

	public void removeCategory(Category category) {
		model.removeCategory(category);
	}

	public void editCategory(Category category, String newName) {
		model.editCategory(category, newName);
	}

	public void setEditMode(boolean b) {
		model.setEditMode(b);
	}

}
