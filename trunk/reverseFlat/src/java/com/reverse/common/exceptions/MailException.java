package com.reverse.common.exceptions;

//import com.google.gwt.user.client.rpc.SerializableException;

@SuppressWarnings("deprecation")
public class MailException extends Exception { //SerializableException {

	private static final long serialVersionUID = 1L;

	public MailException() {
	}

	public MailException(String msg) {
		super(msg);
	}

}
