package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.interfaces.ChooseCategoryInterface;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ChooseCategoryFragment extends ListFragment {

	private Map<String, Integer> map = new HashMap<String, Integer>();
	private View view;
	private Account account;
	private List<String> list;
	private ListAdapter listAdapter;
	private ChooseCategoryInterface chooseCategoryListener;
	private int parentCategoryId;
	private Category chosenCategory;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState, ChooseCategoryInterface cci, int id) {

		this.view = inflater.inflate(R.layout.fragment_choose_category,
				container, false);

		parentCategoryId = id;
		chooseCategoryListener = cci;

		account = Account.getInstance(this.getActivity());
		list = this.getCategoryListToString(parentCategoryId);
		listAdapter = new ArrayAdapter<String>(this.getActivity(),
				R.layout.simplerow, list);
		setListAdapter(listAdapter);
		return view;
	}

	@Override
	public void onListItemClick(ListView lv, View v, int pos, long id) {
		String item = (String) getListAdapter().getItem(pos);
		chosenCategory = account.getCategory(map.get(item));
		chooseCategoryListener.chooseCategoryCategoryChosen(chosenCategory);
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
}
