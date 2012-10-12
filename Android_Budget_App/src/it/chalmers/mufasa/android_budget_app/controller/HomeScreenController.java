package it.chalmers.mufasa.android_budget_app.controller;

import it.chalmers.mufasa.android_budget_app.model.HomeScreenModel;

public class HomeScreenController {
	HomeScreenModel model;
	
	public HomeScreenController(HomeScreenModel model){
		this.model = model;
	}

	public void calculatePercentage() {
		this.model.calculatePercentage();
	}
	
}
