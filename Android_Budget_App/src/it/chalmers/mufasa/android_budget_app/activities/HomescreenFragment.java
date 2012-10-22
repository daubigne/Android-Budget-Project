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
import it.chalmers.mufasa.android_budget_app.controller.HomeScreenController;
import it.chalmers.mufasa.android_budget_app.model.HomeScreenModel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * @author daubigne
 * 
 *         A class that is the view of the home-screen displaying the user's current balance and a simple tool to keep track of the budget.
 * 
 */
public class HomescreenFragment extends Fragment {
	private View view;
	private TextView tv;
	private TextView tv2;
	private HomeScreenModel model;
	private HomeScreenController controller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.model = new HomeScreenModel(this.getActivity());
		this.controller = new HomeScreenController(this.model);

		this.view = inflater.inflate(R.layout.fragment_homescreen, null, false);
		// Create text fields and a progressbar to hold a balance and a comparison between the users' transactions and budgets.
		tv = (TextView) view.findViewById(R.id.Balancefield);
		tv2 = (TextView) view.findViewById(R.id.progressFeedback);
		TextView progressBarTextView = (TextView) view.findViewById(R.id.progressBarTextView);
		TextView progressBarTextViewRest = (TextView) view.findViewById(R.id.progressBarTextViewRest);
		// compares the user's transaction sum to the budget's total sum
		// and calculate how much % of the budget that the user has spent
		controller.calculatePercentage();
		// Fill these components with information
		tv.setText(model.getBalance() + "kr");
		double percentage = model.getPercentage();

		LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
		layoutParams.weight = (float) percentage;

		progressBarTextView.setLayoutParams(layoutParams);

		layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
		layoutParams.weight = (float) (1.0 - percentage);

		progressBarTextViewRest.setLayoutParams(layoutParams);
		tv2.setText(String.valueOf(model.getLeftInMonth()) + "kr");

		return view;
	}

}
