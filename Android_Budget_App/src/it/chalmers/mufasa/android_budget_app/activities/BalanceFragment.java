package it.chalmers.mufasa.android_budget_app.activities;

import it.chalmers.mufasa.android_budget_app.R;
import it.chalmers.mufasa.android_budget_app.controller.BalanceController;
import it.chalmers.mufasa.android_budget_app.model.BalanceModel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BalanceFragment extends Fragment{
	private EditText balanceInput;
	private Button saveBalanceButton;
	private LayoutInflater inflater;
	private BalanceModel model;
	private BalanceController controller;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.model = new BalanceModel(this.getActivity());
		this.controller = new BalanceController(this.model);
		this.view = inflater.inflate(R.layout.fragment_balance,
				container, false);
		this.balanceInput = (EditText) view.findViewById(R.id.balanceText);
		setupOnClickListeners();

		return view;
	}
	private void setupOnClickListeners() {
		saveBalanceButton = (Button) view
				.findViewById(R.id.saveBalance);
		saveBalanceButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.saveBalance(Double.valueOf((balanceInput.getText().toString())));
				((HostActivity)getActivity()).changeFragment(new OptionsFragment());
			}
		});
	}

}
