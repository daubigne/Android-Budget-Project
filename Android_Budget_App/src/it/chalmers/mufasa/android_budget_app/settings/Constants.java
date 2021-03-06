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
package it.chalmers.mufasa.android_budget_app.settings;

/**
 * A class holding constants which the whole application has acces to.
 */
public final class Constants {
	private Constants(){
	}
	//The ID of the only existing Account in the model.
	public static final int ACCOUNT_ID = 1;
	//Used when get transactions from the database.
	public static final int NUMBER_OF_TRANSACTIONS = 50;
	//The ID of the Category "Expenses" which is the parent of all expenses categories.
	public static final int EXPENSE_ID = 2;
	//The ID of the Category "Income" which is the parent of all income categories.
	public static final int INCOME_ID = 1;
	
	public static final int PROGRESS = 0x1;
}
