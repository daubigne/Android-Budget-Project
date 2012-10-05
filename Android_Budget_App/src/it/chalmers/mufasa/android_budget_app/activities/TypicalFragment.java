package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 
 * @author daubigne
 * 
 * A class made to work as a example fragment, temporary.
 *
 */
public class TypicalFragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.typical_fragment, container, false);
	}
	
	//can implement onCreate() etc in the fragment.
}
