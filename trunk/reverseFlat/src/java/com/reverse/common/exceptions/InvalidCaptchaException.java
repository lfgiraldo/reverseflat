package com.reverse.common.exceptions;

//import com.google.gwt.user.client.rpc.SerializableException;

@SuppressWarnings("deprecation")
public class InvalidCaptchaException extends Exception{  //extends SerializableException {

	private static final long serialVersionUID = 1L;

	public InvalidCaptchaException() {
	}

	public InvalidCaptchaException(String msg) {
		super(msg);
	}

}
