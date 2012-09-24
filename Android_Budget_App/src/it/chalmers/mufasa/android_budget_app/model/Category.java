package it.chalmers.mufasa.android_budget_app.model;


public class Category {
	private String name;
	private int id;
	private Category parent;
	
	public Category(String name, int id, Category parent){
		this.name = name;
		this.id = id;
		this.parent = parent;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public Category getParent(){
		return this.parent;
	}
}
