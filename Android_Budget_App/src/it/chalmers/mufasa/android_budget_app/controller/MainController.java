package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.MainModel;


import android.content.Context;

public class MainController {
	
	private MainModel model;
	private DataAccessor dataAccessor;

	public MainController(Context context, MainModel model) {
		this.model = model;
		this.dataAccessor = new DataAccessor(context);
		try {
			model.setBalance(this.dataAccessor.getAccount(1).getBalance());
		} catch (IllegalArgumentException e) { // Account 0 does not exist...
			this.dataAccessor.addAccount("My Account",0);
			model.setBalance(dataAccessor.getAccount(1).getBalance());
		}
	}

	public void setBalance(double balance) {
		this.dataAccessor.setAccountBalance(dataAccessor.getAccount(1),balance);
		model.setBalance(balance);
	}

}
