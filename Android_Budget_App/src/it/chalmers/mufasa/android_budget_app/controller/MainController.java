package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.MainModel;

public class MainController {
	MainModel model;
	
	public MainController(MainModel model) {
		this.model = model;
	}
	
	public void setBalance(int balance) {
		model.setBalance(balance);
	}
	
}
