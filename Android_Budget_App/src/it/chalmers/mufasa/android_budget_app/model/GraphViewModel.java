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

import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor.AccountDay;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;

public class GraphViewModel {

	Account account;
	List<BudgetItem> budgetItems;

	private PropertyChangeSupport pcs;

	public GraphViewModel(Context context) {
		this.pcs = new PropertyChangeSupport(this);
		this.account = Account.getInstance(context);
	}
	
	public List<Double> getAccountBalanceListForGraph(int number) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.add(Calendar.MONTH, -number);
		Date from = calendar.getTime();		
		calendar.add(Calendar.MONTH, 1);		
		Date to = calendar.getTime();
		
		List<Double> result = getAccountBalanceForEachDay(from, to);

		Collections.reverse(result);
		
		while(result.size() < calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			result.add(result.get(result.size()-1));
		}
		
		return result;
	}

	/**
	 * Gets change of account balance each day over a specific time period. Handy for
	 * graph views.
	 * 
	 * @param from
	 *            from date
	 * @param to
	 *            to date
	 * @return 
	 */
	public List<Double> getAccountBalanceForEachDay(Date from, Date to) {
		
		List<Double> accountBalances = new ArrayList<Double>();
		
		for(AccountDay accountDay : account.getAccountBalanceForEachDay(from)) {
			if(accountDay.getDay().getTime() >= from.getTime() && accountDay.getDay().getTime() <= to.getTime()) {
				accountBalances.add(accountDay.getValue());
			}
		}
		
		return accountBalances;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
