package com.oracle.test;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test {
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String user = "scott";
	private static final String pass = "tiger";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner sc =  new Scanner(System.in);
		System.out.println("请输入用户名");
		String name = sc.next();
		System.out.println("请输入日期");
		String password = sc.next();
//		System.out.println("请输入你要修改的用户名");
//		String newname = sc.next();
//		System.out.println("请输入你要修改的密码");
//		String newpasswordString = sc.next();
		//1.加载驱动
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.创建连接
		Connection conm = DriverManager.getConnection(url, user, pass);
		//3.创建执行对象
		
		String sql = "insert into www values (?,to_date(?,'yyyy-mm-dd'))";
		//String DELsql = "delete from usertest where name = ? and password = ? ";
//		String Upsql = "update www set name =? ,password = ? where name = ? and password = ?";
		String Selsql = "select * from www";
		PreparedStatement ps = conm.prepareStatement(sql); //预处理
//		PreparedStatement ps1 = conm.prepareStatement(Selsql);
		
		
		//给ps传值
		ps.setString(1, name);
		ps.setString(2, password);
//		ps.setString(1, newname);
//		ps.setString(2, newpasswordString);
		
//		Statement s = conm.createStatement();
		//4.执行
		
		int result = ps.executeUpdate();
		System.out.println(result);
		if(ps.execute(Selsql)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name")+"\t"+rs.getString("password"));
			}
		}
		
		
		//5.释放资源
		ps.close();
		conm.close();
		
	}
}
