package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ModelListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * A class that responds to user input.
 */

public class MainActivity extends Activity implements ModelListener {

	private MainController controller;
	private MainModel model;

	private EditText balanceField;
	private EditText transactionNameField;
	private ListView listView;
	private ArrayAdapter<String> listAdapter;
	private ArrayList<String> transactionList;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		balanceField = (EditText) findViewById(R.id.accountBalanceField);
		transactionNameField = (EditText) findViewById(R.id.transactionNameField);
		listView = (ListView) findViewById(R.id.transactionList);
		transactionList = new ArrayList<String>();
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, transactionList);  


		this.model = new MainModel();
		this.controller = new MainController(this.getApplicationContext(),model);
		this.model.addChangeListener(this);
	}
	
	public void saveTransaction(View view) {
		//TODO: Very unfinished
		Category cat = new Category("Cat", 1, null);
		String transaction = this.transactionNameField.getText().toString();
		controller.addTransaction(Double.parseDouble(transaction), new Date(), "", cat, model.getAccount());
		
		transactionList.add(0,transaction);
		listView.setAdapter( listAdapter ); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void saveBalance(View view) {
		controller.setBalance(Integer.parseInt(this.balanceField.getText().toString()));
	}

	public void onChange(MainModel model) {
		this.balanceField.setText(Double.toString(model.getBalance()));
	}
}
