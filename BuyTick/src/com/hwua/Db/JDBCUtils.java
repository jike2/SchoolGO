package com.hwua.Db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	
//	public static Connection getConnection() {
//		Connection con=null ;
//			try {
//				//加载驱动
//				Class.forName("com.mysql.jdbc.Driver");
//				//建立连接
//				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/person",
//						"root","123");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		return con;
//	}
	private static String DriverClass=null;
	private static String URL=null;
	private static String userName=null;
	private static String passWord=null;
	private static final ThreadLocal thread_local_db = new ThreadLocal();
	static {
		try {
			Properties ps = new Properties();
			String path = JDBCUtils.class.getClassLoader().getResource("").toURI().getPath();
			InputStream is = new FileInputStream(path+"jdbc.properties");
			ps.load(is);
			DriverClass = ps.getProperty("driverClass");
			URL = ps.getProperty("url");
			userName = ps.getProperty("userName");
			passWord = ps.getProperty("passWord");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	//获取链接
	public static Connection getConn() {
		Connection conn = (Connection) thread_local_db.get();
		try {
			if(conn == null) {
				Class.forName(DriverClass);
				conn = DriverManager.getConnection(URL,userName,passWord);
				thread_local_db.set(conn);//把链接对象放入线程池
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	//关闭资源
	public static void release(Connection conn,Statement st,ResultSet rs) {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(st !=null) {
				st.close();
			}
			if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs=null;
			st=null;
			conn=null;
			
		}
	}
}
