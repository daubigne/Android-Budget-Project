package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import android.content.Context;

public class MainController {
	MainModel model;

	DataAccessor da;

	public MainController(Context context, MainModel model) {
		this.model = model;
		this.da = new DataAccessor(context);
		try {
			model.setBalance(this.da.getAccountBalance(0));
		} catch (IllegalArgumentException e) { // Account 0 does not exist...
			model.setBalance(0);
		}
	}

	public void setBalance(int balance) {
		this.da.setAccountBalance(balance,0);
		model.setBalance(balance);
	}

}
