package com.oracle.Butils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws SQLException {
		DButils db = new DButils();
		ResultSet re = null;
		String sql = "select * from sosouser";
		re = db.preQuery(sql);
		System.out.println("员工编号"+"\t"+"员工姓名"+"\t"+"员工职位");
		while(re.next()) {
			System.out.println("员工编号"+"\t"+"员工姓名"+"\t"+"员工职位");
			System.out.println("\t"+re.getString("cardnumber")+"\t"+re.getString("name")+"\t"+re.getString("password")+"\t"+re.getDouble(""));
		}
		db.close();
	}
}
