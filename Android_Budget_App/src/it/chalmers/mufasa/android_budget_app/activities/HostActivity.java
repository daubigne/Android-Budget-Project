/*
 * Copyright � 2012 Mufasa developer unit
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
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * @Author : daubigne
 * 
 *         A hostclass for all fragments, works as the frame of the application
 * 
 */
public class HostActivity extends Activity {

	public static final int MY_DIALOG_ID = 2;
	private ActionBar theBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_host);

		// create an action bar for navigation
		theBar = getActionBar();
		theBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		theBar.setDisplayShowHomeEnabled(false);
		theBar.setDisplayShowTitleEnabled(false);
		

		Tab tab1 = theBar
				.newTab()
				.setIcon(R.drawable.ic_tab_home)
				.setTabListener(
						new HostTabListener(new HomescreenFragment()));
		Tab tab2 = theBar
				.newTab()
				.setIcon(R.drawable.ic_tab_transactions)
				.setTabListener(
						new HostTabListener(new TransactionListFragment()));
		Tab tab3 = theBar
				.newTab()
				.setIcon(R.drawable.ic_tab_budget)
				.setTabListener(
						new HostTabListener(new BudgetEditFragment()));
		Tab tab4 = theBar
				.newTab()
				.setIcon(R.drawable.ic_tab_graphs)
				.setTabListener(new HostTabListener(new GraphViewFragment()));
		Tab tab5 = theBar
				.newTab()
				.setIcon(R.drawable.ic_tab_options)
				.setTabListener(
						new HostTabListener(new OptionsFragment()));

		// add the tabs to the action bar
		theBar.addTab(tab1);
		theBar.addTab(tab2);
		theBar.addTab(tab3);
		theBar.addTab(tab4);
		theBar.addTab(tab5);


	}
	
	/**
	 * A class used to respond to actions performed on the tab.
	 */
	private class HostTabListener implements ActionBar.TabListener {
		private Fragment fragment;

		public HostTabListener(Fragment fragment) {
			this.fragment = fragment;
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			//Do nothing.
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}

	}

	/**
	 * Changes current shown fragment.
	 */
	public void changeFragment(Fragment fragment){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fragment_container, fragment);
		transaction.commit();
	}

	// Code extracted from
	// http://mobile.tutsplus.com/tutorials/android/android-sdk_datepickerdialog/
	@Override
	protected Dialog onCreateDialog(int id) {
		DatePickerDialog dateDlg = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						Time chosenDate = new Time();
						chosenDate.set(dayOfMonth, monthOfYear, year);
						long dtDob = chosenDate.toMillis(true);
						CharSequence strDate = DateFormat.format(
								"MMMM dd, yyyy", dtDob);
						Toast.makeText(HostActivity.this,
								"Date picked: " + strDate, Toast.LENGTH_SHORT)
								.show();
					}
				}, 2011, 0, 1);
		dateDlg.setMessage("Transaction date");
		return dateDlg;
	}

}
