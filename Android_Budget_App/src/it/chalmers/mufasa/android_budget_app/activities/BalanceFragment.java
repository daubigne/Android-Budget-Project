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
import it.chalmers.mufasa.android_budget_app.controller.BalanceController;
import it.chalmers.mufasa.android_budget_app.model.BalanceModel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BalanceFragment extends Fragment{
	private EditText balanceInput;
	private Button saveBalanceButton;
	private BalanceModel model;
	private BalanceController controller;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.model = new BalanceModel(this.getActivity());
		this.controller = new BalanceController(this.model);
		this.view = inflater.inflate(R.layout.fragment_balance,
				container, false);
		this.balanceInput = (EditText) view.findViewById(R.id.balanceText);
		setupOnClickListeners();

		return view;
	}
	private void setupOnClickListeners() {
		saveBalanceButton = (Button) view
				.findViewById(R.id.saveBalance);
		saveBalanceButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.saveBalance(Double.valueOf((balanceInput.getText().toString())));
				((HostActivity)getActivity()).changeFragment(new OptionsFragment());
			}
		});
	}

}
