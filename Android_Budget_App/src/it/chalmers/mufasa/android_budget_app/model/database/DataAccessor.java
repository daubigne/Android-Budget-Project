package it.chalmers.mufasa.android_budget_app.model.database;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.settings.Constants;
import it.chalmers.mufasa.android_budget_app.settings.Settings;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//this.addAccount(account.getName(), account.getBalance());
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

	/*public Account getAccount(int accountID) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
		Cursor cursor = db.query("accounts", arr, "id == " + accountID, null,
				null, null, null);

		if (cursor.moveToFirst()) {
			return new Account(cursor.getInt(0), cursor.getString(1),
					cursor.getDouble(2));
		} else {
			throw new IllegalArgumentException("Account ID " + accountID
					+ " does not exist");
		}
	}*/
	
	public boolean accountExists() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
		//TODO Account ID????
		Cursor cursor = db.query("accounts", arr, "id == " + 1, null,
				null, null, null);

		return cursor.moveToFirst();
		
	}

	public void addAccount(String name, double balance) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		db.execSQL("INSERT INTO accounts (name, balance) VALUES (\"" + name
				+ "\"," + balance + ")");
	}

	public void setAccountBalance(double balance, int id) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("UPDATE accounts SET balance=" + balance + " WHERE id == "
				+ id);

	}

	public void updateAccount(Account account) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("balance", Double.toString(account.getBalance()));
		values.put("name", account.getName());

		db.update("accounts", values, "id == " + account.getId(), null);

	}

	public Double getAccountBalance() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
		Cursor cursor = db.query("accounts", arr, "id == " + Constants.ACCOUNT_ID,
				null, null, null, null);

		if (cursor.moveToFirst()) {
			return cursor.getDouble(2);
		} else {
			// throw new
			// IllegalArgumentException("Could not access account balance");
			return null;
		}
	}

	public String getAccountName() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };

		Cursor cursor = db.query("accounts", arr, "id == " + Constants.ACCOUNT_ID,
				null, null, null, null);

		if (cursor.moveToFirst()) {
			return cursor.getString(1);
		} else {
			throw new IllegalArgumentException("Could not access account name");
		}
	}

	public int getAccountId() {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name", "balance" };
		Cursor cursor = db.query("accounts", arr, "id == " + Constants.ACCOUNT_ID,
				null, null, null, null);

		if (cursor.moveToFirst()) {
			return cursor.getInt(0);
		} else {
			throw new IllegalArgumentException("Could not access account ID");
		}
	}

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
				+ "\"" + ", " + amount + ", " + category.getId() + ")");
		
		this.setAccountBalance(getAccountBalance() + amount, Constants.ACCOUNT_ID);

	}

	public void removeTransaction(Transaction transaction) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("DELETE FROM transactions WHERE id ==" + transaction.getId());
		this.setAccountBalance(getAccountBalance()
				- transaction.getAmount(), Constants.ACCOUNT_ID);
	}

	public List<Transaction> getTransactions(Account account, SortBy sortBy,
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

		String[] arr = { "name", "date", "id", "value" };

		Cursor cursor = db.query("transactions", arr,
				"accountId == " + account.getId(), null, null, null, sortByTemp
						+ " " + sortByOrderTemp);

		if (cursor.moveToPosition(start)) {
			for (int i = start; i < Math.min(start + count, cursor.getCount()); i++) {
				String dateString = cursor.getString(1);
				String[] list = dateString.split("-");
				// Date date = new Date(Integer.parseInt(list[0]),
				// Integer.parseInt(list[1]), Integer.parseInt(list[2]));
				Date date = new Date(10000);

				Category category = new Category("untitled category", 1, null);
				Transaction transaction = new Transaction(cursor.getInt(2),
						(cursor.getInt(3)), date, cursor.getString(2), category);
				transactions.add(transaction);

				cursor.moveToNext();
			}
		}

		return transactions;
	}

	public List<Category> getCategories() {

		List<Category> list = new ArrayList<Category>();

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "name", "id", "parentId" };

		Cursor cursor = db.query("categories", arr, null, null, null, null,
				null);

		if (cursor.moveToFirst()) {
			Category category = new Category(cursor.getString(0),
					cursor.getInt(1), this.getCategory(cursor.getInt(2)));
			list.add(category);

			while (cursor.moveToNext()) {
				category = new Category(cursor.getString(0), cursor.getInt(1),
						this.getCategory(cursor.getInt(2)));
				list.add(category);
			}
		}

		return list;
	}

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

	public void addCategory(String name, Category parent) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		String parentId = "null";
		if (parent != null) {
			parentId = String.valueOf(parent.getId());
		}

		db.execSQL("INSERT INTO categories (name, parentId) VALUES ( " + "\""
				+ name + "\"" + ", " + parentId + ")");
	}

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

	public void addBudgetItem(Category category, Double value) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("categoryId", category.getId());
		values.put("value", value);

		db.insert("budgetitems", null, values);

	}

	public List<BudgetItem> getBudgetItems() {

		List<BudgetItem> budgetItemList = new ArrayList<BudgetItem>();

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		String[] columns = { "id", "categoryId", "value" };

		Cursor cursor = db.query("budgetitems", columns, null, null, null,
				null, null);

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

	public void removeBudgetItem(BudgetItem item) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		db.delete("budgetitems", "id == " + item.getId(), null);

	}

	public void editBudgetItem(BudgetItem item, Double newValue) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("value", newValue);

		db.update("budgetitems", values, "id == " + item.getId(), null);

	}

	public void removeCategory(Category category) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("DELETE FROM categories WHERE id ==" + category.getId());
	}

	public void editCategory(Category category, String newName) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("UPDATE categories SET name = \"" + newName
				+ "\" WHERE id == " + category.getId());
	}

}