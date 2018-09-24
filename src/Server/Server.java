package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import bean.Environment;
import oracle.net.aso.t;
import util.LogUtil;

public class Server {
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Server() throws IOException {
		try {
			LogUtil.LOGGER.info("��ʼ��������...");
			serverSocket = new ServerSocket(8088);
			LogUtil.LOGGER.info("��ʼ����������ϣ�");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// �������������������ݣ��������ݼ��϶���
	public void start() {
		Socket socket = null;
		try {
			while (true) {
				LogUtil.LOGGER.info("�ȴ��ͻ�����...");
				socket = serverSocket.accept();
				// ��ȡ�ͻ���ip��ַ
				InetAddress address = socket.getInetAddress();
				// ��ȡ�ͻ��˶˿ں�
				int port = socket.getPort();
				LogUtil.LOGGER.info("�ͻ���" + address + ":" + port + "������!");
                
				//�������������ӽ����Ŀͻ��˵��߳�
				new Thread(new ClientHandleThread(socket)).start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//�����������������ͻ��ˣ���������
		try {
			Server server = new Server();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
