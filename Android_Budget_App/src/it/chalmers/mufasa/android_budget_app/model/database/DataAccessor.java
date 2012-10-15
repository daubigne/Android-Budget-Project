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

import it.chalmers.mufasa.android_budget_app.model.database.DatabaseOpenHelper;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import it.chalmers.mufasa.android_budget_app.settings.Settings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Data Accessor Object to fetch and save data from database. This is the only
 * place where classes Account,BudgetItem,Category,Transaction should be fetched
 * and saved from.
 */
public class DataAccessor {
	private Context context;

	public DataAccessor(Context context) {
		this.context = context;
		// this.addAccount(account.getName(), account.getBalance());
	}

	public enum SortBy {
		NAME, DATE, CATEGORY
	}

	public enum SortByOrder {
		DESC, ASC
	}

	/*
	 * public List<Account> getAccounts() {
	 * 
	 * List<Account> accountList = new ArrayList<Account>();
	 * 
	 * SQLiteDatabase db = new
	 * DatabaseOpenHelper(context).getWritableDatabase(); String[] arr = { "id",
	 * "name", "balance"}; Cursor cursor = db.query("accounts", arr, null, null,
	 * null, null, null);
	 * 
	 * if (cursor.moveToFirst()) { accountList.add(new
	 * Account(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
	 * 
	 * while(cursor.moveToNext()) { accountList.add(new
	 * Account(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2))); } }
	 * 
	 * return accountList;
	 * 
	 * }
	 */

	/*
	 * public Account getAccount(int accountID) { SQLiteDatabase db = new
	 * DatabaseOpenHelper(context) .getWritableDatabase(); String[] arr = {
	 * "id", "name", "balance" }; Cursor cursor = db.query("accounts", arr,
	 * "id == " + accountID, null, null, null, null);
	 * 
	 * if (cursor.moveToFirst()) { return new Account(cursor.getInt(0),
	 * cursor.getString(1), cursor.getDouble(2)); } else { throw new
	 * IllegalArgumentException("Account ID " + accountID + " does not exist");
	 * } }
	 */

	/**
	 * True if an Account exists in the database.
	 */
	public boolean accountExists() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
				Cursor cursor = db.query("accounts", arr, "id == " + Constants.ACCOUNT_ID, null, null,
				null, null);

		return cursor.moveToFirst();

	}

	/**
	 * Adds an Account to the database.
	 */
	public void addAccount(String name, double balance) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		db.execSQL("INSERT INTO accounts (name, balance) VALUES (\"" + name
				+ "\"," + balance + ")");
	}

	/**
	 * Sets the balance of an Account with the given ID:
	 */
	public void setAccountBalance(double balance, int id) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("UPDATE accounts SET balance=" + balance + " WHERE id == "
				+ id);

	}

	/**
	 * Updates the Account in the database.
	 */
	public void updateAccount(Account account) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("balance", Double.toString(account.getBalance()));
		values.put("name", account.getName());

		db.update("accounts", values, "id == " + account.getId(), null);

		

	}

	/**
	 * Returns the balance of the Account with the given ID.
	 */
	public Double getAccountBalance() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
		Cursor cursor = db.query("accounts", arr, "id == "
				+ Constants.ACCOUNT_ID, null, null, null, null);

		if (cursor.moveToFirst()) {
			Double balance = cursor.getDouble(2);
			return balance;
		} else {
			return null;
		}

	}

	/**
	 * Returns the name of the Account with the given ID.
	 */
	public String getAccountName(int id) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };

		Cursor cursor = db.query("accounts", arr, "id == "
				+ id, null, null, null, null);

		if (cursor.moveToFirst()) {
			String name = cursor.getString(1);
			
			
			return name;
		} else {
			
			
			throw new IllegalArgumentException("Could not access account name");
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getAccountId() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
		Cursor cursor = db.query("accounts", arr, "id == "
				+ Constants.ACCOUNT_ID, null, null, null, null);

		if (cursor.moveToFirst()) {
			int id = cursor.getInt(0);
			
			
			return id;
		} else {
			
			
			throw new IllegalArgumentException("Could not access account ID");
		}
	}

	/**
	 * Adds a transactions to the database.
	 */
	public void addTransaction(Double amount, Date date, String name,
			Category category) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("INSERT INTO transactions (accountId, name, date, value, categoryId ) VALUES ( "
				+ Constants.ACCOUNT_ID
				+ ", "
				+ "\""
				+ name
				+ "\""
				+ ", "
				+ "\""
				+ date.getYear()
				+ "-"
				+ date.getMonth()
				+ "-"
				+ date.getDay()
				+ "\""
				+ ", "
				+ amount
				+ ", "
				+ category.getId() + ")");
		
		if(category.getParent() != null) {
			if(category.getParent().getId() == Constants.INCOME_ID ){
				this.setAccountBalance(getAccountBalance() + amount,
						Constants.ACCOUNT_ID);
			} else if (category.getParent().getId() == Constants.EXPENSE_ID) {
				this.setAccountBalance(getAccountBalance() - amount,
						Constants.ACCOUNT_ID);
			}
		} else if(category.getId() == Constants.INCOME_ID){
			this.setAccountBalance(getAccountBalance() + amount,
					Constants.ACCOUNT_ID);
		} else if(category.getId() == Constants.EXPENSE_ID){
			this.setAccountBalance(getAccountBalance() - amount,
					Constants.ACCOUNT_ID);
		} else {
			throw new IllegalArgumentException("This category or parent category id must be "
					+ "Constant.EXPENSE_ID or Constant.ACCOUNT_ID");
		}

	}

	/**
	 * Removes given transaction from the database.
	 */
	public void removeTransaction(Transaction transaction) {
		Category category = transaction.getCategory();
		double amount = transaction.getAmount();

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("DELETE FROM transactions WHERE id ==" + transaction.getId());
		
//		this.setAccountBalance(getAccountBalance() - amount,
//				Constants.ACCOUNT_ID);
		if(category == null){
			System.out.println("category Šr null");
		}
		
		if(category.getParent() != null) {
			if(category.getParent().getId() == Constants.INCOME_ID ){
				this.setAccountBalance(getAccountBalance() + amount,
						Constants.ACCOUNT_ID);
			} else if (category.getParent().getId() == Constants.EXPENSE_ID) {
				this.setAccountBalance(getAccountBalance() - amount,
						Constants.ACCOUNT_ID);
			}
		} else if(category.getId() == Constants.INCOME_ID){
			this.setAccountBalance(getAccountBalance() + amount,
					Constants.ACCOUNT_ID);
		} else if(category.getId() == Constants.EXPENSE_ID){
			this.setAccountBalance(getAccountBalance() - amount,
					Constants.ACCOUNT_ID);
		} else {
			throw new IllegalArgumentException("This category or parent category id must be "
					+ "Constant.EXPENSE_ID or Constant.ACCOUNT_ID");
		}
		
	}

	/**
	 * Returns transactions in the database.
	 * 
	 * @param sortBy The attribute of which the transactions will be sorted by.
	 * @param sortByOrder Can either be ascending or descending.
	 * @param start The first transaction to be returned.
	 * @param count The number of transactions to be returned.
	 */
	public List<Transaction> getTransactions(SortBy sortBy,
			SortByOrder sortByOrder, int start, int count) {

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		List<Transaction> transactions = new ArrayList<Transaction>();
		String sortByTemp = "date";
		String sortByOrderTemp = "desc";

		switch (sortBy) {
		case NAME:
			sortByTemp = "name";
			break;
		case DATE:
			sortByTemp = "date";
			break;
		case CATEGORY:
			sortByTemp = "category";
			break;
		}
		switch (sortByOrder) {
		case ASC:
			sortByOrderTemp = "ASC";
			break;
		case DESC:
			sortByOrderTemp = "DESC";
			break;
		}

		String[] arr = { "name", "date", "id", "value", "categoryId" };

		Cursor cursor = db.query("transactions", arr, "accountId == "
				+ Constants.ACCOUNT_ID, null, null, null, sortByTemp + " "
				+ sortByOrderTemp);

		if (cursor.moveToPosition(start)) {
			for (int i = start; i < Math.min(start + count, cursor.getCount()); i++) {
				String dateString = cursor.getString(1);
				String[] list = dateString.split("-");
				// Date date = new Date(Integer.parseInt(list[0]),
				// Integer.parseInt(list[1]), Integer.parseInt(list[2]));
				
				//TODO DATE!?!?!?!?
				Date date = new Date(10000);

				Category category = getCategory(cursor.getInt(4));
				
				Transaction transaction = new Transaction(cursor.getInt(2),
						(cursor.getDouble(3)), date, cursor.getString(0),
						category);
				transactions.add(transaction);

				cursor.moveToNext();
			}
		}
		
		
		return transactions;
	}

	/**
	 * Returns transactions of a certain category.
	 * @param sortBy The attribute of which the transactions will be sorted by.
	 * @param sortByOrder Can either be ascending or descending.
	 * @param start The first transaction to be returned.
	 * @param count The number of transactions to be returned.
	 */
	public List<Transaction> getTransactions(SortBy sortBy,
			SortByOrder sortByOrder, int start, int count, Category parent) {

		List<Transaction> transactionList = new ArrayList<Transaction>();

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		Cursor cursor;

		String sortByTemp = "date";
		String sortByOrderTemp = "desc";

		switch (sortBy) {
		case NAME:
			sortByTemp = "name";
			break;
		case DATE:
			sortByTemp = "date";
			break;
		case CATEGORY:
			sortByTemp = "category";
			break;
		}
		switch (sortByOrder) {
		case ASC:
			sortByOrderTemp = "ASC";
			break;
		case DESC:
			sortByOrderTemp = "DESC";
			break;
		}

		if (parent == null) {
			String[] arr = { "name", "date", "id", "value", "categoryId" };

			cursor = db.query("transactions", arr,
					"accountId == " + Constants.ACCOUNT_ID, null, null, null,
					sortByTemp + " " + sortByOrderTemp);
		} else {
			cursor = db
					.rawQuery(
							"SELECT transactions.name, transactions.date, transactions.id, transactions.value, transactions.categoryId, categories.parentId FROM transactions INNER JOIN categories ON transactions.categoryId==categories.id WHERE categories.id == "
									+ parent.getId()
									+ " OR categories.parentId == "
									+ parent.getId(), null);
		}

		int counter = 0;

		if (cursor.moveToFirst()) {
			Category cat = this.getCategory(cursor.getInt(5));
			Date date = new Date(cursor.getInt(1));
			Transaction transaction = new Transaction(cursor.getInt(2),
					(cursor.getDouble(3)), date, cursor.getString(0), cat);
			transactionList.add(transaction);
			counter++;
			while (cursor.moveToNext() && counter <= count) {
				cat = this.getCategory(cursor.getInt(5));
				date = new Date(cursor.getInt(1));
				transaction = new Transaction(cursor.getInt(2),
						(cursor.getDouble(3)), date, cursor.getString(0), cat);
				transactionList.add(transaction);
				counter++;
			}
		}
		
		return transactionList;
	}

	/**
	 * Returns all categories from the database.
	 */
	public List<Category> getCategories() {

		return this.getCategories(null);

	}

	/**
	 * Returns all categories under a certain parent category.
	 */
	public List<Category> getCategories(Category parent) {

		List<Category> list = new ArrayList<Category>();

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "name", "id", "parentId" };// use more?

		Cursor cursor;
		if (parent == null) {

			cursor = db.query("categories", arr, null, null, null, null, null);
		} else {
			cursor = db.rawQuery(
					"SELECT name, id, parentId FROM categories WHERE parentId == "
							+ parent.getId(), null);
		}
		Category category;
		if (cursor.moveToFirst()) {
			category = new Category(cursor.getString(0), cursor.getInt(1),
					this.getCategory(cursor.getInt(2)));
			list.add(category);
			while (cursor.moveToNext()) {

				category = new Category(cursor.getString(0), cursor.getInt(1),
						this.getCategory(cursor.getInt(2)));
				list.add(category);
			}

		}
		
		
		return list;
	}

	/**
	 * Returns a category with a certain ID.
	 */
	public Category getCategory(int id) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		String[] arr = { "name", "id", "parentId" };

		Cursor cursor = db.query("categories", arr, "id == " + id, null, null,
				null, null);

		if (cursor.moveToFirst()) {
			return new Category(cursor.getString(0), cursor.getInt(1),
					this.getCategory(cursor.getInt(2)));
		}
			
		return null;
	}

	/**
	 * Adds a category to the database.
	 */
	public void addCategory(String name, Category parent) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		
		String parentId = "null";
		
		if(parent != null){
			parentId = String.valueOf(parent.getId());
			
		}
		
		db.execSQL("INSERT INTO categories (name, parentId) VALUES ( " + "\""
				+ name + "\"" + ", " + parentId + ")");

//		if (parent.getId() == Constants.EXPENSE_ID || parent.getId() == Constants.INCOME_ID){
//			String parentId = String.valueOf(parent.getId());
//
//			db.execSQL("INSERT INTO categories (name, parentId) VALUES ( " + "\""
//					+ name + "\"" + ", " + parentId + ")");
//		} else {
//			throw new IllegalArgumentException("Parent ID must be either " +
//					"Constants.EXPENSE_ID or Constants.INCOME_ID");
//		}
			
		
	}

	/**
	 * Returns the settings from the database.
	 */
	public Settings getSettings() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		String[] arr = { "currentAccountId" };

		Cursor cursor = db.query("settings", arr, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			Settings settings = new Settings(cursor.getInt(0));
			
			return settings;
		}
		
		
		return null;
	}

	/**
	 * Adds a budget item to the database.
	 */
	public void addBudgetItem(Category category, Double value) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("categoryId", category.getId());
		values.put("value", value);

		db.insert("budgetitems", null, values);
		

	}

	/**
	 * Returns all budget items from the database.
	 */
	public List<BudgetItem> getBudgetItems() {
		return this.getBudgetItems(null);
	}

	/**
	 * Returns all budget items under a certain category from the databse.
	 */
	public List<BudgetItem> getBudgetItems(Category parent) {

		List<BudgetItem> budgetItemList = new ArrayList<BudgetItem>();

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		Cursor cursor;

		if (parent == null) {
			String[] columns = { "id", "categoryId", "value" };
			cursor = db.query("budgetitems", columns, null, null, null, null,
					null);
		} else {
			cursor = db
					.rawQuery(
							"SELECT budgetitems.id, budgetitems.categoryId, budgetitems.value, categories.parentId FROM budgetitems INNER JOIN categories ON budgetitems.categoryId==categories.id WHERE categories.id == "
									+ parent.getId()
									+ " OR categories.parentId == "
									+ parent.getId(), null);
		}

		if (cursor.moveToFirst()) {
			Category cat = this.getCategory(cursor.getInt(1));
			BudgetItem item = new BudgetItem(cursor.getInt(0), cat,
					cursor.getDouble(2));
			budgetItemList.add(item);
			while (cursor.moveToNext()) {
				cat = this.getCategory(cursor.getInt(1));
				item = new BudgetItem(cursor.getInt(0), cat,
						cursor.getDouble(2));
				budgetItemList.add(item);
			}
		}
		
		
		return budgetItemList;
	}

	/**
	 * Removes a certain budget item from the database.
	 */
	public void removeBudgetItem(BudgetItem item) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		db.delete("budgetitems", "id == " + item.getId(), null);
		

	}

	/**
	 * Edits an already existing budget item in the database.
	 */
	public void editBudgetItem(BudgetItem item, Double newValue) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("value", newValue);

		db.update("budgetitems", values, "id == " + item.getId(), null);
		

	}

	/**
	 * Removes a category from the database.
	 */
	public void removeCategory(Category category) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("DELETE FROM categories WHERE id ==" + category.getId());
		
	}

	/**
	 * Edits an already existing category in the database.
	 */
	public void editCategory(Category category, String newName) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("UPDATE categories SET name = \"" + newName
				+ "\" WHERE id == " + category.getId());
		
	}
}
