package it.chalmers.mufasa.android_budget_app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ManageCategoryModel {
	
	PropertyChangeSupport pcs;
	List <Category> categoryList;
	
	public ManageCategoryModel(){
		pcs = new PropertyChangeSupport(this);
	}
	
	public void setList(List<Category> categoryList){
		this.categoryList=categoryList;
		pcs.firePropertyChange("categoryList", null, null);
	}
	
	public List<Category> getList(){
		return categoryList;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	
}
