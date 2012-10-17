package it.chalmers.mufasa.android_budget_app.utilities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.model.GraphViewModel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;


public class GraphViewAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater inflater;
	private GraphViewModel model;

	public GraphViewAdapter(Context context, GraphViewModel model) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.model = model;
	}
	
	public int getCount() {
		return 12;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			
			View view = inflater.inflate(R.layout.graph_list, null);
			ListView listView = (ListView) view.findViewById(R.id.graphListView);
			
			listView.setAdapter(new GraphListAdapter(inflater.getContext(), model.getAccountBalanceListForGraph(position)));
			
			convertView = view;
			
//			View view = inflater.inflate(R.layout.graph_scroll_list, null);
//			LinearLayout layout = (LinearLayout) view.findViewById(R.id.graphList);
//			layout.addView((new AccountBalanceGraphViewFragment()).onCreateView(inflater, parent, null));
//			layout.addView((new AccountBalanceGraphViewFragment()).onCreateView(inflater, parent, null));
//			layout.addView((new AccountBalanceGraphViewFragment()).onCreateView(inflater, parent, null));
//			convertView = view;
		}
		return convertView;
	}

	public String getTitle(int position) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -position);
		
		return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + calendar.get(Calendar.YEAR);
	}

}
