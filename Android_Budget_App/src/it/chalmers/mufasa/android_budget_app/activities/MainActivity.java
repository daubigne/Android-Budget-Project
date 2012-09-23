package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ModelListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements ModelListener {

	MainController controller;
	MainModel model;

	EditText balanceField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		balanceField = (EditText) findViewById(R.id.accountBalanceField);

		this.model = new MainModel();
		this.controller = new MainController(this.getApplicationContext(),model);

		this.model.addChangeListener(this);
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
		this.balanceField.setText(Integer.toString(model.getBalance()));
	}
}
