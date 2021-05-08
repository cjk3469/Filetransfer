package com.common;
//자동 정렬 Ctrl+Shift+F
import java.io.IOException;

import com.server.FileSocket;

public interface FileServerListener {
	public void clientConnection(FileSocket client);
	public void connectionError(IOException e);
	public void connectionClose();
}
