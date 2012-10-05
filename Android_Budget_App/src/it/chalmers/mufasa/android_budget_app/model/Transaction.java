package it.chalmers.mufasa.android_budget_app.model;

import java.util.Date;

/**
 * version 1
 * 
 * @author daubigne
 * 
 *         A class that is a transaction for the given account
 * 
 */
public class Transaction {
	private double amount;
	private Date date;
	private String name;
	private Category category;
	private int id;

	public Transaction(int id, double amount, Date date, String name,
			Category category) {
		setAmount(amount);
		setDate(date);
		setName(name);
		setCategory(category);
		setId(id);

	}

	private void setAmount(double amount) {
		this.amount = amount;
	}

	private void setDate(Date date) {
		this.date = date;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setCategory(Category category) {
		this.category = category;
	}

	private void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getName() {
		return this.name;
	}

	public Date getDate() {
		Date temp = this.date;
		return temp;
	}

	public Category getCategory() {
		Category temp = this.category;
		return temp;
	}
	
	public int getId() {
		return id;
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
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount)) {
			return false;
		}
		if (category.equals(other.category)) {
			return false;
		} else if (!(category.equals(other.category))) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
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
		return true;
	}

}
