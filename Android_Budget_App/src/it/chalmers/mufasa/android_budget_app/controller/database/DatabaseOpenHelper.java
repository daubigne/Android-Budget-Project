package it.chalmers.mufasa.android_budget_app.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{

	public DatabaseOpenHelper(Context context) {
		super(context, "StudentBudget", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE accounts ( id INTEGER PRIMARY KEY, name TEXT, balance FLOAT NOT NULL);");
		db.execSQL("CREATE TABLE transactions ( id INTEGER PRIMARY KEY, name TEXT, value FLOAT NOT NULL, date DATE NOT NULL, accountId INTEGER, categoryId INTEGER);");
		db.execSQL("CREATE TABLE categories ( id INTEGER PRIMARY KEY, name TEXT, parentId INTEGER);");
		db.execSQL("CREATE TABLE budgetitems ( id INTEGER PRIMARY KEY, categoryId INTEGER, value FLOAT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
