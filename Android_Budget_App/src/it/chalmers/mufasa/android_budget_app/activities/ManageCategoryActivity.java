package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.controller.ManageCategoryController;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class ManageCategoryActivity extends Activity {

	private ManageCategoryController controller;
	private MainModel model;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage_category, menu);
        return true;
    }
    
    public void addCategory(View view) {
    	controller.addCategory(name, parent);
    }
    
    public void removeCategory(View view) {
    	controller.removeCategory(category);
    }
    
    public void editCategory(View view) {
    	controller.editCategory(category, newName);
    }
    
    
}
