///**
// * 
// */
//package it.chalmers.mufasa.android_budget_app.test.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import it.chalmers.mufasa.android_budget_app.controller.TransactionListController;
//import it.chalmers.mufasa.android_budget_app.model.Category;
//import it.chalmers.mufasa.android_budget_app.model.Transaction;
//import it.chalmers.mufasa.android_budget_app.model.TransactionListModel;
//import android.test.AndroidTestCase;
//import android.test.RenamingDelegatingContext;
//
///**
// * @author Slurpo
// * 
// */
//public class TransactionListControllerTest extends AndroidTestCase {
//
//
//	protected void setUp() throws Exception {
//		super.setUp();
//	}
//
//	public void testAddTransactions() {
//		
//		RenamingDelegatingContext context = new RenamingDelegatingContext(
//				getContext(), "_test"); // This will start with fresh userdata
//
//		TransactionListModel model = new TransactionListModel();
//		TransactionListController controller = new TransactionListController(context, model);
//		
//		Category cat = new Category("CatFromTransactionListControllerTest", 1,
//				null);
//
//		controller.addTransaction(100.0, new Date(), "transaction1", cat,
//				model.getAccount());
//
//		controller.addTransaction(100.0, new Date(), "transaction2", cat,
//				model.getAccount());
//
//		List<Transaction> list = model.getTransactionHistory();
//		
//		if (list.size() != 2) {
//			fail("Size != 2 is " + list.size());
//		}
//		if (model.getAccount().getBalance() != 200.0) {
//			fail("Balance != 200 is " + model.getAccount().getBalance());
//		}
//	}
//	
//	public void testRemoveTransactions() {
//		
//		RenamingDelegatingContext context = new RenamingDelegatingContext(
//				getContext(), "_test"); // This will start with fresh userdata
//
//		TransactionListModel model = new TransactionListModel();
//		TransactionListController controller = new TransactionListController(context, model);
//		
//		Category cat = new Category("CatFromTransactionListControllerTest", 1,
//				null);
//		controller.addTransaction(75.0, new Date(2,1,2012), "transaction1", cat,
//				model.getAccount());
//
//		controller.addTransaction(25.0, new Date(3,1,2012), "transaction2", cat,
//				model.getAccount());
//
//
//		controller.addTransaction(100.0, new Date(4,1,2012), "transaction3", cat, 
//				model.getAccount());
//
//		controller.removeTransaction(model.getTransactionHistory().get(0));
//
//		controller.removeTransaction(model.getTransactionHistory().get(0));
//
//
//
//		List<Transaction> list = model.getTransactionHistory();
//
//		if (list.size() != 1) {
//			fail("Size != 1 is " + list.size());
//		}
//		if (model.getAccount().getBalance() != 75) {
//			fail("Balance != 75 is " + model.getAccount().getBalance());
//		}
//	}
//}
