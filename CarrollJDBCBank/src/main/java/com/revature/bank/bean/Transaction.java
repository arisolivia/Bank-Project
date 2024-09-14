package com.revature.bank.bean;

import java.sql.Timestamp;

import com.revature.bank.util.Verify;

public class Transaction {
	private int accountId;
	private String username;
	private double amount;
	private Timestamp date;
	
	public Transaction() {
	
	}
	public Transaction(int accountId,String username, double amount) {
		
		this.accountId = accountId;
		this.username = username;
		this.amount = amount;
	}
	public Transaction(int accountId, String username, double amount, Timestamp date) {
	
		this.accountId = accountId;
		this.username = username;
		this.amount = amount;
		this.date = date;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUserUsername(String username) {
		this.username = username;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction [Made on "+date+" by user ID = "+username+" in account "+Verify.findAccount(String.valueOf(accountId))+" in the amount of $"+amount+"]";
	}
	
	
	

}
