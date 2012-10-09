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
public class AccountBalanceGraphViewFragment extends Fragment{

	private LayoutInflater inflater;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		this.inflater = inflater;
		
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
			      new GraphViewData(1, 2.0d)  
			      , new GraphViewData(2, 1.5d)  
			      , new GraphViewData(3, 2.5d)  
			      , new GraphViewData(4, 1.0d)  
			});  
		GraphView graphView = new LineGraphView(inflater.getContext(), "GraphViewDemo");
		
		graphView.addSeries(exampleSeries);
		
		this.view = graphView;
		
		return this.view;
	}
}
