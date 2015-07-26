/**
 * 
 */
package com.wsheng.aggregator.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class C3p0PoolThread extends Thread {

	@Override
	public void run() {
		C3p0ConnectionPool pool = C3p0ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = pool.getConnection();
			stmt = connection.prepareStatement("select sysdate() as nowtime from dual");
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(Thread.currentThread().getId() + "---------------开始" + rs.getString("nowtime"));  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getId() + "--------结束");  
		
	}

	
}
