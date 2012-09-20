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
		db.execSQL("CREATE TABLE accounts ( id INT PRIMARY KEY, name TEXT, balance FLOAT NOT NULL);");
		db.execSQL("CREATE TABLE transactions ( id INT PRIMARY KEY, name TEXT, value FLOAT NOT NULL, date DATE NOT NULL, account-id INT, category-id INT);");
		db.execSQL("CREATE TABLE categories ( id INT PRIMARY KEY, name TEXT, parent-id INT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
