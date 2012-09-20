package it.chalmers.mufasa.android_budget_app.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{

	public DatabaseOpenHelper(Context context) {
		super(context, "StudentBudget", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE accounts ( id VARCHAR(255), name TEXT, balance FLOAT);");
		db.execSQL("CREATE TABLE transactions ( id VARCHAR(255), name TEXT, value FLOAT, date DATE, account-id VARCHAR(255), category-id VARCHAR(255));");
		db.execSQL("CREATE TABLE categories ( id VARCHAR(255), name TEXT, parent-id VARCHAR(255));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
