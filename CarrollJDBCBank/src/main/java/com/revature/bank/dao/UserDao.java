package com.revature.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bank.bean.*;

public interface UserDao {
	
	public List<User> loadUsers() throws SQLException;
	
	public List<Account> getAccountsByUsername(String username) throws SQLException;
	
	public int getUserIdFromDatabase(String username) throws SQLException;
	
	public boolean verifyUsername(String username) throws SQLException;
	
	public User findUserByUsername(String username) throws SQLException;
	
	public void addUser(User user) throws SQLException;
	
	public void updateUserInfo(String field, String newName, String username) throws SQLException;
	
	public void deleteCustomerAndAccounts(User user) throws SQLException;
}
