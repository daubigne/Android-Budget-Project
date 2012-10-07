package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionListController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A class for taking user input from the transaction list.
 * 
 * @author slurpo
 */
public class TransactionFragment extends Fragment implements
		PropertyChangeListener {

	private TransactionListController controller;
	private Account account;

	private LayoutInflater inflater;
	private View view;

	private EditText transactionNameField;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_transaction, container,
				false);

		this.account = Account.getInstance(this.getActivity());
		this.controller = new TransactionListController(this.account);
		this.account.addPropertyChangeListener(this);
		this.setupOnClickListeners();
		return view;
	}

	private void populateTransactionListView(List<Transaction> list) {
		LinearLayout budgetListLayout = (LinearLayout) view
				.findViewById(R.id.budgetItemListLayout);
		budgetListLayout.removeAllViews();
		if (controller.isEditMode()) {
			for (Transaction transaction : list) {
				View v = inflater.inflate(R.layout.budget_item_edit_row_edit,
						null);

				v.setTag(transaction);

				Button categoryButton = (Button) v
						.findViewById(R.id.budgetItemEditRowEditCategoryButton);
				categoryButton.setText(transaction.getCategory().getName());
				categoryButton.setTag(transaction.getCategory()); // Stores the
																	// budgetitem
																	// object in
																	// the
																	// button so
																	// it can be
																	// accessed
																	// in
																	// onClick
																	// events.

				categoryButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// this.chooseCategory(v);
					}
				});

				EditText valueTextEdit = (EditText) v
						.findViewById(R.id.budgetItemEditRowEditValueTextEdit);
				valueTextEdit.setText(transaction.getAmount() + "");

				Button removeButton = (Button) v
						.findViewById(R.id.budgetItemEditRowEditRemoveButton);
				removeButton.setTag(transaction);

				removeButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						TransactionFragment.this.removeTransaction(v);
					}

				});

				budgetListLayout.addView(v);
			}

			Button addRowButton = (Button) inflater.inflate(
					R.layout.budget_item_edit_row_addbutton, null);

			addRowButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					//TransactionFragment.this.saveTransaction(v);
				}
			});

			budgetListLayout.addView(addRowButton);
		} else {
			for (Transaction transaction : list) {
				View v = inflater.inflate(R.layout.budget_item_edit_row, null);

				TextView categoryButton = (TextView) v
						.findViewById(R.id.budgetItemEditRowCategoryTextView);
				categoryButton.setText(transaction.getCategory().getName());

				TextView amountTextEdit = (TextView) v
						.findViewById(R.id.budgetItemEditRowValueTextView);
				amountTextEdit.setText(transaction.getAmount() + "");

				budgetListLayout.addView(v);
			}
		}
	}

	private void setupOnClickListeners() {
		Button saveButton = (Button) view.findViewById(R.id.saveTransaction);
		Button removeButton = (Button) view
				.findViewById(R.id.removeTransaction);

		saveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//TransactionFragment.this.saveTransaction(v);
			}
		});

		removeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TransactionFragment.this.removeTransaction(v);
			}
		});
	}

	/**
	 * Takes data from textfields and stores it as a trasnsaction.
	 */
//	public void saveTransaction(View view) {
//		transactionNameField = (EditText) view
//				.findViewById(R.id.transactionNameField);
//
//		Category cat = new Category("CatFromSaveTransaction", 1, null);
//
//		// TODO: If nothing is written in the text field?
//		String amount;
//		try {
//			amount = this.transactionNameField.getText().toString();
//		} catch (NumberFormatException npe) {
//			return;
//		}
//		// TODO: This function should get more user input in the future.
//		controller.addTransaction(Double.parseDouble(amount), new Date(), "",
//				cat);
//		populateTransactionListView(account
//				.getTransactions(Constants.NUMBER_OF_TRANSACTIONS));
//	}

	public void enterEditMode(View view) {
		controller.setEditMode(true);
	}

	public void exitEditMode(View view) {

		List<Transaction> transactionList = new ArrayList<Transaction>();

		LinearLayout budgetListLayout = (LinearLayout) this.view.findViewById(R.id.budgetItemListLayout);
		for (int i = 0; i < budgetListLayout.getChildCount(); i++) {
			if (budgetListLayout.getChildAt(i) instanceof LinearLayout && budgetListLayout.getChildAt(i).getVisibility() != View.GONE) {
				LinearLayout v = (LinearLayout) budgetListLayout.getChildAt(i);

				if (v.getTag() instanceof Transaction) {

					Transaction transaction = (Transaction) v.getTag();

					Button categoryButton = (Button) v.findViewById(R.id.budgetItemEditRowEditCategoryButton);
					EditText valueText = (EditText) v.findViewById(R.id.budgetItemEditRowEditValueTextEdit);

					Category category = (Category) categoryButton.getTag();
					Double value = Double.parseDouble(valueText.getText().toString());

					// budgetItem.setCategory(category);
					// budgetItem.setValue(value);

					// rfnoneed
					transactionList.add(new Transaction(transaction.getId(),  value, new Date(), "hej", category));
				}
			}
		}

		// rfnoneed
		controller.saveAllTransactions(transactionList);

		controller.setEditMode(false);
	}

	// TODO: Should receive a specific transaction to remove.
	/**
	 * Removes the first transaction in the transaction list.
	 */
	public void removeTransaction(View view) {
		if (view.getTag() instanceof Transaction) {
			Transaction transaction = (Transaction) view.getTag();
			controller.removeTransaction(transaction);
		}
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("Transactions Updated")) {
			populateTransactionListView(account
					.getTransactions(Constants.NUMBER_OF_TRANSACTIONS));
		}
	}

}
