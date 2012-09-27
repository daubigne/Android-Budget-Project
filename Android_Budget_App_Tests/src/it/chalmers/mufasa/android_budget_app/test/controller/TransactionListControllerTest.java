package it.chalmers.mufasa.android_budget_app.test.controller;

import it.chalmers.mufasa.android_budget_app.model.Account;
import it.chalmers.mufasa.android_budget_app.model.Transaction;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import junit.framework.TestCase;

public class TransactionListControllerTest extends TestCase {
	
	private Account account;
	private ArrayList<Transaction> transactionHistory;
	private PropertyChangeSupport pcs;
}
