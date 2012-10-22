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
import it.chalmers.mufasa.android_budget_app.interfaces.ChooseCategoryInterface;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ChooseCategoryFragment extends DialogFragment implements
		DialogInterface.OnClickListener {

	private Map<String, Integer> map = new HashMap<String, Integer>();
	private View view;
	private Account account;
	private List<String> list;
	private ListAdapter listAdapter;
	private ChooseCategoryInterface chooseCategoryListener;
	private int parentCategoryId;
	private Category chosenCategory;

	public ChooseCategoryFragment(ChooseCategoryInterface cci, int id) {
		parentCategoryId = id;
		chooseCategoryListener = cci;

		list = new ArrayList<String>();
		this.account = Account.getInstance(getActivity());
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Choose Category");
		builder.setNegativeButton("Cancel", this);
		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.view = inflater.inflate(R.layout.fragment_choose_category,
				null);
		builder.setView(this.view);
		
		list = this.getCategoryListToString(parentCategoryId);
		listAdapter = new ArrayAdapter<String>(this.getActivity(),
				R.layout.simplerow, list);

		builder.setAdapter(listAdapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				String item = (String) listAdapter.getItem(which);
				chosenCategory = account.getCategory(map.get(item));
				chooseCategoryListener
						.chooseCategoryCategoryChosen(chosenCategory);
			}
		});

		return builder.create();

	}

	private List<String> getCategoryListToString(int id) {

		List<Category> listCategory = account.getCategories(account
				.getCategory(id));
		for (Category cat : listCategory) {
			map.put(cat.getName(), cat.getId());
			list.add(cat.getName());
		}

		return list;
	}

	public void onClick(DialogInterface dialog, int pos) {
		
	}
}
