package it.chalmers.mufasa.android_budget_app.model;

public class Category {
	private String name;
	
	public Category(String name){
		this.name = name;
	}
	public Category(int id){
		
	}
	public String getName(){
		return this.name;
	}
	public int getId(){
		//TODO
		return 1;
	}
}
