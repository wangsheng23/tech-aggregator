package com.wsheng.aggregator.db;  
  
import java.sql.Connection;  
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
public class CRUD {  
  
    public static void main(String[] args) throws SQLException {  
        create();  
        retrieve();  
        update();  
        delete();  
    }  

	static void delete() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet resultset = null;

		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			
			// 3.创建语句
			st = conn.createStatement();
			
			// 4.执行语句
			String sql = "delete from user where id>5";
			int i = st.executeUpdate(sql);

			System.out.println("i=" + i);
		} finally {
			JdbcUtils.free(resultset, st, conn);
		}  
    }  
    
	static void update() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet resultset = null;

		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			
			// 3.创建语句
			st = conn.createStatement();
			
			// 4.执行语句
			String sql = "update user set money=money+20";
			int i = st.executeUpdate(sql);

			System.out.println("i=" + i);
		} finally {
			JdbcUtils.free(resultset, st, conn);
		}
	}

	static void create() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet resultset = null;

		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			
			// 3.创建语句
			st = conn.createStatement();
			
			// 4.执行语句
			String sql = "insert into user(name,birthday,money) values('wy','2011-09-23','2894656')";
			int i = st.executeUpdate(sql);

			System.out.println("i=" + i);
		} finally {
			JdbcUtils.free(resultset, st, conn);
		}
	}

	static void retrieve() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet resultset = null;

		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();
			
			// 4.执行语句
			resultset = st.executeQuery("select id,name,birthday,money from user");
			
			// 5.处理结果
			while (resultset.next()) {
				System.out.println(resultset.getObject("id"));
				System.out.println(resultset.getObject("name"));
				System.out.println(resultset.getObject("birthday"));
				System.out.println(resultset.getObject("money"));
			}

		} finally {
			JdbcUtils.free(resultset, st, conn);
		}
	}
}  
