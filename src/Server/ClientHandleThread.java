package Server;
/**
 * �̹߳��ܣ����������տͻ��˴��������ݣ���¼�����ݿ�
 * @author liangyumim
 * @created 2018��9��24�� ����1:19:02
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
			// ��������
			LogUtil.LOGGER.info("���ڽ�������...");
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			list = (Collection<Environment>) ois.readObject();
			if (list.size() == 98480) {
				LogUtil.LOGGER.info("���ݽ��ճɹ���");
			} else {
				LogUtil.LOGGER.info("���ݽ���ʧ�ܣ�");
			}

			// ¼������
			conn = DBUtil.getConnection();
			long start = System.currentTimeMillis();
			LogUtil.LOGGER.info("����¼������...");
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
			LogUtil.LOGGER.info("������¼����ϣ�");
			LogUtil.LOGGER.info("������ʱ��" + (end - start) / 1000 + "�롣");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
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
