package com.revature.bank.menu;

import java.util.Scanner;

import com.revature.bank.bean.*;
import com.revature.bank.util.Verify;

public class OptionMenu {
	static Scanner input = new Scanner(System.in);
	
	public static void startup() {
		System.out.println("Welcome to Bank of the People\n");
		System.out.println("Select from options:\n");
		System.out.println("\t[1] Login");
		System.out.println("\t[2] Register as new customer");
		System.out.println("\t[3] Quit\n");
		String answer = input.nextLine();
		switch (answer) {
		case "1": MenuMethod.login(); startup(); break;
		case "2": MenuMethod.register("CUSTOMER"); startup(); break;
		case "3": System.exit(0);
		default: System.out.println("Please enter a 1, 2, or 3.\n"); startup();
		}
	}
	public static void customerMenu(User customer) {
		System.out.println("Hello " + customer.getFirstName() + ", please select from options:\n");
		System.out.println("\t[1] View account");
		System.out.println("\t[2] Open new account");
		System.out.println("\t[3] View/update profile");
		System.out.println("\t[4] Close an account");
		System.out.println("\t[5] Back to previous menu");
		System.out.println("\t[6] Log out\n");
		String answer = input.nextLine();
		switch (answer) {
		case "1":
			if (customer.getAccounts().size() == 0) {
				System.out.println("No accounts avaiable. Please open new account.\n");
			} else {
				System.out.println("Your accounts are " + customer.getAccounts()+". Enter the ID number of the account.");
				answer = input.nextLine();
				//check if account exist
				if (Verify.isAccountInDatabase(answer)) {
					accountMenu(Verify.findAccount(answer), customer);
				}
			}
			customerMenu(customer); break;
		case "2": 
			MenuMethod.newAccount(customer); 
			customerMenu(customer); 
			break;
		case "3": 
			MenuMethod.viewProfile(customer);
			MenuMethod.updateProfile(customer);
			MenuMethod.viewProfile(customer);
			customerMenu(customer); 
			break;
		case "4": 
			MenuMethod.cancelAccount(); 
			customerMenu(customer); 
			break;
		case "5": break;
		case "6": MenuMethod.logOut(customer);
		default: System.out.println("Please enter 1, 2, 3, 4, 5, or 6.\n"); customerMenu(customer);
		}
	}
	public static void employeeMenu(User employee) {
		System.out.println("Select from options:\n");
		System.out.println("\t[1] View all user profiles");
		System.out.println("\t[2] Enter into customer account");
		System.out.println("\t[3] View/update customer or employee profile");
		System.out.println("\t[4] Register new employee or customer");
		System.out.println("\t[5] Delete customer profile");
		System.out.println("\t[6] Cancel account");
		System.out.println("\t[7] Back to previous menu");
		System.out.println("\t[8] Log out\n");
		String answer = input.nextLine();
		switch (answer) {
		case "1": 
			MenuMethod.viewAllUsersInfo(); 
			employeeMenu(employee); 
			break;
		case "2":
			System.out.println("Enter account Id");
			answer = input.nextLine();
			if (Verify.isAccountInDatabase(answer)) {
				accountMenu(Verify.findAccount(answer), employee);
			}
			employeeMenu(employee); break;
		case "3": 
			System.out.println("Enter username");
			answer = input.nextLine();
			if (Verify.isUsernameInDatabase(answer)) {
				MenuMethod.updateProfile(Verify.findUser(answer)); 
			}
			employeeMenu(employee); break;
		case "4":
			System.out.println("Enter type of user.");
			answer = input.nextLine();
			if (answer.equalsIgnoreCase(Verify.verifyType(answer))) {
				MenuMethod.register(Verify.verifyType(answer));
			}
			employeeMenu(employee); break;
		case "5": 
			System.out.println("Enter username of profile to be deleted.");
			answer = input.nextLine();
			if (Verify.isUsernameInDatabase(answer)) {
				Verify.deleteProfile(Verify.findUser(answer)); 
			}
			employeeMenu(employee); break;
		case "6": MenuMethod.cancelAccount(); employeeMenu(employee); break;
		case "7": break;
		case "8": MenuMethod.logOut(employee);
		default: System.out.println("Please enter 1, 2, 3, 4, 5, 6, 7, or 8.\n"); employeeMenu(employee);
		}
	}
	public static void accountMenu(Account account, User user) {
		System.out.println("Select from options:\n");
		System.out.println("\t[1] Withdraw");
		System.out.println("\t[2] Deposit");
		System.out.println("\t[3] View account balance");
		System.out.println("\t[4] View transaction history");
		System.out.println("\t[5] Back to previous menu\n");
		String answer = input.nextLine();
		switch (answer) {
		case "1": 
			System.out.println("Enter amount to withdraw. Enter amount without decimals (e.g 100.00 = 10000).");
			answer = input.nextLine();
			account.withdraw(answer, user);
			transactionMenu(account, user); 
			break;
		case "2": 
			System.out.println("Enter amount to deposit. Enter amount without decimals (e.g 100.00 = 10000).");
			answer = input.nextLine();
			account.deposit(answer, user);
			transactionMenu(account, user); 
			break;
		case "3":
			System.out.println("Your balance is $" + account.getBalance()+"\n");
			transactionMenu(account,user); 
			break;
		case "4": 
			for(Transaction t: account.getTransactions()) {
				System.out.println(t);
			}
			transactionMenu(account, user); break;
		case "5": break;
		default: System.out.println("Please enter 1, 2, 3, 4 or 5.\n"); accountMenu(account, user);
		}
	}
	public static void transactionMenu(Account account, User user) {
		System.out.println("Would you like to make another transaction in this account? y/n");
		String answer = input.nextLine();
		switch (answer.toLowerCase()) {
		case "y": accountMenu(account, user); break;
		case "n": break;
		default: System.out.println("Please enter [y] for yes or [n] for no."); transactionMenu(account, user);
		}
	}
}