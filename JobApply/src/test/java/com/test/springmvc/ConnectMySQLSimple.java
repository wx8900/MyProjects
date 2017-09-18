package com.test.springmvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQLSimple {

	public static void main(String[] args) throws ClassNotFoundException {
		ConnectMySQLSimple cm = new ConnectMySQLSimple();
		System.out.println("000000");
		cm.print();
		System.out.println("=========================="+getConnection());
		System.out.println("555555");
	}
	
	public void print() {
		System.out.println("2222233333");
	}
	
	
	public static Connection getConnection() throws ClassNotFoundException{
		
		String url="jdbc:mysql://localhost:3306/JobApply";//我连的数据库是MySQL中的jdbc数据库
		String username="root";
		String password="123456";//我的MySQL数据库的密码是空字符串
		String driverClass="com.mysql.jdbc.Driver";
		Connection ct=null;
		Class.forName(driverClass);
		try {
			ct=DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

}
