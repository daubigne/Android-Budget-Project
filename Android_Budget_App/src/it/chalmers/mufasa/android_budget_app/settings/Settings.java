package it.chalmers.mufasa.android_budget_app.settings;

public class Settings {

	private int currentAccountId;
	
	public Settings(int currentAccountId) {
		this.currentAccountId = currentAccountId;
	}
	
	public int getCurrentAccountId() {
		return this.currentAccountId;
	}
}
