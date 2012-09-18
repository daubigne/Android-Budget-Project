package it.chalmers.mufasa.android_budget_app.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataAccessor {
    
    Context context;
    
    public DataAccessor(Context context) {
	this.context = context;
    }
    
    public int getAccountBalance(int accountID) {
	SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
	String[] arr = {"balance"};
	Cursor cursor = db.query("account", arr, "id == "+accountID, null, null, null, null);
	
	if(cursor.moveToFirst()) {
	    return cursor.getInt(0);
	} else {
	    return -1;
	}
    }
    
    public void setAccountBalance(int balance, int accountID) {
	SQLiteDatabase db = new DatabaseOpenHelper(context).getWritableDatabase();
	
	if(getAccountBalance(accountID)==-1) {
	    db.execSQL("INSERT INTO account VALUES ("+accountID+",null,"+balance+")");
	} else {
	    db.execSQL("UPDATE account SET balance="+balance+" WHERE id == "+accountID);
	}
	
    }
}
