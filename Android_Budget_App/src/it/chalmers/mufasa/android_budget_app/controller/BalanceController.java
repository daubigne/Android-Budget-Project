package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.BalanceModel;

public class BalanceController {
	private BalanceModel model;
	
	public void saveBalance(double balance){
			this.model.saveBalance(balance);
	}
	public BalanceController(BalanceModel model){
		this.model = model;
	}

}
