package it.chalmers.mufasa.android_budget_app.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GraphViewAdapter extends FragmentPagerAdapter {
	
	private Context context;

	public GraphViewAdapter(Context context, FragmentManager fm) {
		super(fm);
		this.context = context;

	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = new Fragment();
		switch (position) {
		case 0:
			fragment = new AccountBalanceGraphViewFragment();
			break;
		case 1:
			fragment = new AccountBalanceGraphViewFragment();
			break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;
	}

}