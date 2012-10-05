package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ModelListener;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

	private MainController controller;
	private MainModel model;

	private EditText balanceField;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		balanceField = (EditText) findViewById(R.id.accountBalanceField);

		this.model = new MainModel();
		this.controller = new MainController(this.getApplicationContext(),model);
		this.model.addPropertyChangeListener(this);
		
		this.balanceField.setText(Double.toString(model.getBalance()));
	}

	public void saveBalance(View view) {
		controller.setBalance(Integer.parseInt(this.balanceField.getText().toString()));
	}

	public void propertyChange(PropertyChangeEvent event) {
		this.balanceField.setText(Double.toString(model.getBalance()));
	}
}
