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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionListController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.TransactionListModel;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * A class for taking user input from the transaction list.
 * @author slurpo
 */
public class TransactionListActivity extends Activity implements
		PropertyChangeListener {

	private TransactionListController controller;
	private TransactionListModel model;
	private Account account;

	private EditText transactionNameField;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	private ArrayList<String> transactionListString;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_list);

		transactionNameField = (EditText) findViewById(R.id.transactionNameField);
		listView = (ListView) findViewById(R.id.transactionList);

		this.account = Account.getInstance(this.getApplicationContext());
		this.controller = new TransactionListController(this.account);
		this.account.addPropertyChangeListener(this);

		transactionListString = new ArrayList<String>();
		//controller.updateTransactionHistory();
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,
				transactionListString);
		updateTransactionList();

	}

	/**
	 * Takes data from textfields and stores it as a trasnsaction.
	 */
	public void saveTransaction(View view) {
		Category cat = new Category("CatFromSaveTransaction", 1, null);
		
		// TODO: If nothing is written in the text field?
		String amount;
		try {
			amount = this.transactionNameField.getText().toString();
		} catch (NumberFormatException npe) {
			return;
		}
		// TODO: This function should get more user input in the future.
		controller.addTransaction(Double.parseDouble(amount), new Date(), "",
				cat);
	}
	
	/**
	 * Fetches all transaction from the database and set it to a list.
	 */
	private void updateTransactionList() {
		transactionListString.clear();
		for (Transaction t : account.getTransactions(Constants.NUMBER_OF_TRANSACTIONS)) {
			transactionListString.add(t.getAmount() + "");
		}
		listView.setAdapter(listAdapter);
	}

	// TODO: Should receive a specific transaction to remove.
	/**
	 * Removes the first transaction in the transaction list.
	 */
	public void removeTransaction(View view) {
		List<Transaction> transactionList = account.getTransactions(Constants.NUMBER_OF_TRANSACTIONS);
		if (transactionList.isEmpty()) {
			return;
		}
		controller.removeTransaction(account.getTransactions(1).get(0));
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("Transactions Updated")) {
			updateTransactionList();
		}
	}

}
