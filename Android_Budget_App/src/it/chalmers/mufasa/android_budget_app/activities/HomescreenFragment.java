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
import it.chalmers.mufasa.android_budget_app.R.layout;
import it.chalmers.mufasa.android_budget_app.R.menu;
import it.chalmers.mufasa.android_budget_app.controller.HomeScreenController;
import it.chalmers.mufasa.android_budget_app.model.HomeScreenModel;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * @author daubigne
 * 
 * A class that is the view of the home-screen displaying the user's current balance and a simple tool to keep track of the budget.
 *
 */
public class HomescreenFragment extends Fragment {
	private LayoutInflater inflater;
	private View view;
	private TextView tv;
	private TextView tv2;
	private ProgressBar progressBar;
	private HomeScreenModel model;
	private HomeScreenController controller;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.model = new HomeScreenModel(this.getActivity());
		this.inflater = inflater;
		this.controller = new HomeScreenController(this.model);


		this.view = inflater.inflate(R.layout.fragment_homescreen, container,
				false);
		//Create text fields and a progressbar to hold a balance and a comparison between the users' transactions and budgets.
		tv = (TextView) view.findViewById(R.id.Balancefield);
		tv2 = (TextView) view.findViewById(R.id.progressFeedback);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		//Fill these components with information
		tv.setText(model.getBalance() + "kr");
		controller.calculatePercentage();
		progressBar.setProgress((int)model.getPercentage());
		tv2.setText("Remaining % of your budget");
		
		return view;
	}
	
	//TODO
	private void setupOnClickListeners(){
		Button incomeButton = (Button) view
				.findViewById(R.id.homeScreenAddTransactionButton);
		
		incomeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});
	}

	//TODO: Method for swapping the percentage for something else, so that user can customize his or her homescreen.
}
