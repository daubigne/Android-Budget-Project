package it.chalmers.mufasa.android_budget_app.utilities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A class for formatting the list rows in the transaction list.
 * @author slurp
 *
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> {

	private List<Transaction> transactions;
	private Context context;

	public TransactionAdapter(Context context, int textViewResourceId,
			List<Transaction> objects) {
		super(context, textViewResourceId, textViewResourceId, objects);
		this.transactions = objects;
		this.context = context;
	}

	/**
	 * Uses the layout transaction_list_row to show transaction information.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.transaction_list_row, null);
		}

		Transaction item = transactions.get(position);
		if (item != null) {
			TextView nameView = (TextView) view.findViewById(R.id.nameTextView);
			TextView amountView = (TextView) view
					.findViewById(R.id.amountTextView);
			TextView categoryView = (TextView) view
					.findViewById(R.id.categoryTextView);

			nameView.setText(item.getName());
			amountView.setText(item.getAmount() + "");
			categoryView.setText(item.getCategory().getName());
		}

		return view;
	}
}
