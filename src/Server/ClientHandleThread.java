package Server;
/**
 * 线程功能：服务器接收客户端传来的数据，并录入数据库
 * @author liangyumim
 * @created 2018年9月24日 下午1:19:02
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import bean.Environment;
import util.DBUtil;
import util.LogUtil;

public class ClientHandleThread implements Runnable {
	private Socket socket;
	private Collection<Environment> list;
	private Connection conn;
	private PreparedStatement ps;

	public ClientHandleThread(Socket socket) {
		super();
		this.socket = socket;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			// 接收数据
			LogUtil.LOGGER.info("正在接收数据...");
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			list = (Collection<Environment>) ois.readObject();
			if (list.size() == 98480) {
				LogUtil.LOGGER.info("数据接收成功！");
			} else {
				LogUtil.LOGGER.info("数据接收失败！");
			}

			// 录入数据
			conn = DBUtil.getConnection();
			long start = System.currentTimeMillis();
			LogUtil.LOGGER.info("正在录入数据...");
			for (Environment en : list) {
				int day = Integer.parseInt((en.getGather() + "").substring(8, 10));
				String sql = "insert into e_detail_" + day + " values(?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, en.getName());
				ps.setString(2, en.getSrcId());
				ps.setString(3, en.getDstId());
				ps.setString(4, en.getSersorAddress());
				ps.setInt(5, en.getCount());
				ps.setString(6, en.getCmd());
				ps.setInt(7, en.getStatus());
				ps.setFloat(8, en.getData());
				ps.setTimestamp(9, en.getGather());
				
				ps.addBatch();
				ps.executeBatch();
				ps.clearBatch();
				ps.close();
			}
			long end = System.currentTimeMillis();
			LogUtil.LOGGER.info("数据已录入完毕！");
			LogUtil.LOGGER.info("共计用时：" + (end - start) / 1000 + "秒。");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
					DBUtil.closeConnection(conn);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
