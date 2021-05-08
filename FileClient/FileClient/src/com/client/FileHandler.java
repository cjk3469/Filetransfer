package com.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JLabel;

public class FileHandler implements MouseListener{
	
	ClientSocket client = null;
	String roomName = null;
	String fileName = null;
	JLabel jlb_file= null;
	
	public FileHandler(ClientSocket client, String roomName, 
							String fileName, JLabel jlb_file) {
		this.client = client;
		this.roomName = roomName;
		this.fileName = fileName;
		this.jlb_file = jlb_file;
		System.out.println(client);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			client.receive(roomName, fileName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		jlb_file.setText("<html><a href=''>" + fileName + "</a></html>");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		jlb_file.setText(fileName);		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
