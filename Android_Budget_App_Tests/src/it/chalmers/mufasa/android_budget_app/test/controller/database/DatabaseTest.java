package it.chalmers.mufasa.android_budget_app.test.controller.database;


import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortBy;
import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor.SortByOrder;
import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.util.Date;
import java.util.List;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class DatabaseTest extends AndroidTestCase {
	
	DataAccessor dataAccessor;

	protected void setUp() throws Exception {
		super.setUp();
		
		RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "_test"); //This will start with fresh userdata
		
		dataAccessor = new DataAccessor(context);
	}

	public void testAddAccount() {
		
		double balance = 10.0;
		
		dataAccessor.addAccount("My Account",balance);
		
		System.out.println("ACCOUNTS " + dataAccessor.getAccounts().size());
		
		for(Account a : dataAccessor.getAccounts()) {
			System.out.println(a.getName() + " B: " + a.getBalance());
		}
		
		//assertTrue(true);
		
		assertTrue(dataAccessor.getAccount(1).getBalance() == balance);
	}
	
	public void testAddCategory() {
		dataAccessor.addCategory("1", null);
		dataAccessor.addCategory("2", null);
		
		List<Category> catList = dataAccessor.getCategories();
		
		if(catList.size() != 2) {
			fail("Could not add and get two first categories. Size:"+catList.size());
		}
	}
	
	public void testAddSubCategories() {
		
		dataAccessor.addCategory("1", null);
		dataAccessor.addCategory("2", null);
		
		List<Category> catList = dataAccessor.getCategories();
		
		if(catList.size() != 2) {
			fail("Could not add and get two first categories. Size:"+catList.size());
		}
		
		for(Category cat : catList) {
			dataAccessor.addCategory(cat.getName()+"-1", cat);
			dataAccessor.addCategory(cat.getName()+"-2", cat);
		}
		
		catList = dataAccessor.getCategories();
		
		for(Category cat : catList) {
			if(cat.getParent() != null) {
				dataAccessor.addCategory(cat.getName()+"-1", cat);
				dataAccessor.addCategory(cat.getName()+"-2", cat);
			}
		}
		
		catList = dataAccessor.getCategories();
		
		//Print out just for fun
		for(Category cat : catList) {
			String parent = "";
			if(cat.getParent() != null) {
				parent = cat.getParent().getName();
			}
			System.out.println(cat.getName() + " Parent:" + parent);
			
		}
		
		if(catList.size() != (2+2*2+2*2*2)) {
			fail("Could not add or get subcategories. Size:"+catList.size());
		}
	}
	
	public void testAddTransactions() {
		dataAccessor.addAccount("My Account",0);
		dataAccessor.addCategory("category", null);
		
		Account account = dataAccessor.getAccount(1);
		Category category = dataAccessor.getCategory(1);
		
		dataAccessor.addTransaction(100.0, new Date(), "transaction1", category, account);
		dataAccessor.addTransaction(100.0, new Date(), "transaction2", category, account);
		
		List<Transaction> list = dataAccessor.getTransactions(account, SortBy.DATE, SortByOrder.DESC, 0, 10);
		for(Transaction t : list) {
			System.out.println("Name: " + t.getName() + " Date: " + t.getDate().getYear() + t.getDate().getMonth() + t.getDate().getDay() + " Value: " + t.getAmount());
		}
		
		account = dataAccessor.getAccount(1);
		
		System.out.println("Balance: " + account.getBalance());
		
		assertTrue(list.size() == 1 && account.getBalance() == 100.0);
		
	}
	
	public void testRemoveCategory(){
		Category category;
		dataAccessor.addCategory("herpaderp", null);
		
		category = dataAccessor.getCategory(1);
		System.out.println(category.getName());
		
		dataAccessor.removeCategory(category);
		if(dataAccessor.getCategory(category.getId())!=null){
			fail("Could not remove category: "+category.getName());
		}
		
		
	}
	
	public void testEditCategory(){
		Category category;
		
		dataAccessor.addCategory("herpaderp", null);
		category = dataAccessor.getCategory(1);
		dataAccessor.editCategory(category, "derpaherp");
		category = dataAccessor.getCategory(1);
		
		if(!category.getName().equals("derpaherp")){
			fail("Could not rename category: "+category.getName());
		}
		
		
	}

}