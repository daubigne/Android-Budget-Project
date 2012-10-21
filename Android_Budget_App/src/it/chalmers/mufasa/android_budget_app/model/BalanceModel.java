package it.chalmers.mufasa.android_budget_app.model;

import android.content.Context;

public class BalanceModel {
	private Account account;
	
	
	public void saveBalance(double balance){
		this.account.setBalance(balance);
	}
	public BalanceModel(Context context){
		this.account = Account.getInstance(context);
	}

}
