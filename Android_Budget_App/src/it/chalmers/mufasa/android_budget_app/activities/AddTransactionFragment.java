package it.chalmers.mufasa.android_budget_app.activities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.interfaces.ChooseCategoryInterface;
import it.chalmers.mufasa.android_budget_app.interfaces.DateDialogFragmentListener;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
public class AddTransactionFragment extends Fragment implements
		DateDialogFragmentListener, ChooseCategoryInterface {

	private LayoutInflater inflater;
	private View view;
	private Account account;
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
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_add_transaction,
				container, false);

		calendar = Calendar.getInstance();

		this.account = Account.getInstance(this.getActivity());
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
				AddTransactionFragment.this.chooseCategory(v);
			}
		});

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
	private void saveTransaction(View v) {
		EditText nameEdit = (EditText) view
				.findViewById(R.id.transactionNameEditText);

		EditText amountEdit = (EditText) view
				.findViewById(R.id.transactionAmountEditText);

		controller.addTransaction(
				Double.parseDouble(amountEdit.getText().toString()),
				this.calendar.getTime(), nameEdit.getText().toString(),
				choosenCategory);
	}

	public void dateDialogFragmentDateSet(Calendar date) {
		this.calendar = date;
		updateDateText();
	}

	private void chooseCategory(View v) {
		this.chooseCategoryFragment = new ChooseCategoryFragment(this,
				controller.getCurrentMainCategory().getId());
		FragmentManager fm = ((HostActivity) getActivity())
				.getFragmentManager();

		chooseCategoryFragment.show(fm, "");
		updateCategoryText();
	}

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

	public void chooseCategoryCategoryChosen(Category category) {
		this.choosenCategory = category;
		chooseCategoryFragment.dismiss();
	}
}
