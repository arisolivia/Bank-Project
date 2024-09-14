package com.revature.bank.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bank.bean.Account;
import com.revature.bank.bean.User;
import com.revature.bank.util.Verify;

public class VerifyTest {
	static Account acc;
	static User cus;
	
	@BeforeClass
	public static void makeConstructorsToTest() {
		acc = new Account("savings", "employee1");
		cus = new User();
		cus.setUsername("username", "employee1");
	}
	@Test
	public void getCustomersTest() {
		Verify.getCustomers();
		assertEquals(1, Verify.customerList.size());
	}
	@Test
	public void isAccountInDatabaseTest() {
		assertFalse(Verify.isAccountInDatabase("5562"));
	}
	@Test
	public void findAccountTest() {
		assertNull(Verify.findAccount("45623"));
	}
	@Test
	public void isUsernameInDatabaseTest() {
		assertTrue(Verify.isUsernameInDatabase("employee1"));
		assertFalse(Verify.isUsernameInDatabase("admin"));
	}
	@Test
	public void findUserTest() {
		assertNull(Verify.findUser("the"));
	}
	@Test
	public void verifyTypeTest() {
		assertEquals("SAVINGS", Verify.verifyType("saVings"));
		assertNull(Verify.verifyType("saVes"));
	}
	@Test
	public void verifyNumTest() {
		assertTrue(Verify.verifyNum("52"));
		assertFalse(Verify.verifyNum("5.2"));
		assertFalse(Verify.verifyNum("-52"));
		assertFalse(Verify.verifyNum("5w2"));
	}
	@Test
	public void getNumberOfTotalUsersTest() {
		assertEquals(1, Verify.getNumberOfTotalUsers());
	}

}