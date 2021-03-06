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
package it.chalmers.mufasa.android_budget_app.model;

import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.Context;
/**
 * 
 * @author daubigne
 * 
 * A class that represent the homescreen's state and the appropriate calls to the account.
 *
 */
public class HomeScreenModel { 
	private Account account;
	private double percentage;
	private double leftInMonth;
	
	public HomeScreenModel(Context context) {
		this.account = Account.getInstance(context);
		this.percentage = 0.0;
		this.leftInMonth = 0.0;
	}
	public double getBalance(){
		return this.account.getBalance();
	}
	public Account getAccount(){
		return this.account;
	}
	public double getPercentage(){
		return this.percentage;
	}
	
	public double getLeftInMonth(){
		return this.leftInMonth;
	}
	
	/**
	 * Calculates how much percentage of the budget that the user has left. The method reads the monthly 
	 * transactions through the account and compares it to the monthly budget. Uses Date from GregorianCalendar.
	 * 
	 */
	public void calculatePercentage() {
		Calendar c = new GregorianCalendar();
		Date to = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, 0);
		Date from = c.getTime();
		//Make sure to calculate with double's . 
		
		double budgetItemsSum = this.account.getBudgetItemsSum(Constants.EXPENSE_ID);
		double transactionsSum = this.account.getTransactionsSum(from,to,Constants.EXPENSE_ID);
		
		this.leftInMonth = budgetItemsSum-transactionsSum;
		this.percentage = 1.0-(transactionsSum
				/ budgetItemsSum);

	}
	
}
