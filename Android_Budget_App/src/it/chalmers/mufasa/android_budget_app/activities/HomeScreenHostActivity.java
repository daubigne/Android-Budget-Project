package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.R.layout;
import it.chalmers.mufasa.android_budget_app.R.menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.Menu;


/**
 * @Author : daubigne
 * 
 * A hostclass for all fragments, works as the frame of the application
 * 
 */
public class HomeScreenHostActivity extends Activity implements android.app.ActionBar.TabListener {
	private ActionBar theBar;
	private Fragment frag;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_host); //TODO: check if this is nessessary
        
        //create an action bar for navigation
        theBar = getActionBar();
        theBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        theBar.setDisplayShowTitleEnabled(false);
        
       
        //Create tabs for the action bar 
        //TODO : Replace the empty constructor with fragments that are going to fill the app, 
        // upon completion;remove the empty constructor
        Tab tab1 = theBar.newTab().setText("Home").setTabListener(new HomeScreenHostActivity());
        Tab tab2 = theBar.newTab().setText("Transactions").setTabListener(new HomeScreenHostActivity());
        Tab tab3 = theBar.newTab().setText("Categories").setTabListener(new HomeScreenHostActivity());
        Tab tab4 = theBar.newTab().setText("Budget").setTabListener(new HomeScreenHostActivity());
        Tab tab5 = theBar.newTab().setText("Options").setTabListener(new HomeScreenHostActivity());
        
        //add the tabs to the action bar
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
    
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//If the tab is reselected, do nothing.
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//when the tab is selected, attach it to the activity.
		ft.attach(frag);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//when another tab is selected, detach the fragment from the view.
		ft.detach(frag);
	}
		
		public HomeScreenHostActivity(){
			//TODO: Fix this xD
			frag = (Fragment) new TypicalFragment();
		}
		public HomeScreenHostActivity(Fragment frag){
			frag = frag;
		}
		public Fragment getFragment(){
			Fragment temp = this.frag;
			return temp;
		}
}
