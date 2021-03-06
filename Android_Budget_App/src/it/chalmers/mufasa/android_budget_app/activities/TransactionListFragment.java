 /*
  * Copyright � 2012 Mufasa developer unit
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
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import it.chalmers.mufasa.android_budget_app.utilities.TransactionAdapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * A fragment for displaying all transactions made by the user.
 * 
 * @author marcusisaksson
 * 
 */
public class TransactionListFragment extends Fragment implements
		PropertyChangeListener {

	private View view;
	private Account account;
	private TransactionController controller;
	private List<Transaction> transactionList;
	private ListView listView;
	private TransactionAdapter listAdapter;

	/**
	 * Sets up its view when created.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.view = inflater.inflate(R.layout.fragment_transaction_list,
				container, false);

		this.account = Account.getInstance(this.getActivity());
		account.addPropertyChangeListener(this);
		this.controller = new TransactionController(account);

		setupTransactionList();

		setupOnClickListeners();

		updateIncomeExpensesButtons();

		return view;
	}

	/**
	 * Used when the fragment is created to set up the listView.
	 */
	private void setupTransactionList() {
		transactionList = controller
				.getTransactions(Constants.NUMBER_OF_TRANSACTIONS);

		listAdapter = new TransactionAdapter(getActivity(),
				R.layout.transaction_list_row, transactionList,
				controller.isEditMode());

		listView = (ListView) view.findViewById(R.id.transactionList);
		listView.setAdapter(listAdapter);
	}

	/**
	 * Fetches all transactions and updates its listView.
	 */
	private void updateTransactionList() {
		listAdapter.setEditMode(controller.isEditMode());
		transactionList = controller
				.getTransactions(Constants.NUMBER_OF_TRANSACTIONS);
		listView.setAdapter(listAdapter);
	}

	/**
	 * Adds actions to button clicks.
	 */
	private void setupOnClickListeners() {
		Button addTransactionButton = (Button) view
				.findViewById(R.id.addTransactionButton);
		addTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((HostActivity) getActivity())
						.changeFragment(new AddTransactionFragment(
								TransactionListFragment.this.controller));
			}
		});
		Button expensesButton = (Button) view
				.findViewById(R.id.transactionListExpensesSwitchButton);
		expensesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.switchToExpenses();
				updateIncomeExpensesButtons();
				updateTransactionList();
			}
		});
		Button incomeButton = (Button) view
				.findViewById(R.id.transactionListIncomeSwitchButton);
		incomeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.switchToIncome();
				updateIncomeExpensesButtons();
				updateTransactionList();

			}
		});
		final Button editTransactionButton = (Button) view
				.findViewById(R.id.editTransactionButton);
		editTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (controller.isEditMode()) {
					controller.setEditMode(false);
					editTransactionButton.setText("Edit");
				} else {
					controller.setEditMode(true);
					editTransactionButton.setText("Done");
				}
				updateTransactionList();

			}
		});

	}

	/**
	 * Switches the income/expenses buttons "on" and "off".
	 */
	public void updateIncomeExpensesButtons() {
		if (controller.getCurrentMainCategory().getId() == Constants.EXPENSE_ID) {
			view.findViewById(R.id.transactionListIncomeSwitchButton)
					.setEnabled(true);
			view.findViewById(R.id.transactionListExpensesSwitchButton)
					.setEnabled(false);
		} else {
			view.findViewById(R.id.transactionListIncomeSwitchButton)
					.setEnabled(false);
			view.findViewById(R.id.transactionListExpensesSwitchButton)
					.setEnabled(true);
		}
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("Transactions Updated")) {
			updateTransactionList();
		}
	}

}
