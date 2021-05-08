package com.client;

import java.io.File;
import java.io.IOException;

import com.common.ClientAddress;

public class ClientMain {

	public static void main(String[] args) {
		try {
			ClientAddress chatAddress = new ClientAddress("edc3665.iptime.org", 9100);
			ClientAddress fileAddress = new ClientAddress("edc3665.iptime.org", 9101);
			ClientSocket client = new ClientSocket(chatAddress, fileAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} 
