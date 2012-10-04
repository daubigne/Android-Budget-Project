/**
 * 
 */
package it.chalmers.mufasa.android_budget_app.test.controller;

import it.chalmers.mufasa.android_budget_app.controller.BudgetEditController;
import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
import it.chalmers.mufasa.android_budget_app.model.Category;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;


public class BudgetEditControllerTest extends AndroidTestCase {


	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testAddGetEditRemoveBudgetItem() {
		
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), "_test"); // This will start with fresh userdata

		BudgetEditModel model = new BudgetEditModel();
		BudgetEditController controller = new BudgetEditController(context, model);
		
		int initSize = model.getBudgetItems().size();
		
		//TODO Get category from database?
		Category cat = new Category("Mat",4,new Category("Utgifter",2,null));
		
		controller.addBudgetItem(cat,123.0);
		
		if(model.getBudgetItems().size() <= initSize) {
			fail("can not add budget item");
		}
		
		BudgetItem justAdded = null;
		
		for(BudgetItem item : model.getBudgetItems()) {
			if(item.getCategory().equals(cat) && item.getValue() == 123.0) {
				justAdded = item;
			}
		}
		
		if(justAdded == null) {
			fail("can not get equal budget item");
		}
		
		controller.editBudgetItem(justAdded, 234.0);
		
		BudgetItem justEdited = null;
		
		for(BudgetItem item : model.getBudgetItems()) {
			if(item.getCategory().equals(cat) && item.getValue() == 234.0) {
				justEdited = item;
			}
		}
		
		if(justEdited == null) {
			fail("can not edit budget item");
		}
		
		controller.removeBudgetItem(justEdited);
		
		if(model.getBudgetItems().size() != initSize) {
			fail("can not remove budget item");
		}
	}
}
