package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static String url;
	private static String user;
	private static String password;
	private static Connection conn;
	private static PreparedStatement ps;
	
	//��̬�飺��ȡ�����ļ�����������
	static {
		try {
			Properties prop = new Properties();
			//��ȡ�����ļ� 
			InputStream fis = DBUtil.class.getClassLoader().getResourceAsStream("util\\DBConfig.properties");
			
			prop.load(fis);
			fis.close();
			
			//��ȡ�����ļ���Ϣ
			String driver = prop.getProperty("driver").trim();
			url = prop.getProperty("url").trim();
			user = prop.getProperty("user").trim();
			password = prop.getProperty("password").trim();
			
			//ע������
			Class.forName(driver);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ����
	public static Connection getConnection() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	//�ضϱ�
	public static void trunTable() {
		try {
			conn = getConnection();
			for (int i = 1;i <= 31;i++) {
				String sql = "truncate table e_detail_" + i;
				ps = conn.prepareStatement(sql);
				ps.execute();
			}
			System.out.println("������ɾ����ϣ�");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				DBUtil.closeConnection(conn);
			}
		}
	}
	
	//��ѯ������
	public static void selectCount() {
		try {
			conn = getConnection();
			int sum = 0;
			for (int i = 1;i <= 31;i++) {
				String sql = "select count(*) from e_detail_" + i;
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				rs.next();
				int count = rs.getInt(1);
				System.out.println("��"+i+"�칲��"+count+"������");
				sum += count;
			}
			System.out.println("�������ݣ�"+sum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				DBUtil.closeConnection(conn);
			}
		}
	}
	
	//�ر�����
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
