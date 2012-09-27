package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ModelListener;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * A class that responds to user input.
 */

public class MainActivity extends Activity implements PropertyChangeListener {

	private TransactionListController controller;
	private MainModel model;

	private EditText balanceField;
	private EditText transactionNameField;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	private ArrayList<String> transactionListString;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		balanceField = (EditText) findViewById(R.id.accountBalanceField);
		transactionNameField = (EditText) findViewById(R.id.transactionNameField);
		listView = (ListView) findViewById(R.id.transactionList);

		this.model = new MainModel();
		this.controller = new TransactionListController(this.getApplicationContext(),model);
		this.model.addPropertyChangeListener(this);
		
		transactionListString = new ArrayList<String>();
		controller.updateTransactionHistory();
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, transactionListString);  
		updateTransactionList();
		
		
	}
	
	public void saveTransaction(View view) {
		Category cat = new Category("CatFromSaveTransaction", 1, null);
		//TODO: If nothing is written in the text field?
		String amount;
		try{
			amount = this.transactionNameField.getText().toString();
		} catch(NumberFormatException npe){
			return;
		}
		
		//TODO: This function should get more user input in the future.
		controller.addTransaction(Double.parseDouble(amount), new Date(), "", cat, model.getAccount());
		
	}
	
	public void updateTransactionList(){
		transactionListString.clear();
		for (Transaction t : model.getTransactionHistory()) {
			transactionListString.add(t.getAmount() + "");
		}
		listView.setAdapter(listAdapter);
	}
	
	//TODO: Should receive a specific transaction to remove.
	public void removeTransaction(View view){
		List<Transaction> transactionList = controller.getTransactionHistory();
		if(transactionList.isEmpty()){
			System.out.println("Hej");
			return;
		}
		controller.removeTransaction(controller.getTransactionHistory().get(0));
	}

	public void saveBalance(View view) {
		controller.setBalance(Integer.parseInt(this.balanceField.getText().toString()));
	}

	public void onChange(MainModel model) {
		this.balanceField.setText(Double.toString(model.getBalance()));
	}

	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equals("Transaction Updated")){
			updateTransactionList();
		}
	}
}
