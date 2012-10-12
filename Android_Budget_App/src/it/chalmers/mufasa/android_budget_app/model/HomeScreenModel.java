package it.chalmers.mufasa.android_budget_app.model;

import android.content.Context;

public class HomeScreenModel { 
	private Account account;	
	private double balance;
	private double percentage;
	
	public HomeScreenModel(Context context) {
		this.account = Account.getInstance(context);
		this.balance = this.account.getBalance();
		this.percentage = 0.0;
	}
	public double getBalance(){
		return this.balance;
	}
	public Account getAccount(){
		Account temp = this.account;
		return temp;
	}
	public double getPercentage(){
		return this.percentage;
	}
	public void calculatePercentage() {
		this.percentage = (this.account.getTransactionsSum(2) * 1.0 
				/ this.account.getBudgetItemsSum(2) * 1.0) *100.0;
	}
	
}
