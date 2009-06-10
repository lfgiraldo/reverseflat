package com.reverseFlat.ejb.exception;

public class PayPalServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PayPalServiceException() {
	}

	public PayPalServiceException(String msg) {
		super(msg);
	}

}
