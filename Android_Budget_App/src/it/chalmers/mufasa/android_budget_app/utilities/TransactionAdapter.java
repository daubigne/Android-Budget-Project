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
package it.chalmers.mufasa.android_budget_app.utilities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * A class for formatting the list rows in the transaction list.
 * 
 * @author slurp
 * 
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> {

	private List<Transaction> transactions;
	private Context context;
	private View view;
	private Boolean isEditMode;

	public TransactionAdapter(Context context, int textViewResourceId,
			List<Transaction> objects, boolean isEditMode) {
		super(context, textViewResourceId, textViewResourceId, objects);
		this.transactions = objects;
		this.context = context;
		setEditMode(isEditMode);
		
	}

	/**
	 * Uses the layout transaction_list_row to show transaction information.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (isEditMode) {
				view = inflater.inflate(R.layout.transaction_edit_list_row,
						null);
			} else {
				view = inflater.inflate(R.layout.transaction_list_row, null);
			}
		}

		Transaction item = transactions.get(position);
		if (item != null) {
			TextView nameView = (TextView) view.findViewById(R.id.nameTextView);
			TextView amountView = (TextView) view
					.findViewById(R.id.amountTextView);
			TextView categoryView = (TextView) view
					.findViewById(R.id.categoryTextView);
			TextView dateView = (TextView) view.findViewById(R.id.dateTextView);

			nameView.setText(item.getName());
			amountView.setText(item.getAmount() + "kr");
			categoryView.setText(item.getCategory().getName());

			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = df.format(item.getDate());
			dateView.setText(dateString);

			if (isEditMode) {
				setupOnClickListeners(item);
			}
		}

		return view;
	}

	/**
	 * Sets onClickListeners for the buttons in the layout.
	 */
	private void setupOnClickListeners(final Transaction transaction) {
		Button removeTransactionButton = (Button) view
				.findViewById(R.id.removeTransactionButton);
		removeTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Account.getInstance(context).removeTransaction(transaction);
			}
		});

	}
	
	/**
	 * Switches the editMode on or off depending on the give boolean.
	 */
	public void setEditMode(boolean isEditMode){
		this.isEditMode = isEditMode;
	}
}
