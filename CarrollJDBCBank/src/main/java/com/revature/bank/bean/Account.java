package com.revature.bank.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.doaImpl.AccountDaoImpl;
import com.revature.bank.util.*;

public class Account {
	private AccountDaoImpl adi = new AccountDaoImpl();

	private int accountId = 0;
	private String type;
	private double balance = 0;
	private List<Transaction> transactions = new ArrayList<>();
	
	public Account() {
		
	}
	public Account(String type, String username) {
		this.type = type;
		Logging.LogIt("info","Customer, username = "+username+", has created a new "+type+" account");
	}
	public Account(int accountId, String type, double balance) {
		this.accountId = accountId;
		this.type = type;
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int id) {
		this.accountId = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Transaction> getTransactions() {
		transactions.clear();
		try {
				transactions.addAll(adi.getTransactionsByAccountId(this.accountId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	public void setTransactions(Transaction transaction) {
		this.transactions.add(transaction);
	}
	public void withdraw(String value, User user) {
		if (Verify.verifyNum(value)) {
			value = value.substring(0, value.length()-2)+"."+value.substring(value.length()-2, value.length());
			double amount = Double.parseDouble(value);
			this.balance -= amount;
			if (this.balance < 0) {
				System.out.println("Transaction incomplete. Insufficient funds\n");
				this.balance += amount; 
			} else {
				Transaction trans = new Transaction(this.accountId, user.getUsername(), amount);
				try {
					adi.updateAccountBalance(this.balance, this.accountId);
					adi.addTransactionToDatabase(trans);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.transactions.add(trans);
				Logging.LogIt("info",user.getType()+", ID = "+user.getUserId()+", has withdrew $" + amount +" into account, ID = "+this.accountId+". The new balance is $" + this.balance);
				System.out.println(" ");
			}
		}
	}
	public void deposit(String value, User user) {
		if (Verify.verifyNum(value)) {
			value = value.substring(0, value.length()-2)+"."+value.substring(value.length()-2, value.length());
			double amount = Double.parseDouble(value);
			this.balance += amount;
			Transaction trans = new Transaction(this.accountId, user.getUsername(), amount);
			try {
				adi.updateAccountBalance(this.balance, this.accountId);
				adi.addTransactionToDatabase(trans);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.transactions.add(trans);
			Logging.LogIt("info",user.getType()+", ID = "+user.getUserId()+", has deposited $" + amount +" into account, ID = "+this.accountId+". The new balance is $" + this.balance);	
			System.out.println(" ");
		}
	}
	@Override
	public String toString() {
		return type + " = " + accountId + " = $" + balance;
	}
}
