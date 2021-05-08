package com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.common.Protocol;

public class AddUserHandler implements ActionListener{
	private AddUserView addView = null;
	private ClientSocket client = null;
	
	AddUserHandler(AddUserView addView,ClientSocket client){
		this.addView = addView;
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		try { 
				if(obj == addView.jbtn_join|| obj == addView.jtf_pw) {
				String new_id = addView.jtf_id.getText();
				String new_pw = addView.jtf_pw.getText();
				String new_name = addView.jtf_name.getText();
				if(new_id.equals("")||new_pw.equals("")|new_name.equals("")) {
					JOptionPane.showMessageDialog(addView, "빈칸이 있습니다.");
				}else {
					client.send(Protocol.addUser, new_id, new_pw, new_name);					
				}

				}else {
					
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
