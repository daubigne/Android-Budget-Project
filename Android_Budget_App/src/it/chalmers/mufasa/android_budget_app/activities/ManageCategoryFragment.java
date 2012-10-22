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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.ManageCategoryController;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.ManageCategoryModel;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * version 1
 * 
 * @author FannyM Activity Class to manage categories depending on the user
 *         input.
 */

public class ManageCategoryFragment extends Fragment implements
		PropertyChangeListener {

	private ManageCategoryController controller;
	private ManageCategoryModel model;
	private LayoutInflater inflater;
	private View view;
	private View addView;
	private Button incomeButton;
	private Button expenseButton;
	private Button editSaveButton;
	private Button addButton;
	private Button backToOptionsButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.view = inflater.inflate(R.layout.fragment_manage_category,
				container, false);
		this.addView = inflater.inflate(
				R.layout.manage_categories_add_category, container, false);
		this.model = new ManageCategoryModel(this.getActivity());
		this.controller = new ManageCategoryController(this.model);
		model.addPropertyChangeListener(this);

		this.populateCategoryListView(model.getCategoryList());

		this.setupOnClickListeners();

		return view;
	}

	private void setupOnClickListeners() {
		incomeButton = (Button) view
				.findViewById(R.id.manageCategoryButtonIncome);
		expenseButton = (Button) view
				.findViewById(R.id.manageCategoryButtonExpense);
		editSaveButton = (Button) view
				.findViewById(R.id.manageCategoryButtonEditSave);
		addButton = (Button) addView.findViewById(R.id.manageCategoryButtonAdd);
		backToOptionsButton= (Button) view.findViewById(R.id.backToOptions);

		incomeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ManageCategoryFragment.this.toggleIncomeExpense();
			}
		});

		expenseButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ManageCategoryFragment.this.toggleIncomeExpense();
			}
		});

		editSaveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ManageCategoryFragment.this.toggleEditSave();
			}
		});

		addButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText editTextname = (EditText) addView
						.findViewById(R.id.manageCategoryEditTextAdd);
				String nameString = editTextname.getText().toString();
				ManageCategoryFragment.this.addCategory(nameString);
				editTextname.setText("");
			}
		});
		this.backToOptionsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((HostActivity)getActivity()).changeFragment(new OptionsFragment());
			}
		});
	}

	/**
	 * a method to add categories to the list view in different ways depending
	 * on save or edit mode.
	 * 
	 * @param list
	 */
	private void populateCategoryListView(List<Category> list) {
		LinearLayout categoryListLayout = (LinearLayout) view
				.findViewById(R.id.manageCategoryListLayout);
		categoryListLayout.removeAllViews();

		if (model.isEditMode()) {
			for (Category cat : list) {
				View v = this.inflater.inflate(R.layout.category_list_row_edit,
						null);

				EditText categoryEditText = (EditText) v
						.findViewById(R.id.manageCategoryCategoryEditText);
				categoryEditText.setText(cat.getName());
				
				Button removeButton = (Button) v
						.findViewById(R.id.manageActivityButtonRemove);
				removeButton.setTag(cat);
				v.setTag(cat);
				removeButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						if (v.getTag() instanceof Category) {
							Category category = (Category) v.getTag();
							ManageCategoryFragment.this
									.removeCategory(category);
						}
					}
				});

				categoryListLayout.addView(v);
			}

			categoryListLayout.addView(addView);
		} else {
			for (Category cat : list) {
				View v = inflater.inflate(R.layout.category_list_row, null);

				TextView categoryTextView = (TextView) v
						.findViewById(R.id.manageCategoryCategoryTextView);
				categoryTextView.setText(cat.getName());
				categoryListLayout.addView(v);
			}
		}
	}

	/**
	 * a method to add a category
	 * 
	 * @param view
	 */

	public void addCategory(String name) {
		controller.addCategory(name, model.getCurrentParentCategory());
	}

	/**
	 * a method to remove a category
	 * 
	 * @param category
	 */
	public void removeCategory(Category category) {
		controller.removeCategory(category);
	}
	
	/**
	 * a method to edit a category
	 * 
	 * @param category
	 */
	public void editCategory(Category category, String newName) {
		controller.editCategory(category, newName);
	}

	/**
	 * a method to switch between income and expenses in the shown list
	 */
	public void toggleIncomeExpense() {
		if (model.getCurrentParentCategory().getId() == 1) {
			expenseButton.setEnabled(false);
			incomeButton.setEnabled(true);
			controller.setCurrentParentCategory(2);
		} else {
			incomeButton.setEnabled(false);
			expenseButton.setEnabled(true);
			controller.setCurrentParentCategory(1);
		}
	}

	/**
	 * a method to switch between save and edit mode
	 */
	public void toggleEditSave() {
		if (model.isEditMode()) {

			
			LinearLayout editCategoryLayout = (LinearLayout) this.view
					.findViewById(R.id.manageCategoryListLayout);
			
			for (int i = 0; i < editCategoryLayout.getChildCount(); i++) {
				
				
				if (editCategoryLayout.getChildAt(i) instanceof LinearLayout) {
					
					LinearLayout editCategoryRowLayout = (LinearLayout) editCategoryLayout.getChildAt(i);
					
					EditText editText = (EditText)editCategoryRowLayout.findViewById(R.id.manageCategoryCategoryEditText);
					
					if (editCategoryRowLayout.getTag() instanceof Category) {
					Category category = (Category) editCategoryRowLayout.getTag();
					this.removeCategory(category);
					this.addCategory(editText.getText().toString());
					}
					
					
				}
			}
			editSaveButton.setText(R.string.edit);
			controller.setEditMode(false);
		} else {
			controller.setEditMode(true);
			editSaveButton.setText(R.string.save);
		}
	}

	/**
	 * a method that listens for property changes from the model.
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("added_category")) {
			populateCategoryListView(model.getCategoryList());
		} else if (event.getPropertyName().equals("removed_category")) {
			populateCategoryListView(model.getCategoryList());
		} else if (event.getPropertyName().equals("edited_category")) {
			populateCategoryListView(model.getCategoryList());
		} else if (event.getPropertyName().equals("changed_parent_category")) {
			populateCategoryListView(model.getCategoryList());
		} else if (event.getPropertyName().equals("changed_editmode")) {
			populateCategoryListView(model.getCategoryList());
		}
	}
}
