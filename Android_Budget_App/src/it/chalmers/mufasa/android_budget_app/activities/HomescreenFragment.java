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
package it.chalmers.mufasa.android_budget_app.activities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.R.layout;
import it.chalmers.mufasa.android_budget_app.R.menu;
import it.chalmers.mufasa.android_budget_app.controller.TransactionListController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomescreenFragment extends Fragment implements PropertyChangeListener {
	private Account account;
	private LayoutInflater inflater;
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_homescreen, container,
				false);
		this.account = Account.getInstance(this.getActivity());
		this.account.addPropertyChangeListener(this);
		TextView tv = (TextView) view.findViewById(R.id.Balancefield);
		tv.setText(this.account.getBalance() + "kr");
		return view;
	}
	public void setNewBalance(double balance){
		
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
