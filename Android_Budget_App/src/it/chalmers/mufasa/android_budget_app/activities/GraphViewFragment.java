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
import it.chalmers.mufasa.android_budget_app.controller.GraphViewController;
import it.chalmers.mufasa.android_budget_app.model.GraphViewModel;
import it.chalmers.mufasa.android_budget_app.utilities.GraphViewAdapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Activity for managing the users current budget. Includes adding, editing and removing budgetitem rows.
 * 
 * @author Simon
 * 
 */
public class GraphViewFragment extends Fragment implements PropertyChangeListener {

	private GraphViewController controller;
	private GraphViewModel model;

	private LayoutInflater inflater;
	private View view;
	private ViewFlow viewFlow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		this.model = new GraphViewModel(inflater.getContext());
		this.controller = new GraphViewController(model);
		
		this.view = inflater.inflate(R.layout.graph_title_layout, null);

		this.viewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
		GraphViewAdapter adapter = new GraphViewAdapter(inflater.getContext(),this.model);
		viewFlow.setAdapter(adapter,adapter.getCount());

		TitleFlowIndicator indicator = (TitleFlowIndicator) view
				.findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);

		viewFlow.setFlowIndicator(indicator);
		
		return this.view;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
