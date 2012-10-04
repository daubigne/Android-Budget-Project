package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.BudgetEditController;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BudgetEditActivity extends Activity implements PropertyChangeListener {

	private BudgetEditController controller;
	private BudgetEditModel model;
	
	private ListView listView;
	private EditText categoryIdEditText;
	private EditText valueEditText;
	
	private List<BudgetItem> budgetItemList;
	private ArrayAdapter<BudgetItem> listAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_edit);
        
        this.model = new BudgetEditModel();
        this.controller = new BudgetEditController(this.getApplicationContext(), model);
        
        model.addPropertyChangeListener(this);
        
        //Category cat = new Category("Mat",1,null);
        
        //categoryIdEditText = (EditText) findViewById(R.id.categoryId);
        //valueEditText = (EditText) findViewById(R.id.value);
        
        //listAdapter = new ArrayAdapter<BudgetItem>(this, R.layout.simplerow, model.getBudgetItems());
        
        //listView = (ListView) findViewById(R.id.budgetItemList);
        //listView.setAdapter(listAdapter);
        
        this.populateBudgetListView(model.getBudgetItems());
    }
    
    private void populateBudgetListView(List<BudgetItem> list) {
    	LinearLayout budgetListLayout = (LinearLayout) findViewById(R.id.budgetItemListLayout);
    	budgetListLayout.removeAllViews();
    	
    	
    	if(model.isEditMode()) {
    		for(BudgetItem bi : list) {
	    		View v = this.getLayoutInflater().inflate(R.layout.budget_item_edit_row_edit, null);
	    		
	    		v.setTag(bi);
	    		
	    		Button categoryButton = (Button) v.findViewById(R.id.budgetItemEditRowEditCategoryButton);
	            categoryButton.setText(bi.getCategory().getName());
	            categoryButton.setTag(bi.getCategory()); //Stores the budgetitem object in the button so it can be accessed in onClick events.
	            
	            EditText valueTextEdit = (EditText) v.findViewById(R.id.budgetItemEditRowEditValueTextEdit);
	            valueTextEdit.setText(bi.getValue().toString());
	            
	            Button removeButton = (Button) v.findViewById(R.id.budgetItemEditRowEditRemoveButton);
	            removeButton.setTag(v);
	            
	            budgetListLayout.addView(v);
	    	}
    		
    		budgetListLayout.addView(this.getLayoutInflater().inflate(R.layout.budget_item_edit_row_addbutton, null));
    	} else {
    		for(BudgetItem bi : list) {
    			View v = this.getLayoutInflater().inflate(R.layout.budget_item_edit_row, null);
	    		
	    		TextView categoryButton = (TextView) v.findViewById(R.id.budgetItemEditRowCategoryTextView);
	            categoryButton.setText(bi.getCategory().getName());
	            
	            TextView valueTextEdit = (TextView) v.findViewById(R.id.budgetItemEditRowValueTextView);
	            valueTextEdit.setText(bi.getValue().toString());
	            
	            budgetListLayout.addView(v);
    		}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_budget_edit, menu);
        return true;
    }
    
    public void addBudgetItemRow(View view) {
    	controller.addBudgetItem(model.getCurrentMainCategory(), 0.0);
    }
    /*
    public void addBudgetItem(View view) {
    	//int categoryId = Integer.parseInt(this.categoryIdEditText.getText().toString());
    	double value = Double.parseDouble(this.valueEditText.getText().toString());
    	Category cat;
    	
    	if(value > 0) {
    		cat = new Category("Inkomster",1,null); //TODO get category from ChooseCategoryActivity
    	} else if(value < 0) {
    		cat = new Category("Utgifter",2,null); 
    	} else {
    		return;//TODO Alert user value can not be zero?
    	}
    	
    	controller.addBudgetItem(cat, value);
    }
    */
    
    public void enterEditMode(View view) {
    	controller.setEditMode(true);
    }
    
    public void exitEditMode(View view) {
    	
    	List<BudgetItem> budgetItemList = new ArrayList<BudgetItem>();
    	
    	LinearLayout budgetListLayout = (LinearLayout) findViewById(R.id.budgetItemListLayout);
    	for(int i=0; i<budgetListLayout.getChildCount(); i++) {
    		if(budgetListLayout.getChildAt(i) instanceof LinearLayout && budgetListLayout.getChildAt(i).getVisibility() != View.GONE) {
	    		LinearLayout v = (LinearLayout)budgetListLayout.getChildAt(i);
	    		
	    		if(v.getTag() instanceof BudgetItem) {
	    			
	    			BudgetItem budgetItem = (BudgetItem) v.getTag();
	    			
		    		Button categoryButton = (Button)v.findViewById(R.id.budgetItemEditRowEditCategoryButton);
		    		EditText valueText = (EditText)v.findViewById(R.id.budgetItemEditRowEditValueTextEdit);
	    			
		    		Category category = (Category)categoryButton.getTag();
		    		Double value = Double.parseDouble(valueText.getText().toString());
		    		
	    			//budgetItem.setCategory(category);
	    			//budgetItem.setValue(value);
	    			
	    			//rfnoneed
	    			budgetItemList.add(new BudgetItem(budgetItem.getId(),category,value));
	    		}
    		}
    	}
    	
    	//rfnoneed
    	controller.saveAllBudgetItems(budgetItemList);
    	
    	controller.setEditMode(false);
    }
    
    public void switchToIncome(View view) {
    	controller.switchToIncome();
    }
    
    public void switchToExpenses(View view) {
    	controller.switchToExpenses();
    }
    
    public void chooseCategory(View view) {
    	
    	//TODO Choose category via ChooseCategoryActivity
    	if(view.getTag() instanceof Category) {
    		Category category = (Category) view.getTag();
    		System.out.println("Choose category... current category:" + category);
    	}
    }
    
    public void removeItem(View view) {
    	
    	if(view.getTag() instanceof LinearLayout) {
    		LinearLayout v = (LinearLayout) view.getTag();
    		v.setVisibility(View.GONE);
    	}
    }
    
    public void setEditButtonBar() {
    	if(model.isEditMode()) {
    		View doneBar = this.findViewById(R.id.budgetItemEditDoneBar);
    		doneBar.setVisibility(View.VISIBLE);
    		View editBar = this.findViewById(R.id.budgetItemEditBar);
    		editBar.setVisibility(View.GONE);
    	} else {
    		View doneBar = this.findViewById(R.id.budgetItemEditDoneBar);
    		doneBar.setVisibility(View.GONE);
    		View editBar = this.findViewById(R.id.budgetItemEditBar);
    		editBar.setVisibility(View.VISIBLE);
    	}
    }
    
    public void updateIncomeExpensesButtons() {
    	if(model.getCurrentMainCategory().getId() == 2) {
    		findViewById(R.id.budgetEditIncomeSwitchButton).setEnabled(true);
        	findViewById(R.id.budgetEditExpensesSwitchButton).setEnabled(false);
    	} else {
    		findViewById(R.id.budgetEditIncomeSwitchButton).setEnabled(false);
        	findViewById(R.id.budgetEditExpensesSwitchButton).setEnabled(true);
    	}
    }

	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equals("updated_budgetitem_list")) {
			this.populateBudgetListView(model.getBudgetItems());
		}
		
		if(event.getPropertyName().equals("editmode")) {
			this.populateBudgetListView(model.getBudgetItems());
			this.setEditButtonBar();
		}
		
		if(event.getPropertyName().equals("updated_current_category")) {
			this.updateIncomeExpensesButtons();
		}
	}
}
