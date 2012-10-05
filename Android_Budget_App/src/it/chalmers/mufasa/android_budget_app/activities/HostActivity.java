package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

/**
 * @Author : daubigne
 * 
 *         A hostclass for all fragments, works as the frame of the application
 * 
 */
public class HostActivity extends Activity {
	
	private ActionBar theBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host);
		
		// create an action bar for navigation
		theBar = getActionBar();
		theBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		theBar.setDisplayShowTitleEnabled(false);

		// Create tabs for the action bar
		// TODO : Replace the empty constructor with fragments that are going to
		// fill the app,
		// upon completion;remove the empty constructor
		Tab tab1 = theBar.newTab().setText("Home").setTabListener(new HostTabListener(new TypicalFragment("First tab")));
		Tab tab2 = theBar.newTab().setText("Transactions").setTabListener(new HostTabListener(new TypicalFragment("Second tab")));
		Tab tab3 = theBar.newTab().setText("Categories").setTabListener(new HostTabListener(new TypicalFragment("Thrid tab")));
		Tab tab4 = theBar.newTab().setText("Budget").setTabListener(new HostTabListener(new TypicalFragment("Fourth tab")));
		Tab tab5 = theBar.newTab().setText("Options").setTabListener(new HostTabListener(new TypicalFragment("Fifth tab")));

		// add the tabs to the action bar
		theBar.addTab(tab1);
		theBar.addTab(tab2);
		theBar.addTab(tab3);
		theBar.addTab(tab4);
		theBar.addTab(tab5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_home_screen_host, menu);
		return true;
	}

	private class HostTabListener implements ActionBar.TabListener {
		public Fragment fragment;

		public HostTabListener(Fragment fragment) {
			this.fragment = fragment;
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//	Toast.makeText(HomeScreenHostActivity.appContext, "Reselected!",
		//			Toast.LENGTH_LONG).show();
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}

	}
}
