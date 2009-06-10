package com.reverseFlat.common.exceptions;

//import com.google.gwt.user.client.rpc.SerializableException;

@SuppressWarnings("deprecation")
public class LoginException extends Exception { //SerializableException {

	private static final long serialVersionUID = 1L;

	public LoginException() {
	}

	public LoginException(String msg) {
		super(msg);
	}

}
