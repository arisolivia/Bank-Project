package com.revature.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bank.bean.*;

public interface AccountDao {
	
	public double getBalanceFromDatabase(int accountId) throws SQLException;
	
	public boolean verifyAccountExistByAccountId(int accountId) throws SQLException;
	
	public Account findAccountByAccountId(int accountId) throws SQLException;
	
	public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException;
	
	public void addTransactionToDatabase(Transaction trans) throws SQLException;
	
	public void updateAccountBalance(double newBalance, int id) throws SQLException;
	
	public void addToUserAccountTable(int accountId,String username) throws SQLException;
	
	public int addAccount(Account account) throws SQLException;
	
	public void deleteAccount(int accountId) throws SQLException;
	
	public int getNumberOfTotalUsers() throws SQLException;

}
