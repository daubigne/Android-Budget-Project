 /*
  * Copyright © 2012 Mufasa developer unit
  *
  * This file is part of Mufasa Budget.
  *
  *	Mufasa Budget is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Mufasa Budget is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with Mufasa Budget.  If not, see <http://www.gnu.org/licenses/>.
  */
package it.chalmers.mufasa.android_budget_app.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
	
	/** Database table initialization code */
	public static final String SETTINGS_TABLE = "settings";
	public static final String SETTINGS_TABLE_INIT = "CREATE TABLE settings ( currentAccountId INTEGER);";

	public static final String ACCOUNTS_TABLE = "accounts";
	public static final String ACCOUNTS_TABLE_INIT = "CREATE TABLE accounts ( id INTEGER PRIMARY KEY, name TEXT, balance FLOAT NOT NULL);";
	  
	public static final String TRANSACTIONS_TABLE = "transactions";
	public static final String TRANSACTIONS_TABLE_INIT = "CREATE TABLE transactions ( id INTEGER PRIMARY KEY, name TEXT, value FLOAT NOT NULL, date DATE NOT NULL, accountId INTEGER, categoryId INTEGER);";
	
	public static final String CATEGORIES_TABLE = "categories";
	public static final String CATEGORIES_TABLE_INIT = "CREATE TABLE categories ( id INTEGER PRIMARY KEY, name TEXT, parentId INTEGER);";
	
	public static final String BUDGETITEMS_TABLE = "budgetitems";
	public static final String BUDGETITEMS_TABLE_INIT = "CREATE TABLE budgetitems ( id INTEGER PRIMARY KEY, categoryId INTEGER, value FLOAT NOT NULL);";
	

	public DatabaseOpenHelper(Context context) {
		super(context, "Cashin", null, 1);
	}

	/** 
	 * Initializes default database tables
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SETTINGS_TABLE_INIT);
		db.execSQL(ACCOUNTS_TABLE_INIT);
		db.execSQL(TRANSACTIONS_TABLE_INIT);
		db.execSQL(CATEGORIES_TABLE_INIT);
		db.execSQL(BUDGETITEMS_TABLE_INIT);

		this.insertDefaultValues(db);
	}
	
	/** 
	 * Inserts default account and settings. Also adding a default budget.
	 */
	private void insertDefaultValues(SQLiteDatabase db) {
		//Assumes this is an empty database
		
		db.execSQL("INSERT INTO accounts (id,name,balance) VALUES (1,\"My Account\",0)");
		db.execSQL("INSERT INTO settings (currentAccountId) VALUES (1)");
		
		//Values from average student between 18-30 years in Sweden. Data from konsumentverket.se
		db.execSQL("INSERT INTO categories (name) VALUES (\"Inkomster\")"); //id = 1
		db.execSQL("INSERT INTO categories (name) VALUES (\"Utgifter\")"); //id = 2

		//Income
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Studiemedel\",1)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (3,8920)");
		
		//Expenses
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Mat\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (4,2310)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Hygien\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (5,370)");

		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Kläder\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (6,600)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Fritid\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (7,630)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Mobiltelefon\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (8,180)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Förbrukningsvaror\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (9,100)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Medier\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (10,930)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Hemförsäkring\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (11,100)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Hyra\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (12,3200)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"El\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (13,250)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Kursliteratur\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (14,750)");
		
		db.execSQL("INSERT INTO categories (name,parentId) VALUES (\"Resor\",2)");
		db.execSQL("INSERT INTO budgetitems (categoryId,value) VALUES (15,500)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
