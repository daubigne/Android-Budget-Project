package it.chalmers.mufasa.android_budget_app.activities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.controller.ManageCategoryController;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.MainModel;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ManageCategoryActivity extends Activity implements
		PropertyChangeListener {

	private ManageCategoryController controller;
	private ManageCategoryModel model;
	private ArrayAdapter<String> listAdapter;
	private ListView listView;
	private TextView categoryName;
	private List<String> listString;
	
	private EditText editTextCategoryName;
	
	private Button editButton;
	private Button incomeButton;
	private Button expenseButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_category);

		this.model = new ManageCategoryModel();
		this.controller = new ManageCategoryController(
				this.getApplicationContext(), model);

		editButton = (Button) findViewById(R.id.manageCategoryButtonEdit);
		listView = (ListView) findViewById(R.id.manageCategoryListView);
		expenseButton = (Button) findViewById(R.id.manageCategoryButtonExpense);
		incomeButton = (Button) findViewById(R.id.manageCategoryButtonIncome);

		listString = new ArrayList<String>();
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,
				listString);
		updateStringList();
	}

//	public void addCategory(View view) {
//		editTextCategoryName= (EditText) findViewById(R.id.manageCategoryEditText);
//		controller.addCategory(editTextCategoryName, this.getParentId());
//	}
	
//
//	public void removeCategory(View view) {
//		controller.removeCategory(category);
//	}
//
//	public void editCategory(View view) {
//		controller.editCategory(category, newName);
//	}
	
	public void disableIncome(View view){
		expenseButton.setEnabled(true);
		incomeButton.setEnabled(false);
	}
	public void disableExpense(View view){
		incomeButton.setEnabled(true);
		expenseButton.setEnabled(false);
	}
	public int getParentId(){
		if (incomeButton.isEnabled()){
			return 1;
		}
		return 2;
	}



	public void updateStringList() {
		listString.clear();
		for (Category cat : model.getList()) {
			listString.add(cat.getName());
		}
		listView.setAdapter(listAdapter);
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("categoryList")) {
		}
		updateStringList();
	}

}
