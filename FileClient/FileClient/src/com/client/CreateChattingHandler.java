package com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import com.common.Protocol;
public class CreateChattingHandler implements ActionListener,ItemListener{
	private CreateChattingView ccView = null;
	private ClientSocket client = null;
	
	CreateChattingHandler(){
		
	}
	public void setInstance(CreateChattingView ccView,ClientSocket client) {
		this.ccView = ccView;
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		try {
			if (obj.equals(ccView.jbtn_create)) {
				if(ccView.selected_ID.size()==0) {
					JOptionPane.showMessageDialog(ccView.jp_center, "선택된 유저가 없습니다.", "메시지", JOptionPane.WARNING_MESSAGE);
				}else {
					String roomName = JOptionPane.showInputDialog("방 이름을 설정해주세요.");
					//채팅방이름 중복생성 check부분.
					boolean success = true;
					for(String room : ccView.serverRooms) {
						if(roomName.equals(room)) {
							JOptionPane.showMessageDialog(ccView.jp_center, "이미 존재하는 방이름 입니다. \n 다시 작성해주세요.");
							success = false;
							break;
						}
					}
					if(success) {//중복된 방이름 없을때.
						try {
							client.send(Protocol.createRoom,roomName
									,Protocol.myID,ccView.selected_ID.toString());
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							ccView.dispose();
						}
					}
				}
			}
			else if(obj.equals(ccView.jbtn_invite)) {
				if(ccView.selected_ID.size()==0) {
					JOptionPane.showMessageDialog(null, "선택된 유저가 없습니다.", "메시지", JOptionPane.WARNING_MESSAGE);
				}else {
					try {
						client.send(Protocol.inviteUser,ccView.roomName,ccView.selected_ID.toString());
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						ccView.dispose();
					}
				}
			}
		}//////////////////////end of try
			catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object obj = ie.getSource();
		if (ie.getStateChange() == ie.SELECTED) {
			ccView.selected_ID.add(((JCheckBox) ie.getSource()).getText()); // 체크박스의 값 들어가야함.
		}

		else if (ie.getStateChange() == ie.DESELECTED) {
			ccView.selected_ID.remove(((JCheckBox) ie.getSource()).getText()); // 체크박스의 값 들어가야함.
		}
		
	}

}
