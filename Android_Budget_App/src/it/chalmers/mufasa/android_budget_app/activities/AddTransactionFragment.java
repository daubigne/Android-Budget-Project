package it.chalmers.mufasa.android_budget_app.activities;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A fragment for adding transactions.
 * 
 * @author marcusisaksson
 * 
 */
public class AddTransactionFragment extends Fragment implements DateDialogFragmentListener {

	private LayoutInflater inflater;
	private View view;
	private Account account;
	private TransactionController controller;

	private int mYear;
	private int mMonth;
	private int mDay;
	
	private Calendar calendar;

	static final int DATE_DIALOG_ID = 0;

	/**
	 * Sets up the fragment when created.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_add_transaction,
				container, false);

		this.account = Account.getInstance(this.getActivity());
		this.controller = new TransactionController(account);

		this.setupOnClickListeners();

		calendar = Calendar.getInstance();
		mYear = calendar.get(Calendar.YEAR);
		mMonth = calendar.get(Calendar.MONTH);
		mDay = calendar.get(Calendar.DAY_OF_MONTH);

		
		return view;
	}

	/**
	 * Sets up click listeners.
	 */
	private void setupOnClickListeners() {
		Button addTransactionButton = (Button) view
				.findViewById(R.id.addTransactionButton);
		Button dateTransactionButton = (Button) view
				.findViewById(R.id.transactionDateButton);

		addTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AddTransactionFragment.this.saveTransaction(v);
				((HostActivity) getActivity())
						.changeFragment(new TransactionListFragment());
			}
		});

		dateTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// create new DateDialogFragment
				DateDialogFragment ddf = DateDialogFragment.newInstance(((HostActivity) getActivity()),
						R.string.set_date, calendar);

				ddf.setDateDialogFragmentListener(new DateDialogFragmentListener() {

					public void dateDialogFragmentDateSet(Calendar date) {
						// update the fragment
						AddTransactionFragment.this.dateDialogFragmentDateSet(date);
					}
				});

				ddf.show(getFragmentManager(),
						"date picker dialog fragment");
			}
		});

	}

	/**
	 * Gathers data from the user and stores it as a transaction.
	 */
	private void saveTransaction(View v) {
		EditText nameEdit = (EditText) view
				.findViewById(R.id.transactionNameEditText);

		EditText amountEdit = (EditText) view
				.findViewById(R.id.transactionAmountEditText);

		controller.addTransaction(Double.parseDouble(amountEdit.getText()
				.toString()), this.calendar.getTime(), nameEdit.getText()
				.toString(), new Category("TempCat", 1, null));
	}
	
	public void dateDialogFragmentDateSet(Calendar date) {
		this.calendar = date;
	}
}
