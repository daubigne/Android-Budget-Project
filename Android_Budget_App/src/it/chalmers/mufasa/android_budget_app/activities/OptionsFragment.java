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

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.OptionsController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.OptionsModel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OptionsFragment extends Fragment{
	private Account account;
	private LayoutInflater inflater;
	private View view;
	private OptionsController controller;
	private OptionsModel model;
	private Button addBalanceButton;
	private Button clearTransactionButton;
	private Button clearBudgetButton;
	private Button manageCategoryButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.model = new OptionsModel(this.getActivity());
		this.inflater = inflater;
		this.controller = new OptionsController(this.model);
		
		this.addBalanceButton = (Button) view.findViewById(R.id.addBalance);
		this.clearTransactionButton = (Button) view.findViewById(R.id.clearTransactions);
		this.clearBudgetButton = (Button) view.findViewById(R.id.clearBudget);
		this.manageCategoryButton = (Button) view.findViewById(R.id.manageCategories);

		return view;
	}
}
