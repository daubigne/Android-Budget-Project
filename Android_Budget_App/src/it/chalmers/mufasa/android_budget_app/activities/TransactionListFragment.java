package it.chalmers.mufasa.android_budget_app.activities;

import java.util.List;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.TransactionController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import it.chalmers.mufasa.android_budget_app.utilities.TransactionAdapter;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A fragment for displaying all transactions made by the user.
 * @author marcusisaksson
 *
 */
public class TransactionListFragment extends Fragment {

	private LayoutInflater inflater;
	private View view;
	private Account account;
	private TransactionController controller;
	private List<Transaction> transactionList;
	private ListView listView;
	private ListAdapter listAdapter;

	/**
	 * Sets up its view when created.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_transaction_list, container,
				false);

		this.account = Account.getInstance(this.getActivity());
		this.controller = new TransactionController(account);
		
		setupTransactionList();

		setupOnClickListeners();
		
		updateIncomeExpensesButtons();

		return view;
	}
	
	/**
	 * Used when the fragment is created to set up the listView.
	 */
	private void setupTransactionList(){
		transactionList = controller
				.getTransactions(Constants.NUMBER_OF_TRANSACTIONS);
		
		listAdapter = new TransactionAdapter(getActivity(),
				R.layout.transaction_list_row, transactionList);
		
		listView = (ListView) view.findViewById(R.id.transactionList);
		listView.setAdapter(listAdapter);
	}
	
	/**
	 * Fetches all transactions and updates its listView.
	 */
	private void updateTransactionList(){
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
				((HostActivity)getActivity()).changeFragment(new AddTransactionFragment());
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
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		          int position, long id) {
		    	  	showAlertRemoveTransactionBox(TransactionListFragment.this.getActivity(), "Do you want to delet this transaction?", position);
		    	  	
		      }
		});
	}
	
	/**
	 * Switches the income/expenses buttons "on" and "off".
	 */
	public void updateIncomeExpensesButtons() {
		if (controller.getCurrentMainCategory().getId() == 2) {
			view.findViewById(R.id.transactionListIncomeSwitchButton).setEnabled(true);
			view.findViewById(R.id.transactionListExpensesSwitchButton).setEnabled(false);
		} else {
			view.findViewById(R.id.transactionListIncomeSwitchButton).setEnabled(false);
			view.findViewById(R.id.transactionListExpensesSwitchButton).setEnabled(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void showAlertRemoveTransactionBox(Context context, String message,int position)
    {
        final int pos = position;

        final AlertDialog alertDialog = new  AlertDialog.Builder(context).create();
           alertDialog.setTitle("Removing transaction");
           alertDialog.setButton("Delete", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   try
                   {
                       controller.removeTransaction(transactionList.get(pos));
                       setupTransactionList();
                   }
                   catch(Exception e)
                   {
                	   return;
                   }
           } }); 
           alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   alertDialog.dismiss();
           } }); 

           alertDialog.setMessage(message);
           alertDialog.show();
    }

}
