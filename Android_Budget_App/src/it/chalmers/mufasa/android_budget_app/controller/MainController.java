package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.database.DataAccessor;


import android.content.Context;

public class MainController {
	
	private MainModel model;
	private DataAccessor dataAccessor;

	public MainController(Context context, MainModel model) {
		this.model = model;		
		
	}

	public void setBalance(double balance) {
		model.setBalance(balance);
	}

}
