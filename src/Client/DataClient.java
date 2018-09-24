package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

import bean.Environment;
import util.LogUtil;

public class DataClient {

	private Socket socket;

	// 初始化客户端，连接服务器
	public DataClient() throws Exception {
		try {
			LogUtil.LOGGER.info("正在连接服务器...");
			socket = new Socket("localhost", 8088);
			LogUtil.LOGGER.info("已成功连接服务器！");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 启动客户端，将数据集合对象序列化到网络中，并发送给服务器
	public void start(Collection<Environment> list) {
		try {
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			LogUtil.LOGGER.info("正在发送数据...");
			oos.writeObject(list);
			LogUtil.LOGGER.info("数据发送完毕！");
		} catch (IOException e) {
			LogUtil.LOGGER.error(e.getMessage()+" : IOException");
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		//启动客户端，发送数据
        try {
			Collection<Environment> list = Gather.analytic(new File("radwtmp"));
			DataClient client = new DataClient();
			client.start(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
