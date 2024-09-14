package com.revature.bank.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.doaImpl.UserDaoImpl;
import com.revature.bank.util.Logging;

public class User {
	private UserDaoImpl udi = new UserDaoImpl();
	private int userId;
	private String firstName;
	private String lastName;
	private String ss;
	private String street;
	private String city;
	private String state;
	private int zip;
	private String username;
	private String password;
	private String type;
	private List<Account> accounts = new ArrayList<>();
	
	public User() {

	}
	public User(String firstName, String lastName, String ss, String street, String city, String state, int zip, 
			String username, String password, String type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ss = ss;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	public User(int userId, String firstName, String lastName, String ss, String street, String city, String state,
			int zip, String username, String password, String type) {	
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ss = ss;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	public int getUserId() {
		int id = userId;
		try {
			id = udi.getUserIdFromDatabase(this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userId = id;
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String field, String firstName) {
		try {
			udi.updateUserInfo(field, firstName, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.firstName = firstName;
		Logging.LogIt("info", this.type+", username = " +this.username+", has updated first name to " + this.firstName);
		System.out.println(" ");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String field, String lastName) {
		try {
			udi.updateUserInfo(field, lastName, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.lastName = lastName;
		Logging.LogIt("info", this.type+", username = " +this.username+", has updated last name to " + this.lastName);
		System.out.println(" ");
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String field, String street) {
		try {
			udi.updateUserInfo(field, street, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.street = street;
		Logging.LogIt("info", this.type+", username = " +this.username+", has updated stree address to " + this.street);
		System.out.println(" ");
	}
	public String getCity() {
		return city;
	}
	public void setCity(String field, String city) {
		try {
			udi.updateUserInfo(field, city, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.city = city;
		Logging.LogIt("info", this.type+", username = " +this.username+", has updated city to " + this.city);
		System.out.println(" ");
	}
	public String getState() {
		return state;
	}
	public void setState(String field, String state) {
		try {
			udi.updateUserInfo(field, state, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.state = state;
		Logging.LogIt("info", this.type+", username = " +this.username+", has updated state to " + this.state);
		System.out.println(" ");
	}
	public int getZip() {
		return zip;
	}
	public void setZip(String field, String zip) {
		try {
			udi.updateUserInfo(field, zip, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.zip = Integer.valueOf(zip);
		Logging.LogIt("info", this.type+", username = " +this.username+", has updated zipcode to " + this.zip);
		System.out.println(" ");
	}
	public String getSs() {
		return ss;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String field, String username) {
		try {
			udi.updateUserInfo(field, username, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.username = username;
		Logging.LogIt("info", this.type+", Id "+this.getUserId()+", username has been changed to "+this.username);
		System.out.println(" ");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String field, String password) {
		try {
			udi.updateUserInfo(field, password, this.username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.password = password;
		Logging.LogIt("info", this.type+", username = " +this.username+", password has been changed to "+this.password);
		System.out.println(" ");
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Account> getAccounts() {
		accounts.clear();
		try {
				accounts.addAll(udi.getAccountsByUsername(this.username));
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return accounts;
	}
	public void setAccount(List<Account> accounts) {
		this.accounts.addAll(accounts);
	}
	public void addAccountToCustomer(Account account) {
		this.accounts.add(account);
	}
	@Override
	public String toString() {
		return this.lastName + " " + this.firstName + " = " + this.username;
	}
	
}
