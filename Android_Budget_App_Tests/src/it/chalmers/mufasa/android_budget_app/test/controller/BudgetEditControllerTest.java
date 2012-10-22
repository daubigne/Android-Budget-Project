// /*
//  * Copyright © 2012 Mufasa developer unit
//  *
//  * This file is part of Mufasa Budget.
//  *
//  *	Mufasa Budget is free software: you can redistribute it and/or modify
//  * it under the terms of the GNU General Public License as published by
//  * the Free Software Foundation, either version 3 of the License, or
//  * (at your option) any later version.
//  *
//  * Mufasa Budget is distributed in the hope that it will be useful,
//  * but WITHOUT ANY WARRANTY; without even the implied warranty of
//  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  * GNU General Public License for more details.
//  *
//  * You should have received a copy of the GNU General Public License
//  * along with Mufasa Budget.  If not, see <http://www.gnu.org/licenses/>.
//  */
//package it.chalmers.mufasa.android_budget_app.test.controller;
//
//import it.chalmers.mufasa.android_budget_app.controller.BudgetEditController;
//import it.chalmers.mufasa.android_budget_app.model.BudgetEditModel;
//import it.chalmers.mufasa.android_budget_app.model.BudgetItem;
//import it.chalmers.mufasa.android_budget_app.model.Category;
//import android.test.AndroidTestCase;
//import android.test.RenamingDelegatingContext;
//
//
//public class BudgetEditControllerTest extends AndroidTestCase {
//
//
//	protected void setUp() throws Exception {
//		super.setUp();
//	}
//
//	public void testAddGetEditRemoveBudgetItem() {
//		
//		RenamingDelegatingContext context = new RenamingDelegatingContext(
//				getContext(), "_test"); // This will start with fresh userdata
//
//		BudgetEditModel model = new BudgetEditModel(context);
//		BudgetEditController controller = new BudgetEditController(model);
//		
//		int initSize = model.getBudgetItems().size();
//		
//		//TODO Get category from database?
//		Category cat = new Category("Mat",4,new Category("Utgifter",2,null));
//		
//		controller.addBudgetItem(cat,123.0);
//		
//		if(model.getBudgetItems().size() <= initSize) {
//			fail("can not add budget item");
//		}
//		
//		BudgetItem justAdded = null;
//		
//		for(BudgetItem item : model.getBudgetItems()) {
//			if(item.getCategory().equals(cat) && item.getValue() == 123.0) {
//				justAdded = item;
//			}
//		}
//		
//		if(justAdded == null) {
//			fail("can not get equal budget item");
//		}
//		
//		controller.editBudgetItem(justAdded, 234.0);
//		
//		BudgetItem justEdited = null;
//		
//		for(BudgetItem item : model.getBudgetItems()) {
//			if(item.getCategory().equals(cat) && item.getValue() == 234.0) {
//				justEdited = item;
//			}
//		}
//		
//		if(justEdited == null) {
//			fail("can not edit budget item");
//		}
//		
//		controller.removeBudgetItem(justEdited);
//		
//		if(model.getBudgetItems().size() != initSize) {
//			fail("can not remove budget item");
//		}
//	}
//
//}