package it.chalmers.mufasa.android_budget_app.activities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.MainController;
import it.chalmers.mufasa.android_budget_app.controller.ManageCategoryController;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
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
	
	private List<String> listString;
	
	private EditText editTextCategoryName;
	
	private String categoryNameString;
	private Button incomeButton;
	private Button expenseButton;
	private Button editSaveButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_category);
		
		this.model = new ManageCategoryModel();
		this.controller = new ManageCategoryController(
				this.getApplicationContext(), model);
		
		expenseButton = (Button) findViewById(R.id.manageCategoryButtonExpense);
		incomeButton = (Button) findViewById(R.id.manageCategoryButtonIncome);
		editSaveButton = (Button) findViewById(R.id.manageCategoryButtonEditSave);

		model.addPropertyChangeListener(this);
		
	    this.populateCategoryListView(model.getCategoryList());
	    }
	    
	    private void populateCategoryListView(List<Category> list) {
	    	LinearLayout categoryListLayout = (LinearLayout) findViewById(R.id.manageCategoryLayout);
	    	categoryListLayout.removeAllViews();
	    	
	    	
	    	if(model.isEditMode()) {
	    		for(Category cat : list) {
		    		View v = this.getLayoutInflater().inflate(R.layout.category_list_row_edit, null);
		    		
		    		EditText categoryEditText = (EditText) v.findViewById(R.id.manageCategoryCategoryEditText);
		            categoryEditText.setText(cat.getName());
		            
		            categoryListLayout.addView(v);
		    	}
	    		
	    		categoryListLayout.addView(this.getLayoutInflater().inflate(R.layout.manage_categories_add_category, null));
	    	} else {
	    		for(Category cat : list) {
	    			View v = this.getLayoutInflater().inflate(R.layout.category_list_row, null);
		    		
		    		TextView categoryTextView = (TextView) v.findViewById(R.id.manageCategoryCategoryTextView);
		    		categoryTextView.setText(cat.getName());
		            categoryListLayout.addView(v);
	    		}
	    	}
	    }



	public void addCategory(View view) {
		editTextCategoryName= (EditText) findViewById(R.id.manageCategoryCategoryEditText);
		categoryNameString = editTextCategoryName.getText().toString();
		controller.addCategory(categoryNameString, model.getCurrentCategory());
	}
	
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
		controller.setCurrentCategory(1);
	}
	public void disableExpense(View view){
		incomeButton.setEnabled(true);
		expenseButton.setEnabled(false);
		controller.setCurrentCategory(2);
	}
	
	public void toggleEditSave(View view){
		if (model.isEditMode()){
			controller.setEditMode(false);
			editSaveButton.setText(R.string.save);
		}else{
			controller.setEditMode(true);
			editSaveButton.setText(R.string.edit);
		}
	}

	public void updateStringList() {
		listString.clear();
		for (Category cat : model.getCategoryList()) {
			listString.add(cat.getName());
		}
		listView.setAdapter(listAdapter);
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("categoryList")) {
		updateStringList();
		}else if(event.getPropertyName().equals("EditSaveMode")){
		populateCategoryListView(model.getCategoryList());	
		}
	}
}
