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

import it.chalmers.mufasa.android_budget_app.model.Account;
import android.content.Context;

/**
 * 
 * @author daubigne
 * 
 * A class that work calls between account and OptionsFragment.
 *
 */
public class OptionsController {
	private Account account;
	
	public OptionsController(Context context){
		this.account = Account.getInstance(context);
	}
	
	public void clearTransactions() {
		this.account.removeAllTransactions();	
	}
	
	public void clearBudget(){
		this.account.removeBudget();
	}
	public void addBalance(double balance){
		this.account.setBalance(balance);
	}

}
