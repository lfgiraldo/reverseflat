package com.reverseFlat.common.exceptions;

//import com.google.gwt.user.client.rpc.SerializableException;

@SuppressWarnings("deprecation")
public class BidException extends Exception { //extends SerializableException {

	private static final long serialVersionUID = 1L;

	public BidException() {
	}

	public BidException(String msg) {
		super(msg);
	}

}
