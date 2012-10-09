package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.BudgetEditController;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * Activity for managing the users current budget. Includes adding, editing and removing budgetitem rows.
 * 
 * @author Simon
 * 
 */
public class GraphViewFragment extends Fragment implements PropertyChangeListener {

	private BudgetEditController controller;
	private BudgetEditModel model;

	private LayoutInflater inflater;
	private View view;
	private ViewFlow viewFlow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		this.inflater = inflater;
		
		LinearLayout layout = new LinearLayout(inflater.getContext());
		
		layout.setBackgroundColor(2);
		
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
			      new GraphViewData(1, 2.0d)  
			      , new GraphViewData(2, 1.5d)  
			      , new GraphViewData(3, 2.5d)  
			      , new GraphViewData(4, 1.0d)  
			});  
		GraphView graphView = new LineGraphView(inflater.getContext(), "GraphViewDemo");
		
		graphView.addSeries(exampleSeries);
		
//		this.view = inflater.inflate(R.layout.graph_title_layout, null);
//
//		this.viewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
//		AsyncAdapter adapter = new AsyncAdapter(inflater.getContext());
//		viewFlow.setAdapter(adapter, adapter.getTodayId());
//
//		TitleFlowIndicator indicator = (TitleFlowIndicator) view
//				.findViewById(R.id.viewflowindic);
//		indicator.setTitleProvider(adapter);
//
//		viewFlow.setFlowIndicator(indicator);
		
		layout.addView(graphView);
		
		this.view = layout;
		
		return this.view;
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
