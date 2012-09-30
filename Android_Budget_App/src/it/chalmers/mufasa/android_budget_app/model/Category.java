package it.chalmers.mufasa.android_budget_app.model;

/**
 * A class representing categories in which transactions will be placed in.
 * 
 * @author marcusisaksson
 * 
 */

public class Category {
	private String name;
	private int id;
	private Category parent;

	public Category(String name, int id, Category parent) {
		setName(name);
		setId(id);
		setParent(parent);
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setParent(Category parent) {
		this.parent = parent;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public Category getParent() {
		return this.parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Category other = (Category) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		return true;
	}
}
