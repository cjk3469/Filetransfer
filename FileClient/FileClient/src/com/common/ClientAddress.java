package com.common;

import java.net.InetSocketAddress;

public class ClientAddress extends InetSocketAddress {
	private static final long serialVersionUID = 1L;
	public ClientAddress(String hostname, int port) {
		super(hostname, port);
	}
	public int getPortnumber() {
		return super.getPort();
	}
}