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
package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.interfaces.ChooseCategoryInterface;
import it.chalmers.mufasa.android_budget_app.interfaces.DateDialogFragmentListener;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A fragment for registering transactions.
 * 
 * @author marcusisaksson
 * 
 */
public class AddTransactionFragment extends Fragment implements
		DateDialogFragmentListener, ChooseCategoryInterface {

	private View view;
	private TransactionController controller;
	private Category choosenCategory;

	private Calendar calendar;

	private ChooseCategoryFragment chooseCategoryFragment;

	static final int DATE_DIALOG_ID = 0;

	private Button addTransactionButton;
	private Button dateTransactionButton;
	private Button chooseCategoryButton;
	private TextView transactionDateTextView;
	private TextView transactionCategoryTextView;

	public AddTransactionFragment(TransactionController controller) {
		super();
		this.controller = controller;
	}

	/**
	 * Sets up the fragment when created.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.view = inflater.inflate(R.layout.fragment_add_transaction,
				container, false);

		calendar = Calendar.getInstance();
		chooseCategoryButton = (Button) view
				.findViewById(R.id.chooseTransactionCategoryButton);
		addTransactionButton = (Button) view
				.findViewById(R.id.addTransactionButton);
		dateTransactionButton = (Button) view
				.findViewById(R.id.transactionDateButton);

		transactionDateTextView = (TextView) view
				.findViewById(R.id.transactionDateTextView);
		transactionCategoryTextView = (TextView) view
				.findViewById(R.id.transactionCategoryTextView);

		this.setupOnClickListeners();

		choosenCategory = controller.getCurrentMainCategory();

		updateDateText();
		updateCategoryText();

		return view;
	}

	/**
	 * Sets up click listeners.
	 */
	private void setupOnClickListeners() {

		chooseCategoryButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AddTransactionFragment.this.chooseCategory();
			}
		});

		addTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AddTransactionFragment.this.saveTransaction();
				((HostActivity) getActivity())
						.changeFragment(new TransactionListFragment());
			}
		});

		dateTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// create new DateDialogFragment
				DateDialogFragment ddf = DateDialogFragment.newInstance(
						((HostActivity) getActivity()), R.string.set_date,
						calendar);

				ddf.setDateDialogFragmentListener(new DateDialogFragmentListener() {

					public void dateDialogFragmentDateSet(Calendar date) {
						// update the fragment
						AddTransactionFragment.this
								.dateDialogFragmentDateSet(date);
					}
				});

				ddf.show(getFragmentManager(), "date picker dialog fragment");
			}
		});

	}

	/**
	 * Gathers data from the user and stores it as a transaction.
	 */
	private void saveTransaction() {
		EditText nameEdit = (EditText) view
				.findViewById(R.id.transactionNameEditText);

		EditText amountEdit = (EditText) view
				.findViewById(R.id.transactionAmountEditText);
		
		try{
			controller.addTransaction(
					Double.parseDouble(amountEdit.getText().toString()),
					this.calendar.getTime(), nameEdit.getText().toString(),
					choosenCategory);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Sets the date of the transaction.
	 */
	public void dateDialogFragmentDateSet(Calendar date) {
		this.calendar = date;
		updateDateText();
	}

	/**
	 * Opens up a chooseCategory fragment.
	 */

	private void chooseCategory() {
		this.chooseCategoryFragment = new ChooseCategoryFragment(this,
				controller.getCurrentMainCategory().getId());
		FragmentManager fm = ((HostActivity) getActivity())
				.getFragmentManager();

		chooseCategoryFragment.show(fm, "");
		updateCategoryText();
	}

	/**
	 * Updates the tex where the choosen date of the transaction is shown.
	 */
	private void updateDateText() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = df.format(calendar.getTime());
		transactionDateTextView.setText(dateString);
	}

	// TODO: Doesn't update.
	public void updateCategoryText() {
		transactionCategoryTextView.setText(controller.getCurrentMainCategory()
				.getName());
	}

	/**
	 * Sets the category.
	 */
	public void chooseCategoryCategoryChosen(Category category) {
		this.choosenCategory = category;
		//Used only by a chooseCategoryFragment which needs to be dismissed when it's done.
		chooseCategoryFragment.dismiss();
	}
}
