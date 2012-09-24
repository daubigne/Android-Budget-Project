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

	public Account getAccount(int accountID) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "id", "name" , "balance" };
		Cursor cursor = db.query("accounts", arr, "id == " + accountID, null,
				null, null, null);

		if (cursor.moveToFirst()) {
			return new Account(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2));
		} else {
			throw new IllegalArgumentException("Account ID "+accountID+" does not exist");
		}
	}

	public void setAccountBalance(double balance, int accountID) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		try {
		    double currentBalance = getAccount(accountID).getBalance();
		    if(currentBalance != balance) {
			db.execSQL("UPDATE accounts SET balance=" + balance
				+ " WHERE id == " + accountID);
		    }
		} catch(IllegalArgumentException e) {
		    db.execSQL("INSERT INTO accounts VALUES (" + accountID + ",null,"
				+ balance + ")");
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

	}

	public void removeTransaction(Transaction transaction) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("DELETE FROM transactions WHERE id ==" + transaction.getId());
	}

	public List<Transaction> getTransactions(Account account, SortBy sortBy,
			SortByOrder sortByOrder, int start, int stop) {

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
		Log.println(9,"MainController", "hej");
		if (cursor.moveToFirst()) {
			Log.println(9,"MainController", "hej");
			cursor.moveToPosition(start);
			for (int i = start; i < stop; i++) {
				String dateString = cursor.getString(1);
				Log.println(9,"MainController", dateString);
				String[] list = dateString.split("-");
				for(String s : list) {
					Log.println(9,"MainController", s);
				}
				//Date date = new Date(Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2]));
				Date date = new Date(10000);
				
				Category category = new Category("untitled category");
				Transaction transaction = new Transaction((cursor.getInt(3)), date, cursor.getString(2), category, account);
				transactions.add(transaction); 
				cursor.moveToNext();
			}
			return transactions;
		} else {
			return null;
		}
	}
}
