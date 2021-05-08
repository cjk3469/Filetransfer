package com.client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Stack;

import com.common.ClientAddress;
import com.common.Protocol;

//전송관련 메소드를 전부 담당하는 클래스
public class ClientSocket extends Socket{
	private ClientAddress fileAddress = null;
	private FileSocket file = null;
	/////////////////////////////////////////////////
	private ClientAddress chatAddress = null;
	public ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;
	private Stack<Exception> errorList = null;
	ClientThread thread = null;
	
	public ClientSocket(ClientAddress chat, ClientAddress file) throws IOException {
		this.chatAddress = chat;
		this.fileAddress = file;
		connection();
	}
	
	/**
	 * 서버 접속 메소드
	 */
	private void connection() throws IOException {
		super.connect(chatAddress);
		oos = new ObjectOutputStream(getOutputStream());
		ois = new ObjectInputStream(getInputStream());
		thread = new ClientThread(this);
		thread.start();
		//구분을 줘서 서버연결 성공 시 쓰레드 실행, 서버 연결 불가시 메세지 출력
	}
	/**
	 *  요청 전송 메소드
	 *  ProtocolNumber, String 입력 시 자동 전송
	 */
	public void send(String... str) throws IOException {
		String msg = "";
		for(int i=0;i<str.length;i++) {
			if(i==str.length-1) 
				msg = msg+str[i];
			else 
				msg = msg+str[i]+Protocol.seperator;				
		}
		System.out.println("샌드 메소드"+msg);
		oos.writeObject(msg);
	}
	/**
	 *  파일 전송 메소드
	 * @throws IOException 
	 */
	public void send(String roomName, File sendPath) throws IOException {
		file = new FileSocket(fileAddress, sendPath);//파일전송 소켓으로 접속.
		file.sendFile(roomName, sendPath);//파일전송 Thread 실행.
	}
	/**
	 * 파일 수신 메소드
	 * @throws IOException 
	 */
	public void receive(String roomName, String fileName) throws IOException {
		//서버에 파일 수신 요청 내 채팅룸폴더, 가져올 파일 전송
		File savePath = new File("C:\\DownloadFile");
		if(!savePath.exists()) {
			savePath.mkdirs();
		}
		file = new FileSocket(fileAddress, savePath);
		file.receiveSend(roomName, fileName);
	}
	
	public void close() throws IOException {
		//프로세스 죽이기 or 메인스레드를 죽이거나......
		thread.interrupt();
		close();
		System.exit(0);
	}
}
