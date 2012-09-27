package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.BudgetEditController;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BudgetEditActivity extends Activity {

	BudgetEditController controller;
	BudgetEditModel model;
	
	private ListView listView;
	private ArrayAdapter<BudgetItem> listAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_edit);
        
        this.model = new BudgetEditModel();
        this.controller = new BudgetEditController(this.getApplicationContext(), model);
        
        //Category cat = new Category("Mat",1,null);
        
        listAdapter = new ArrayAdapter<BudgetItem>(this, R.layout.simplerow, model.getBudgetItems());
        
        listView = (ListView) findViewById(R.id.budgetItemList);
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_budget_edit, menu);
        return true;
    }
}
