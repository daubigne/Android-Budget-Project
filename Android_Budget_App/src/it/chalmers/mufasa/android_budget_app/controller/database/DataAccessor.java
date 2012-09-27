package it.chalmers.mufasa.android_budget_app.controller.database;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataAccessor {
	private Context context;

	public DataAccessor(Context context) {
		this.context = context;
	}

	public enum SortBy {
		NAME, DATE, CATEGORY
	}

	public enum SortByOrder {
		DESC, ASC
	}

	public List<Account> getAccounts() {
		
		List<Account> accountList = new ArrayList<Account>();
		
		SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
		String[] arr = { "id", "name", "balance"};
		Cursor cursor = db.query("accounts", arr, null, null,
				null, null, null);
		
		if (cursor.moveToFirst()) {
			accountList.add(new Account(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
			
			while(cursor.moveToNext()) {
				accountList.add(new Account(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
			}
		}
		
		return accountList;
		
	}
	
	public Account getAccount(int accountID) {
		SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
		String[] arr = { "id", "name" , "balance" };
		Cursor cursor = db.query("accounts", arr, "id == " + accountID, null,
				null, null, null);

		if (cursor.moveToFirst()) {
			return new Account(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2));
		} else {
			throw new IllegalArgumentException("Account ID "+accountID+" does not exist");
		}
	}

	public void addAccount(String name, double balance) {
		SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
		
		db.execSQL("INSERT INTO accounts (name, balance) VALUES (\""+name+"\","+balance+")");
	}
	
	public void setAccountBalance(Account account, double balance) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		
		double currentBalance = account.getBalance();
		    if(currentBalance != balance) {
			db.execSQL("UPDATE accounts SET balance=" + balance
				+ " WHERE id == " + account.getId());
		    }
	}

	public void addTransaction(Double amount, Date date, String name, Category category, Account account) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("INSERT INTO transactions (accountId, name, date, value, categoryId ) VALUES ( "
				+ account.getId()
				+ ", "
				+ "\"" + name + "\""
				+ ", "
				+ "\"" + date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + "\""
				+ ", "
				+ amount
				+ ", "
				+ category.getId() + ")");
		
		this.setAccountBalance(account, account.getBalance() + amount);
		
	}

	public void removeTransaction(Transaction transaction) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("DELETE FROM transactions WHERE id ==" + transaction.getId());
		this.setAccountBalance(transaction.getAccount(), transaction.getAccount().getBalance() - transaction.getAmount());
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

		Cursor cursor = db.query("transactions", arr, "accountId == "
				+ account.getId(), null, null, null, sortByTemp + " "
				+ sortByOrderTemp);

		if(cursor.moveToPosition(start)) {
			for (int i = start; i < Math.min(start+count,cursor.getCount()); i++) {
				String dateString = cursor.getString(1);
				String[] list = dateString.split("-");
				//Date date = new Date(Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2]));
				Date date = new Date(10000);
				
				Category category = new Category("untitled category",1,null);
				Transaction transaction = new Transaction((cursor.getInt(3)), date, cursor.getString(2), category, account);
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
		
		Cursor cursor = db.query("categories", arr, null, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			Category category = new Category(cursor.getString(0), cursor.getInt(1), this.getCategory(cursor.getInt(2)));
			list.add(category);
			
			while(cursor.moveToNext()) {
				category = new Category(cursor.getString(0), cursor.getInt(1), this.getCategory(cursor.getInt(2)));
				list.add(category);
			}
		}
		
		return list;
	}
	
	public Category getCategory(int id) {
		SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
		
		String[] arr = { "name", "id" , "parentId" };
		
		Cursor cursor = db.query("categories", arr, "id == " + id, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			return new Category(cursor.getString(0), cursor.getInt(1), this.getCategory(cursor.getInt(2)));
		}
		
		return null;
	}
	
	public void addCategory(String name, Category parent) {
		SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
		
		String parentId = "null";
		if(parent != null) {
			parentId = String.valueOf(parent.getId());
		}
		
		db.execSQL("INSERT INTO categories (name, parentId) VALUES ( "
		+ "\"" + name + "\""
		+ ", "
		+ parentId
		+ ")");

	}
	
}
