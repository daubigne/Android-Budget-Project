package it.chalmers.mufasa.android_budget_app.test.model;

import it.chalmers.mufasa.android_budget_app.controller.TransactionListController;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import it.chalmers.mufasa.android_budget_app.model.TransactionListModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.test.AndroidTestCase;


public class TransactionListModelTest extends AndroidTestCase {
	
	
	public void testUpdateTransactionHistory() {
		Account account = new Account(1,"account", 20.0);
		Category cat1 = new Category("cat1",1,null);
		Category cat2 = new Category("cat2",2,null);
		Transaction transaction1 = new Transaction(1, 100, new Date(1,1,2012),"transaction1" ,cat1 , account);
		Transaction transaction2 = new Transaction(2, 200, new Date(2,1,2012),"transaction2", cat2, account);
		Transaction transaction3 = new Transaction(2, 200, new Date(2,2,2012),"transaction2", cat2, account);
		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		transactionList.add(transaction3);

		
		TransactionListModel transactionListModel = new TransactionListModel();
		transactionListModel.updateTransactionHistory(transactionList);
		
		if(!(transaction1.equals(transactionListModel.getTransactionHistory().get(0)))){
			fail("transaction1 is not equal");
		}
		if(!(transaction2.equals(transactionListModel.getTransactionHistory().get(1)))){
			fail("transaction2 is not equal");
		}
		if(!(transaction3.equals(transactionListModel.getTransactionHistory().get(2)))){
			fail("transaction3 is not equal");
		}
		

			
	}
	
	public void testGetAccount() {
		Account account1 = new Account(1,"account", 20.0);
		
		TransactionListModel transactionListModel = new TransactionListModel();
		transactionListModel.setAccount(account1);
		if(!(account1.equals(transactionListModel.getAccount()))){
			fail("Account is no equal");
		}
	}

}
