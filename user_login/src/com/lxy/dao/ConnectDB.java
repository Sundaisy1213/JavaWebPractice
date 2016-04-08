package com.lxy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	//���ݿ�����
	public static Connection getConnection(){
		Connection conn = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//���ݿ�����url
			String url = "jdbc:mysql://localhost:3306/db_test1";
			//��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}		
	}	
}
