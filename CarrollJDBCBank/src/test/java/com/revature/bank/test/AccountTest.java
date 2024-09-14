package com.revature.bank.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bank.bean.Account;
import com.revature.bank.bean.User;

public class AccountTest {
	public static Account test;
	public static Account test2;
	public static User user;

	@BeforeClass
	public static void makeConstructorsToTest() {
		test = new Account("savings","employee1");
		test2 = new Account("checking","employee1");
		user = new User();
		user.setUsername("username", "user");
	}
	@Test
	public void setBalanceTest() {
		test.setBalance(100.0);
		assertEquals(100.0, test.getBalance(),0);
	}
	@Test
	public void withdrawTest() {
		test2.setBalance(100.0);
		test2.withdraw("1000", user);
		assertEquals(90.0, test2.getBalance(),0);
		
	}
	@Test
	public void depositTest() {
		test.deposit("2000", user);
		assertEquals(20.0, test.getBalance(),0);
	}

}