package com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import com.common.Protocol;

public class DefHandler implements ActionListener, WindowListener{
	private DefaultView defView = null;
	private ClientSocket client = null;
	
	DefHandler(DefaultView defView,ClientSocket client){
		this.defView = defView;
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		try {
			if (obj.equals(defView.jbtn_chat)) {
				client.send(Protocol.createRoomView, Protocol.myID);
			}
			// 로그아웃
			else if (obj.equals(defView.jbtn_logout)) {
				client.send(Protocol.logout, Protocol.myID);
				System.exit(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent we) {
		try {
			client.send(Protocol.logout, Protocol.myID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
