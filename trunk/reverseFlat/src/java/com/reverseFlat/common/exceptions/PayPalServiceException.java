package com.reverseFlat.common.exceptions;

//import com.google.gwt.user.client.rpc.SerializableException;

@SuppressWarnings("deprecation")
public class PayPalServiceException extends Exception { // SerializableException {

	private static final long serialVersionUID = 1L;

	public PayPalServiceException() {
	}

	public PayPalServiceException(String msg) {
		super(msg);
	}

}
