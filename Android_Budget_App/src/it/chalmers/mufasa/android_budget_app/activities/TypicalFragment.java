package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 
 * @author daubigne
 * 
 * A class made to work as a example fragment, temporary.
 *
 */
public class TypicalFragment extends Fragment{
	
	String str;
	
	public TypicalFragment(String str) {
		this.str = str;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.typical_fragment, container, false);
		
		TextView tv = (TextView)v.findViewById(R.id.textView1);
		tv.setText(str);
		
		return v;
	}
	
	//can implement onCreate() etc in the fragment.
}
