package it.chalmers.mufasa.android_budget_app.test.activities;

import it.chalmers.mufasa.android_budget_app.activities.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;

	public MainActivityTest() {
		super("it.chalmers.mufasa.android_budget_app.activities", MainActivity.class);
	}
		
	@Override    
	protected void setUp() throws Exception {        
		super.setUp();        
		activity = this.getActivity();  
	}
	
	public void testDummy() {
		assertTrue(true);
	}

}
