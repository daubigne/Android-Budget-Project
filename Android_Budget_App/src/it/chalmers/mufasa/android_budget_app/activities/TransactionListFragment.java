package it.chalmers.mufasa.android_budget_app.activities;

import java.util.List;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import it.chalmers.mufasa.android_budget_app.utilities.TransactionAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TransactionListFragment extends Fragment {

	private LayoutInflater inflater;
	private View view;
	private Account account;
	private TransactionController controller;
	private List<Transaction> transactionList;
	private ListView listView;
	private ListAdapter listAdapter;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_transaction_list, container,
				false);

		this.account = Account.getInstance(this.getActivity());
		this.controller = new TransactionController(account);
		
		transactionList = controller
				.getTransactions(Constants.NUMBER_OF_TRANSACTIONS);
		
		listAdapter = new TransactionAdapter(getActivity(),
				R.layout.transaction_list_row, transactionList);
		
		listView = (ListView) view.findViewById(R.id.transactionList);
		listView.setAdapter(listAdapter);

		this.setupOnClickListeners();
		
		updateIncomeExpensesButtons();

		return view;
	}
	
	private void updateTransactionList(){
		transactionList = controller
				.getTransactions(Constants.NUMBER_OF_TRANSACTIONS);
		listView.setAdapter(listAdapter);
	}

	private void setupOnClickListeners() {
		Button addTransactionButton = (Button) view
				.findViewById(R.id.addTransactionButton);
		addTransactionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((HostActivity)getActivity()).switchToAddTransactionFragment();
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
	}
	
	public void updateIncomeExpensesButtons() {
		if (controller.getCurrentMainCategory().getId() == 2) {
			view.findViewById(R.id.transactionListIncomeSwitchButton).setEnabled(true);
			view.findViewById(R.id.transactionListExpensesSwitchButton).setEnabled(false);
		} else {
			view.findViewById(R.id.transactionListIncomeSwitchButton).setEnabled(false);
			view.findViewById(R.id.transactionListExpensesSwitchButton).setEnabled(true);
		}
	}

}
