package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.controller.ManageCategoryController;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;
import it.chalmers.mufasa.android_budget_app.model.ModelListener;
import android.app.Activity;

public class ManageCategoryActivity extends Activity implements ModelListener{

	ManageCategoryController controller;
	ManageCategoryModel model;
	
	
	
	public void onChange(MainModel model) {
		// TODO Auto-generated method stub
		
	}

}
