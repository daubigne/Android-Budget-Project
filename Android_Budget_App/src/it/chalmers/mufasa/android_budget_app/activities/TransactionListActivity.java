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
	private void saveTransaction(View view) {
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
				cat, this.account);

	}
	
	/**
	 * Fetches all transaction from the database and set it to a list.
	 */
	private void updateTransactionList() {
		transactionListString.clear();
		for (Transaction t : account.getTransactions(100)) {
			transactionListString.add(t.getAmount() + "");
		}
		listView.setAdapter(listAdapter);
	}

	// TODO: Should receive a specific transaction to remove.
	/**
	 * Removes the first transaction in the transaction list.
	 */
	private void removeTransaction(View view) {
		List<Transaction> transactionList = account.getTransactions(100);
		if (transactionList.isEmpty()) {
			return;
		}
		controller.removeTransaction(account.getTransactions(1).get(0));
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("Transaction Updated")) {
			updateTransactionList();
		}
	}

}
