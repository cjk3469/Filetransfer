package com.common;

public class UserException extends Exception{
	private int port = 7000;
	public UserException() {}
	public UserException(String msg) {
		super(msg);
		
	}
	public UserException(String msg, int port) {
		super(msg);
		//super(port);
		
	}
	
	
	
	public int getPort() {
		return port;
	}
}
