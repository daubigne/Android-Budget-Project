package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


public class GraphViewAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater inflater;

	public GraphViewAdapter(Context context) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		if (convertView == null) {
			View view = inflater.inflate(R.layout.graph_scroll_list, null);
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.graphList);
			layout.addView((new AccountBalanceGraphViewFragment()).onCreateView(inflater, parent, null));
			layout.addView((new AccountBalanceGraphViewFragment()).onCreateView(inflater, parent, null));
			layout.addView((new AccountBalanceGraphViewFragment()).onCreateView(inflater, parent, null));
			convertView = view;
		}
		return convertView;
	}

	public String getTitle(int position) {
		return "Graph "+position;
	}



}
