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

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OptionsFragment extends Fragment{
	private Account account;
	private LayoutInflater inflater;
	private View view;
	private OptionsController controller;

	private Button addBalanceButton;
	private Button clearTransactionButton;
	private Button clearBudgetButton;
	private Button manageCategoryButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_options,
				container, false);
		this.inflater = inflater;
		this.controller = new OptionsController(this.getActivity());
		setupOnClickListeners();

		return view;

	}
	/**
	 * Sets up clickListeners for the buttons in the option view.
	 */
	private void setupOnClickListeners() {
		this.addBalanceButton = (Button) view.findViewById(R.id.addBalance);
		this.clearTransactionButton = (Button) view.findViewById(R.id.clearTransactions);
		this.clearBudgetButton = (Button) view.findViewById(R.id.clearBudget);
		this.manageCategoryButton = (Button) view.findViewById(R.id.manageCategories);
		
		this.addBalanceButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((HostActivity)getActivity()).changeFragment(new BalanceFragment());
			}
		});
		
		this.clearTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.clearTransactions();
			}
		});
		
		this.clearBudgetButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.clearBudget();
			}
		});
		
		this.manageCategoryButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((HostActivity)getActivity()).changeFragment(new ManageCategoryFragment());
			}
		});
		
		
	}
}
