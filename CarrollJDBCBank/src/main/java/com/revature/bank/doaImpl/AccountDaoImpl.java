package com.revature.bank.doaImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.bean.Account;
import com.revature.bank.bean.Transaction;
import com.revature.bank.dao.AccountDao;
import com.revature.bank.exception.CannotDeleteAccountException;
import com.revature.bank.util.DBConnection;

public class AccountDaoImpl implements AccountDao {
	public static DBConnection db = DBConnection.getInstance();

	@Override
	public boolean verifyAccountExistByAccountId(int accountId) throws SQLException {
		Connection connect = db.getConnection();
		String selectQuery = "select * from accounts where id=?";
		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
		prepStmt.setInt(1, accountId);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	@Override
	public Account findAccountByAccountId(int accountId) throws SQLException {
		//return account with specified account ID
		Connection connect = db.getConnection();
		String selectQuery = "select * from accounts where id=?";
		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
		prepStmt.setInt(1, accountId);
		ResultSet rs = prepStmt.executeQuery();
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3));
		}
		return a;

	}
	@Override
	public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException {
		// get transactions made in account by any user
		List<Transaction> tList = new ArrayList<>();
		Connection connect = db.getConnection();
		String selectQuery = "select * from transaction_history where account_id=?";
		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
		prepStmt.setInt(1, accountId);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			Transaction t = new Transaction(rs.getInt(2), rs.getString(3), rs.getDouble(4),rs.getTimestamp(5));
			tList.add(t);
		}
		return tList;
	}
	@Override
	public void addTransactionToDatabase(Transaction trans) throws SQLException {
		//add new transaction to the database
		Connection connect = db.getConnection();
		String insertQuery = "insert into transaction_history values(default, ?, ?, ?, now())";
		PreparedStatement prepStmt = connect.prepareStatement(insertQuery);
 
			prepStmt.setInt(1, trans.getAccountId());
			prepStmt.setString(2, trans.getUsername());
			prepStmt.setDouble(3, trans.getAmount());
			prepStmt.executeUpdate();
	}
	@Override
	public void addToUserAccountTable(int accountId,String username) throws SQLException {
		//add to user_account table in database
		Connection connect = db.getConnection();
		String insertQuery = "insert into user_account values(?, ?)";
		PreparedStatement prepStmt = connect.prepareStatement(insertQuery);
 
			prepStmt.setString(1, username);
			prepStmt.setInt(2, accountId);
			prepStmt.executeUpdate();
	}
	@Override
	public double getBalanceFromDatabase(int accountId) throws SQLException {
		Connection connect = db.getConnection();
		String selectQuery = "select balance from accounts where id = ?";
		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
		prepStmt.setInt(1, accountId);
		ResultSet rs = prepStmt.executeQuery();
		double balance = 0;
		while(rs.next()) {
			rs.getDouble(1);
		}
		return balance;
	}
	@Override
	public void updateAccountBalance(double newBalance, int id) throws SQLException {
		//update account balance in database
		Connection connect = db.getConnection();
		String updateQuery = "update accounts set balance=? where id=?";
		PreparedStatement prepStmt = connect.prepareStatement(updateQuery);
			prepStmt.setDouble(1, newBalance);
			prepStmt.setInt(2, id);
			prepStmt.executeUpdate();
	}
	@Override
	public int addAccount(Account account) throws SQLException {
		//add new account to database and get the ID of the account
		Connection connect = db.getConnection();
		String selectQuery = "insert into accounts values(default, ?, ?) returning id";
		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
		prepStmt.setString(1, account.getType());
		prepStmt.setDouble(2, account.getBalance());
		ResultSet rs = prepStmt.executeQuery();
		int id = 0;
		while(rs.next()) {
			id = rs.getInt(1);
		}
		return id;
	}
	@Override
	public void deleteAccount(int accountId) throws SQLException {
		Connection connect = db.getConnection();
		String selectQuery = "select balance from accounts where id=?";
		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
		prepStmt.setInt(1, accountId);
		ResultSet rs = prepStmt.executeQuery();
		int balance = 0;
		while(rs.next()) {
			balance = rs.getInt(1);
			if (balance > 0) {
				throw new CannotDeleteAccountException("There is money in account. Account cannot be deleted.");
			} else {
				String deleteQuery = "delete from accounts where id=?";
				PreparedStatement prepStmt2 = connect.prepareStatement(deleteQuery);
				prepStmt2.setInt(1, accountId);
				int row = prepStmt2.executeUpdate();
			}
		}

	}
	@Override
	public int getNumberOfTotalUsers() throws SQLException {
		Connection connect = db.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery("select total_users()");
		int total = 0;
		while (rs.next()) {
			total = rs.getInt(1);
		}
		return total;
	}
}
