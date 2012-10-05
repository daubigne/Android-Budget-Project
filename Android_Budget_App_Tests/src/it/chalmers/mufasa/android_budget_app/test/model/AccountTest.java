package it.chalmers.mufasa.android_budget_app.test.model;

import java.util.Date;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import it.chalmers.mufasa.android_budget_app.model.Transaction;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import junit.framework.TestCase;

public class AccountTest extends AndroidTestCase {

	Account account1;
	Category category1;
	Category category2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test");
		account1 = Account.getInstance(context);
		category1 = new Category("category1", 1, null);
		category2 = new Category("category2", 2, null);

	}

	public void testBalance() {
		account1.setBalance(25.0);
		if (account1.getBalance() != 25.0) {
			fail("Balance is not equal");
		}
	}

	public void testId() {
		if (account1.getId() != 1) {
			fail("ID is not equal");
		}
	}

	public void testTransactions() {
		account1.setBalance(0);
		Transaction transaction1 = new Transaction(1, 3.0, new Date(),
				"transaction1", category1);
		Transaction transaction2 = new Transaction(2, 5.0, new Date(),
				"transaction2", category1);
		Transaction transaction3 = new Transaction(3, 10.0, new Date(),
				"transaction3", category1);

		account1.addTransaction(transaction1.getAmount(),
				transaction1.getDate(), transaction1.getName(),
				transaction1.getCategory());
		account1.addTransaction(transaction2.getAmount(),
				transaction2.getDate(), transaction2.getName(),
				transaction2.getCategory());
		account1.addTransaction(transaction3.getAmount(),
				transaction3.getDate(), transaction3.getName(),
				transaction3.getCategory());
		account1.removeTransaction(transaction3);
		if (account1.getBalance() != 8.0) {
			fail("Balance isn't 8.0 it's " + account1.getBalance());
		}
		if (account1.getTransactions(100).size() != 2) {
			fail("The number of transaction isn't 2 it's "
					+ account1.getTransactions(100).size());
		}

	}

	public void testBudgetItems() {
		
		int budgetListSize = account1.getBudgetItems().size();
		BudgetItem budgetItem1 = new BudgetItem(1, category1, 50.0);

		BudgetItem budgetItem2 = new BudgetItem(2, category2, 30.0);

		BudgetItem budgetItem3 = new BudgetItem(3, category1, 100.0);

		account1.addBudgetItem(budgetItem1.getCategory(),
				budgetItem1.getValue());
		account1.addBudgetItem(budgetItem2.getCategory(),
				budgetItem2.getValue());
		account1.addBudgetItem(budgetItem3.getCategory(),
				budgetItem3.getValue());

		account1.removeBudgetItem(budgetItem1);

		if (account1.getBudgetItems().size() - budgetListSize != 2) {
			fail("The number of added budget items isn't 2 it's "
					+ account1.getBudgetItems().size());
		}

	}
	
	public void tesCategories() {
			
			int budgetListSize = account1.getBudgetItems().size();
			Category category3 = new Category("category3", 3, category1);
	
	
			account1.addCategory(category1.getName(), category1.getParent());
			account1.addCategory(category2.getName(), category2.getParent());
			account1.addCategory(category3.getName(), category3.getParent());
			
			account1.removeCategory(category2);
			
			if (account1.getCategories().size() != 2) {
				fail("The number of categories isn't 2 it's "
						+ account1.getCategories().size());
			}
	
		}

}
