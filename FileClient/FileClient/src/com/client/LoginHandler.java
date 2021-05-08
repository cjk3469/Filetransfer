package com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.common.Protocol;

public class LoginHandler implements ActionListener{
	private LoginView logView = null;
	private ClientSocket client = null;
	
	public LoginHandler(ClientSocket client, LoginView logView){
		this.client = client;
		this.logView = logView;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		try {
			if (obj == logView.jbtn_login || obj == logView.jtf_pw) {
				String id = logView.jtf_id.getText();
				String pw = logView.jtf_pw.getText();
				if(id.equals("")||pw.equals("")) {
					JOptionPane.showMessageDialog(logView, "빈칸이 있습니다.");
				}
				else{
					Protocol.myID = logView.jtf_id.getText();
					client.send(Protocol.checkLogin, logView.jtf_id.getText(), logView.jtf_pw.getText());
				}
			} else if (obj.equals(logView.jbtn_join)) {
				client.send(Protocol.addUserView);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
