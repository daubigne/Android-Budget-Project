package it.chalmers.mufasa.android_budget_app.activities;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class GraphListAdapter extends BaseAdapter {

	private List<Object> objects;
	private Context context;

	public GraphListAdapter(Context context, List<Object> objects) {
		this.objects = objects;
		this.context = context;
	}
	
    public int getCount() {
        return 100;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
			      new GraphViewData(1, 2.0d)  
			      , new GraphViewData(2, 1.5d)  
			      , new GraphViewData(3, 2.5d)  
			      , new GraphViewData(4, 1.0d)  
			});  
		
		GraphView graphView = new LineGraphView(context, "GraphViewDemo");
		graphView.addSeries(exampleSeries);
		graphView.setMinimumHeight(500);
		
		LinearLayout layout = new LinearLayout(context);
		layout.addView(graphView);
		
		return layout;
		
	}
}
