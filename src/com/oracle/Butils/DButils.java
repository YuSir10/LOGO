package com.oracle.Butils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButils {
	private final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private final static String user = "scott";
	private final static String password = "tiger";
	
	private Connection conm = null;
	private PreparedStatement ps = null;
	private ResultSet re = null;
	
	
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int preUpdate(String sql,Object ...arr) throws SQLException {
		int no = 0;
		conm = DriverManager.getConnection(url, user, password);
		ps = conm.prepareStatement(sql);
		if(arr != null) {
			for (int i = 0; i < arr.length; i++) {
				ps.setObject(i+1, arr[i]);
			}
		}
		no = ps.executeUpdate();
		return no ;
		
	}
	
	public ResultSet preQuery(String sql,Object ...arr) {
		try {
			conm = DriverManager.getConnection(url,user,password);
			ps = conm.prepareStatement(sql);
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					ps.setObject(i+1, arr[i]);
				}
			}
			re = ps.executeQuery();
			return re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return re;
		
	}
	
	public  void close() {
		// TODO Auto-generated method stub
		try {
			ps.close();
			conm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
