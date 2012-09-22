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
		this.setBalance(this.da.getAccountBalance(0));
	}
	
	public void setBalance(int balance) {
		this.da.setAccountBalance(balance, 0);
		model.setBalance(balance);
	}
	
}
