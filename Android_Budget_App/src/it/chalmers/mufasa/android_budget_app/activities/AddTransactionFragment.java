package it.chalmers.mufasa.android_budget_app.activities;

import java.util.Date;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/**
 * A fragment for adding transactions.
 * @author marcusisaksson
 *
 */
public class AddTransactionFragment extends Fragment {
	
	private LayoutInflater inflater;
	private View view;
	private Account account;
	private TransactionController controller;
	
	/**
	 * Sets up the fragment when created.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_add_transaction, container, false);

		this.account = Account.getInstance(this.getActivity());
		this.controller = new TransactionController(account);

		this.setupOnClickListeners();

		return view;
	}
	
	/**
	 * Sets up click listeners.
	 */
	private void setupOnClickListeners() {
		Button addTransactionButton = (Button) view.findViewById(R.id.addTransactionButton);
		Button dateTransactionButton = (Button) view.findViewById(R.id.transactionDateButton);

		
		addTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AddTransactionFragment.this.saveTransaction(v);
				((HostActivity)getActivity()).changeFragment(new TransactionListFragment());
			}
		});
		
		dateTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((HostActivity)getActivity()).showDialog(HostActivity.MY_DIALOG_ID);
			}
		});
		
	}
		

	/**
	 * Gathers data from the user and stores it as a transaction.
	 */
	private void saveTransaction(View v) {
		EditText nameEdit = (EditText)view.findViewById(R.id.transactionNameEditText);
		//EditText dateEdit = (EditText)view.findViewById(R.id.transactionDateEditText);
		EditText amountEdit = (EditText)view.findViewById(R.id.transactionAmountEditText);
		
		controller.addTransaction(Double.parseDouble(amountEdit.getText().toString()),
				new Date(), nameEdit.getText().toString(), new Category("TempCat", 1, null));
	}
}
