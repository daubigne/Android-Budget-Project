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
