package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BudgetEditActivity extends Activity {

	private ListView listView;
	private ArrayAdapter<BudgetItem> listAdapter;
	private ArrayList<BudgetItem> budgetItemsList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_edit);
        
        Category cat = new Category("Mat",1,null);
        
        budgetItemsList = new ArrayList<BudgetItem>();
        budgetItemsList.add(new BudgetItem(1,cat,1000.0));
        budgetItemsList.add(new BudgetItem(2,cat,2000.0));
        budgetItemsList.add(new BudgetItem(3,cat,3000.0));
        
        listAdapter = new ArrayAdapter<BudgetItem>(this, R.layout.simplerow, budgetItemsList);
        
        
        listView = (ListView) findViewById(R.id.budgetItemList);
        listView.setAdapter(listAdapter); 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_budget_edit, menu);
        return true;
    }
}
