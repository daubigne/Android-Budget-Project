 /*
  * Copyright © 2012 Mufasa developer unit
  *
  * This file is part of Mufasa Budget.
  *
  *	Mufasa Budget is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Mufasa Budget is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with Mufasa Budget.  If not, see <http://www.gnu.org/licenses/>.
  */
package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.BudgetEditController;
import it.chalmers.mufasa.android_budget_app.interfaces.ChooseCategoryInterface;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Activity for managing the users current budget. Includes adding, editing and removing budgetitem rows.
 * 
 * @author simphax <sim.nilsson@gmail.com>
 * 
 */
public class BudgetEditFragment extends Fragment implements PropertyChangeListener, ChooseCategoryInterface {

	private BudgetEditController controller;
	private BudgetEditModel model;
	private ChooseCategoryFragment chooseCategoryFragment;
	private boolean initialized = false;

	private LayoutInflater inflater;
	private View view;
	
	private Button buttonToUpdate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!initialized) {
			this.model = new BudgetEditModel(this.getActivity());
			this.controller = new BudgetEditController(model);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_budget_edit, container, false);

		this.setupOnClickListeners();

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(!initialized) {
			model.addPropertyChangeListener(this);
			controller.switchToIncome();
		} else {
			this.setEditButtonBar();
			this.updateIncomeExpensesButtons();
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if(!initialized) {
			initialized = true;
		} else {
			this.populateBudgetListView(model.getBudgetItems());
		}
	}
	
	private void setupOnClickListeners() {
		Button incomeButton = (Button) view.findViewById(R.id.budgetEditIncomeSwitchButton);
		Button expensesButton = (Button) view.findViewById(R.id.budgetEditExpensesSwitchButton);
		Button enterEditModeButton = (Button) view.findViewById(R.id.budgetItemEditBarEditButton);
		Button exitEditModeButton = (Button) view.findViewById(R.id.budgetItemEditBarEditDoneButton);
		
		incomeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				BudgetEditFragment.this.switchToIncome(v);
			}
		});
		
		expensesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				BudgetEditFragment.this.switchToExpenses(v);
			}
		});
		
		enterEditModeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				BudgetEditFragment.this.enterEditMode(v);
			}
		});
		
		exitEditModeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				BudgetEditFragment.this.exitEditMode(v);
			}
		});
		
	}

	private void populateBudgetListView(List<BudgetItem> list) {
		LinearLayout budgetListLayout = (LinearLayout) view.findViewById(R.id.budgetItemListLayout);
		budgetListLayout.removeAllViews();

		if (model.isEditMode()) {
			for (BudgetItem bi : list) {
				
				View v = inflater.inflate(R.layout.budget_item_edit_row_edit, null);

				v.setTag(bi);

				Button categoryButton = (Button) v.findViewById(R.id.budgetItemEditRowEditCategoryButton);
				categoryButton.setText(bi.getCategory().getName());
				categoryButton.setTag(bi.getCategory()); // Stores the budget item object in the button so it can be accessed in onClick events.
				
				categoryButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						BudgetEditFragment.this.chooseCategory(v);
					}
				});

				EditText valueTextEdit = (EditText) v.findViewById(R.id.budgetItemEditRowEditValueTextEdit);

				valueTextEdit.setText(String.valueOf(bi.getValue()));

				ImageButton removeButton = (ImageButton) v.findViewById(R.id.budgetItemEditRowEditRemoveButton);
				removeButton.setTag(bi);
				
				removeButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						BudgetEditFragment.this.removeItem(v);
					}
				});

				budgetListLayout.addView(v);
			}

			Button addRowButton = (Button) inflater.inflate(R.layout.budget_item_edit_row_addbutton, null);
			
			addRowButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					BudgetEditFragment.this.addBudgetItemRow(v);
				}
			});
			
			budgetListLayout.addView(addRowButton);
			
			
		} else {
			for (BudgetItem bi : list) {
				View v = inflater.inflate(R.layout.budget_item_edit_row, null);

				TextView categoryButton = (TextView) v.findViewById(R.id.budgetItemEditRowCategoryTextView);
				categoryButton.setText(bi.getCategory().getName());

				TextView valueTextEdit = (TextView) v.findViewById(R.id.budgetItemEditRowValueTextView);
				valueTextEdit.setText(String.valueOf(bi.getValue()));

				budgetListLayout.addView(v);
			}
		}
	}

	public void addBudgetItemRow(View view) {
		controller.newBudgetItem();
	}

	public void enterEditMode(View view) {
		controller.setEditMode(true);
	}

	public void exitEditMode(View view) {

		List<BudgetItem> budgetItemList = new ArrayList<BudgetItem>();

		LinearLayout budgetListLayout = (LinearLayout) this.view.findViewById(R.id.budgetItemListLayout);
		for (int i = 0; i < budgetListLayout.getChildCount(); i++) {
			if (budgetListLayout.getChildAt(i) instanceof LinearLayout && budgetListLayout.getChildAt(i).getVisibility() != View.GONE) {
				LinearLayout v = (LinearLayout) budgetListLayout.getChildAt(i);

				if (v.getTag() instanceof BudgetItem) {

					BudgetItem budgetItem = (BudgetItem) v.getTag();

					Button categoryButton = (Button) v.findViewById(R.id.budgetItemEditRowEditCategoryButton);
					EditText valueText = (EditText) v.findViewById(R.id.budgetItemEditRowEditValueTextEdit);

					Category category = (Category)categoryButton.getTag();
					Double value = Double.parseDouble(valueText.getText().toString());

					// rfnoneed
					budgetItemList.add(new BudgetItem(budgetItem.getId(), category, value));
				}
			}
		}

		// rfnoneed
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
		// TODO Choose category via ChooseCategoryActivity
		if (view.getTag() instanceof Category) {
			this.buttonToUpdate = (Button)view;
			Category category = (Category)buttonToUpdate.getTag();
			if(category.getParent() != null) {
				this.chooseCategoryFragment = new ChooseCategoryFragment(this, category.getParent().getId());
			} else {
				this.chooseCategoryFragment = new ChooseCategoryFragment(this, category.getId());
			}
			FragmentManager fm = ((HostActivity)getActivity()).getFragmentManager();

			chooseCategoryFragment.show(fm, "");
		}
	}

	public void removeItem(View view) {

		if (view.getTag() instanceof BudgetItem) {
			BudgetItem bi = (BudgetItem) view.getTag();
			controller.removeBudgetItem(bi);
		}
	}

	public void setEditButtonBar() {
		if (model.isEditMode()) {
			View doneBar = view.findViewById(R.id.budgetItemEditDoneBar);
			doneBar.setVisibility(View.VISIBLE);
			View editBar = view.findViewById(R.id.budgetItemEditBar);
			editBar.setVisibility(View.GONE);
		} else {
			View doneBar = view.findViewById(R.id.budgetItemEditDoneBar);
			doneBar.setVisibility(View.GONE);
			View editBar = view.findViewById(R.id.budgetItemEditBar);
			editBar.setVisibility(View.VISIBLE);
		}
	}

	public void updateIncomeExpensesButtons() {
		if (model.getCurrentMainCategory().getId() == 2) {
			view.findViewById(R.id.budgetEditIncomeSwitchButton).setEnabled(true);
			view.findViewById(R.id.budgetEditExpensesSwitchButton).setEnabled(false);
		} else {
			view.findViewById(R.id.budgetEditIncomeSwitchButton).setEnabled(false);
			view.findViewById(R.id.budgetEditExpensesSwitchButton).setEnabled(true);
		}
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("replaced_budgetitem_list")) {
			this.populateBudgetListView(model.getBudgetItems());
		}

		if (event.getPropertyName().equals("changed_editmode")) {
			this.populateBudgetListView(model.getBudgetItems());
			this.setEditButtonBar();
		}

		if (event.getPropertyName().equals("updated_current_category")) {
			this.updateIncomeExpensesButtons();
			this.populateBudgetListView(model.getBudgetItems());
		}

		if (event.getPropertyName().equals("new_budgetitem")) {
			this.populateBudgetListView(model.getBudgetItems());
		}

		if (event.getPropertyName().equals("removed_budgetitem")) {
			this.populateBudgetListView(model.getBudgetItems());
		}
	}
	
	public void editButtonCategory(Button button, Category newCategory) {
		button.setTag(newCategory);
		button.setText(newCategory.getName());
	}

	public void chooseCategoryCategoryChosen(Category newCategory) {
		this.editButtonCategory(buttonToUpdate, newCategory);
		chooseCategoryFragment.dismiss();
	}
}
