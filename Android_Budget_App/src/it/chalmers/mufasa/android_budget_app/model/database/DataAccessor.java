package it.chalmers.mufasa.android_budget_app.model.database;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataAccessor {
	Context context;

	public DataAccessor(Context context) {
		this.context = context;
	}

	public enum SortBy {
		NAME, DATE, CATEGORY
	}

	public enum SortByOrder {
		DESC, ASC
	}

	public int getAccountBalance(int accountID) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "balance" };
		Cursor cursor = db.query("accounts", arr, "id == " + accountID, null,
				null, null, null);

		if (cursor.moveToFirst()) {
			return cursor.getInt(0);
		} else {
			return -1;
		}
	}

	public void setAccountBalance(int balance, int accountID) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();

		if (getAccountBalance(accountID) == -1) {
			db.execSQL("INSERT INTO accounts VALUES (" + accountID + ",null,"
					+ balance + ")");
		} else {
			db.execSQL("UPDATE accounts SET balance=" + balance
					+ " WHERE id == " + accountID);
		}

	}

	public void addTransaction(Transaction transaction) {
		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		db.execSQL("INSERT INTO transactions (account-id, name, date, value, category-id ) ("
				+ transaction.getAccount().getId()
				+ ", "
				+ transaction.getName()
				+ ", "
				+ transaction.getDate()
				+ ", "
				+ transaction.getValue()
				+ ", "
				+ transaction.getCategory().getId() + ")");

	}

	public void removeTransaction(Transaction transaction) {

	}

	public List<Transaction> getTransactions(Account account, SortBy sortBy,
			SortByOrder sortByOrder, int start, int stop) {

		String sortByTemp;
		String sortByOrderTemp;

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
			sortByOrderTemp = "asc";
			break;
		case DESC:
			sortByOrderTemp = "desc";
			break;
		}

		SQLiteDatabase db = new DatabaseOpenHelper(context)
				.getWritableDatabase();
		String[] arr = { "name", "date", "category", "value" };

		Cursor cursor = db.query("table", arr, "account-id == "
				+ account.getAccountID, null, null, null, sortByTemp + " "
				+ sortByOrderTemp);

		if (cursor.moveToFirst()) {
			cursor.move(start);
			for (int i = start; i < stop; i++) {
				return cursor.getString(1);

				cursor.moveToNext();
			}
		} else {
			return -1;
		}
	}
}
