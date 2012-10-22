package it.chalmers.mufasa.android_budget_app.utilities;

import it.chalmers.mufasa.android_budget_app.R;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

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
		return 2;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		if (position == 0) {
			View title = LayoutInflater.from(context).inflate(R.layout.graph_account_balance_title, null);
			return title;
		} else if (position == 1) {
			if (values.size() > 0) {
				GraphViewData[] graphViewData = new GraphViewData[values.size()];

				for (int i = 0; i < values.size(); i++) {
					graphViewData[i] = new GraphViewData(i + 1, values.get(i));
				}

				GraphViewSeries graphViewSeries = new GraphViewSeries(graphViewData);

				LineGraphView graphView = new LineGraphView(context, "");
				graphView.addSeries(graphViewSeries);
				graphView.setMinimumHeight(370);

				LayoutParams params = new LayoutParams();

				params.topMargin = 0;
				params.bottomMargin = 30;
				params.leftMargin = 30;
				params.rightMargin = 30;

				graphView.setLayoutParams(params);

				graphView.setDrawBackground(true);

				Double max = Collections.max(values);
				Double min = Collections.min(values);
				Double range = Math.max(Math.abs(max), Math.abs(min));
				range = Math.round(range / 1000.0) * 1000.0; // Round to closest 1000

				graphView.setManualYAxisBounds(range + 1000, 0);

				LinearLayout layout = new LinearLayout(context);
				layout.addView(graphView);

				return layout;
			} else {
				TextView textView = new TextView(context);
				textView.setText("No graph data");
				textView.setMinimumHeight(500);
				return textView;
			}
		} else {
			TextView textView = new TextView(context);
			return textView;
		}
	}
}
