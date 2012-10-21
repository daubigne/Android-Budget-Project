package it.chalmers.mufasa.android_budget_app.activities;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class GraphListAdapter extends BaseAdapter {

	private List<Double> values;
	private Context context;

	public GraphListAdapter(Context context, List<Double> values) {
		this.values = values;
		this.context = context;
	}

	public int getCount() {
		return 1;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		if (values.size() > 0) {
			GraphViewData[] graphViewData = new GraphViewData[values.size()];

			for (int i = 0; i < values.size(); i++) {
				graphViewData[i] = new GraphViewData(i + 1, values.get(i));
			}

			GraphViewSeries graphViewSeries = new GraphViewSeries(graphViewData);

			GraphView graphView = new LineGraphView(context, "Account balance");
			graphView.addSeries(graphViewSeries);
			graphView.setMinimumHeight(500);

			LinearLayout layout = new LinearLayout(context);
			layout.addView(graphView);

			return layout;
		} else {
			TextView textView = new TextView(context);
			textView.setText("No graph data");
			textView.setMinimumHeight(500);
			return textView;
		}
	}
}
