package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ManageCategoryModel {
	
	private PropertyChangeSupport pcs;
	private List <Category> categoryList;
	private Category currentCategory;
	private boolean editMode;
	
	public ManageCategoryModel(){
		pcs = new PropertyChangeSupport(this);
	}
	
	public void setList(List<Category> categoryList){
		this.categoryList=categoryList;
		pcs.firePropertyChange("categoryList", null, null);
	}
	
	public List<Category> getCategoryList(){
		return categoryList;
	}
	
	public Category getCurrentCategory(){
		return currentCategory;
	}
	public void setCurrentCategory(Category category){
		currentCategory = category;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	public boolean isEditMode(){
		return editMode;
		
	}
	public void setEditMode(boolean b){
		pcs.firePropertyChange("EditSaveMode", null, null);
		editMode=b;
		System.out.println("fan");
	}	
}

