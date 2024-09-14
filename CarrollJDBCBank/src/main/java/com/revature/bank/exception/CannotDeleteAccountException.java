package com.revature.bank.exception;

public class CannotDeleteAccountException extends RuntimeException {

	public CannotDeleteAccountException(String errorMessage) {
		super(errorMessage);
	}
}
