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

	// 初始化服务器
	public Server() throws IOException {
		try {
			LogUtil.LOGGER.info("初始化服务器...");
			serverSocket = new ServerSocket(8088);
			LogUtil.LOGGER.info("初始化服务器完毕！");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 启动服务器，接收数据，返回数据集合对象
	public void start() {
		Socket socket = null;
		try {
			while (true) {
				LogUtil.LOGGER.info("等待客户连接...");
				socket = serverSocket.accept();
				// 获取客户端ip地址
				InetAddress address = socket.getInetAddress();
				// 获取客户端端口号
				int port = socket.getPort();
				LogUtil.LOGGER.info("客户端" + address + ":" + port + "已连接!");
                
				//创建处理新连接进来的客户端的线程
				new Thread(new ClientHandleThread(socket)).start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//启动服务器，监听客户端，处理数据
		try {
			Server server = new Server();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
