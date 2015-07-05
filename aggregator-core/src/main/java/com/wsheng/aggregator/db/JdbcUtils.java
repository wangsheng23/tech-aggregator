package com.wsheng.aggregator.db;  
  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  

/**
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 *
 */
public class JdbcUtils {  
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String user = "root";
	private static String password = "123";
	
	private static Connection connection;
 
    private JdbcUtils() {  
    	
    }  
    
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
 
	public static synchronized Connection getConnection() throws SQLException {
		if (connection != null) {
			connection = DriverManager.getConnection(url, user, password);
		} 
		return connection;
	}

	public static void free(ResultSet resultset, Statement st, Connection conn) {
		// 6. 释放资源
		try {
			if (resultset != null)
				resultset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	} 
}  