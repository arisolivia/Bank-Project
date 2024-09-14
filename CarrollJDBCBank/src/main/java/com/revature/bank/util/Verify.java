package com.revature.bank.util;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import com.revature.bank.bean.*;
import com.revature.bank.doaImpl.AccountDaoImpl;
import com.revature.bank.doaImpl.UserDaoImpl;
import com.revature.bank.exception.CannotDeleteAccountException;

public class Verify {
	static UserDaoImpl udi = new UserDaoImpl();
	static AccountDaoImpl adi = new AccountDaoImpl();
	public static List<User> customerList = new ArrayList<>();
	public static List<Transaction> transactionList = new ArrayList<>();
	
	public static void getCustomers() {
		try {
			customerList.addAll(udi.loadUsers());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//check if account exist
	public static boolean isAccountInDatabase(String accountId) {
		if (verifyNum(accountId)) {
			int id = Integer.valueOf(accountId);
			try {
				if (adi.verifyAccountExistByAccountId(id)) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Invald account ID\n");
		}
		return false;
	}
	public static Account findAccount(String accountId) {
		int id = Integer.valueOf(accountId);
		try {
			return adi.findAccountByAccountId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static boolean isUsernameInDatabase(String username) {
		try {
			if(udi.verifyUsername(username)) {
				return true;
			}else {
				System.out.println("Username not found.\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static User findUser(String username) {
		try {
			return udi.findUserByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void addUserToDatabase(User user) {
		try {
			udi.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String verifyType(String type) {
		if (type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("savings") || type.equalsIgnoreCase("joint")) {
			return type.toUpperCase();
		} else if (type.equalsIgnoreCase("customer") || type.equalsIgnoreCase("employee") || type.equalsIgnoreCase("admin")) {
			return type.toUpperCase();
		}
		System.out.println("Invalid type\\n");
		return null;
	}
	//check if special characters or letters are used
	public static boolean verifyNum(String number) {
		for (int i=0; i< number.length(); i++) {
			if (!(Character.isDigit(number.charAt(i)))) {
				System.out.println("Incomplete. Must use whole numbers only\n");
				return false;
			}
		}
		return true;
	}
	public static void addToUserAccountTable(int accountId, String username) {
		try {
			adi.addToUserAccountTable(accountId, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int addAccountToGetId(Account account) {
		int id = 0;
		try {
			id = (int)adi.addAccount(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public static void deleteProfile(User user) {
		try {
			udi.deleteCustomerAndAccounts(user);
		} catch (CannotDeleteAccountException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean deleteAccountInDatabase(String accountId) {
		try {
			adi.deleteAccount(Integer.valueOf(accountId));
		} catch(CannotDeleteAccountException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!(isAccountInDatabase(accountId))) {
			return true;
		}
		return false;
	}
	public static int getNumberOfTotalUsers() {
		int total = 0;
		try {
			total = adi.getNumberOfTotalUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
}