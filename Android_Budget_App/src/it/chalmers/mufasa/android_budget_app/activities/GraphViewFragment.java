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
