package com.client;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.common.Protocol;

public class DefaultView extends JFrame {
	DefHandler defHandler = null;
	
	JTabbedPane jtap = new JTabbedPane();
	//유저목록 패널
	JLabel firstjlb = new JLabel("유저목록", SwingConstants.CENTER);
	JPanel firstjp = new JPanel();
	JPanel jp_north = new JPanel();
	JLabel jlb_name = new JLabel(); // 사용자이름
	// 중단1
	JPanel jp_online = new JPanel();
	JLabel jlb_online = new JLabel("온라인");
	String online[] = { "아이디" };
	DefaultTableModel dtm_online = new DefaultTableModel(online,0){
		@Override //셀 더블클릭 후 수정 안되도록 조정.
		public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
			return false;
		}
	};
	JTable jtb_online = new JTable(dtm_online);
	JScrollPane jsp_online = new JScrollPane(jtb_online);
	// 중단2
	JPanel jp_offline = new JPanel();
	JLabel jlb_offline = new JLabel("오프라인");
	String offline[] = { "아이디" };
	DefaultTableModel dtm_offline = new DefaultTableModel(offline,0){
		@Override //셀 더블클릭 후 수정 안되도록 조정.
		public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
			return false;
		}
	};
	JTable jtb_offline = new JTable(dtm_offline);
	JScrollPane jsp_offline = new JScrollPane(jtb_offline);
	// 하단
	JPanel jp_south = new JPanel();
	
	JButton jbtn_chat = new JButton("방 만들기");
	JButton jbtn_logout = new JButton("로그아웃");
	
	//방목록 패널
	JLabel secondjlb = new JLabel("방목록", SwingConstants.CENTER);
	JPanel secondjp = new JPanel();
	//방목록
	JPanel jp_room = new JPanel();
	JLabel jlb_room = new JLabel("방목록");
	String room[] = { "방" };
	DefaultTableModel dtm_room = new DefaultTableModel(room,0){
		@Override //셀 더블클릭 후 수정 안되도록 조정.
		public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
			return false;
		}
	};
	JTable jtb_room = new JTable(dtm_room);
	JScrollPane jsp_room = new JScrollPane(jtb_room);
	//하단
	JPanel jp_south2= new JPanel();
	JButton jbtn_enter = new JButton("입장하기");
	
	public DefaultView(ClientSocket client) {
		defHandler = new DefHandler(this,client);
		jlb_name.setText(Protocol.myID+"님 환영합니다.");
		initDisplay();
	}
	
	private void initDisplay() {
		this.add(jtap);
		this.addWindowListener(defHandler);
		
		firstjp.add(firstjlb);
		jtap.addTab("유저목록", new UserList());
		

		secondjp.add(secondjlb);
		jtap.addTab("방목록", new RoomList());

		setTitle(Protocol.myID);
		setBounds(650, 200, 500, 600);
		setVisible(true);
	}
	
	class UserList extends JPanel{
		public UserList() {
			jp_north.setBounds(0, 20, 500, 40);
			jp_online.setBounds(0, 60, 500, 200);
			jp_offline.setBounds(0, 280, 500, 200);
			jp_south.setBounds(0, 500, 500, 40);
			/////////////////
			jlb_name.setFont(new Font("맑은고딕", Font.BOLD, 15));
			jp_north.add(jlb_name);
			
			// 중단
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
			dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
			TableColumnModel tcm = jtb_online.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
			for (int i = 0; i < tcm.getColumnCount(); i++) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
			tcm = null;
			tcm = jtb_offline.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
			for (int i = 0; i < tcm.getColumnCount(); i++) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
			
			jp_online.add(jlb_online);
			jp_online.add(jsp_online);
			jp_offline.add(jlb_offline);
			jp_offline.add(jsp_offline);
			//jtb_online.addMouseListener(null);
			//jtb_offline.addMouseListener(null);

			// 하단
			jbtn_chat.addActionListener(defHandler);
			
			jbtn_logout.addActionListener(defHandler);
			jp_south.add(jbtn_chat);
			jp_south.add(jbtn_logout);

			// 프레임
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JFrame.setDefaultLookAndFeelDecorated(true);
			setLayout(null);
			add(jp_north);
			add(jp_online); // 온라인 테이블 적용
			add(jp_offline); // 오프라인 테이블 적용
			add(jp_south);

		}
	}
	
	class RoomList extends JPanel{
		public RoomList() {
			jp_room.setBounds(0, 60, 500, 400);
			jp_south2.setBounds(0, 500, 500, 40);
			
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
			dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
			TableColumnModel tcm = jtb_room.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
			for (int i = 0; i < tcm.getColumnCount(); i++) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
			jp_room.add(jlb_room);
			jp_room.add(jsp_room);
			
			jbtn_enter.addActionListener(defHandler);			
			jp_south2.add(jbtn_enter);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JFrame.setDefaultLookAndFeelDecorated(true);
			setLayout(null);
			add(jp_room);
			add(jp_south2);
		
		}
	}
}
