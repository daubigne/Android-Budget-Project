package it.chalmers.mufasa.android_budget_app.test.controller.database;


import it.chalmers.mufasa.android_budget_app.controller.database.DataAccessor;
import it.chalmers.mufasa.android_budget_app.model.Category;

import java.io.Console;
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

	public void testDummy() {
		assertTrue(true);
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

}