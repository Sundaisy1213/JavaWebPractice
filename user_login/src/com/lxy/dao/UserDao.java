package com.lxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lxy.User;

public class UserDao {
	//����û�����user
	public void saveUSer(User user){
		Connection conn = ConnectDB.getConnection();
		String sql = "insert into tb_user(username,password,sex,tel,photo,email values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getPhoto());
			ps.setString(6, user.getEmail());
			
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDB.closeConnection(conn);
		}
		
	}
	
	//�û���¼
	public User login(String username, String password){
		User user = null;
		//��ȡ���ݿ�����
		Connection conn = ConnectDB.getConnection();
		//�����û����������ѯ�û���Ϣ
		String sql = "select * from tb_user where name = ? and password = ?";
		
		try {
			//��ȡPreparedStatement����
			PreparedStatement ps = conn.prepareStatement(sql);
			//��sql����ռλ���������ж�̬��ֵ
			ps.setString(1, username);
			ps.setString(2, password);
			//ִ�в�ѯ��ȡ�����
			ResultSet rs = ps.executeQuery();
			//�жϽ�����Ƿ���Ч
			if(rs.next()){
				//ʵ����һ���û�����
				user = new User();
				//���û��������Ը�ֵ
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setPhoto(rs.getString("photo"));
				user.setEmail(rs.getString("email"));
			}
			//�ͷ�ResultSet��������ݿ��jdbc��Դ
			rs.close();
			//�ͷ�PreparedStatement��������ݿ��JDBC��Դ
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر����ݿ�
			ConnectDB.closeConnection(conn);
		}		
		return user;
	}
	
	//�ж��û��������ݿ����Ƿ����
	
	public boolean userIsExist(String username){
		//��ȡ���ݿ�����
		Connection conn = ConnectDB.getConnection();
		//����ָ�����û�����ѯ�û���Ϣ
		String sql = "select * from tb_user where username = ?";
		
		try {
			//��ȡps
			PreparedStatement ps = conn.prepareStatement(sql);
			//���û�����������Ը�ֵ
			ps.setString(1, username);
			//ִ�н����ȡ�����
			ResultSet rs = ps.executeQuery();
			//�����Ч��֤���û�������
			if(!rs.next()){
				return true;
			}
			//�ͷŴ�resultset�Ķ������ݿ��jdbc��Դ
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر����ݿ�
			ConnectDB.closeConnection(conn);
		}
		return false;
	}
}
