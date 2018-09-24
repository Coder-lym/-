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
	
	//静态块：读取配置文件，加载驱动
	static {
		try {
			Properties prop = new Properties();
			//读取配置文件 
			InputStream fis = DBUtil.class.getClassLoader().getResourceAsStream("util\\DBConfig.properties");
			
			prop.load(fis);
			fis.close();
			
			//获取配置文件信息
			String driver = prop.getProperty("driver").trim();
			url = prop.getProperty("url").trim();
			user = prop.getProperty("user").trim();
			password = prop.getProperty("password").trim();
			
			//注册驱动
			Class.forName(driver);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接
	public static Connection getConnection() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	//截断表
	public static void trunTable() {
		try {
			conn = getConnection();
			for (int i = 1;i <= 31;i++) {
				String sql = "truncate table e_detail_" + i;
				ps = conn.prepareStatement(sql);
				ps.execute();
			}
			System.out.println("数据已删除完毕！");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				DBUtil.closeConnection(conn);
			}
		}
	}
	
	//查询表数据
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
				System.out.println("第"+i+"天共有"+count+"条数据");
				sum += count;
			}
			System.out.println("共计数据："+sum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				DBUtil.closeConnection(conn);
			}
		}
	}
	
	//关闭连接
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
