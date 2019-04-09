package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.server.bean.Teampanel;
import com.server.bean.User;
import com.server.util.DBUtil;

public class UserDao {
	DBUtil util = new DBUtil();
	
	public User checkUserWX(String u_openid) {
		// T-SQL语句
		String sql = "select * from notewechat.user where u_openid=?";
		// 获得连接
		Connection conn = util.getConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 执行查询
			pstmt.setString(1, u_openid);
			ResultSet rs = pstmt.executeQuery();
			User us=new User();
			while (rs.next()) {
				// 封装信息
				us.setU_id(rs.getInt(1));
				us.setU_openid(rs.getString(2));
				us.setNickname(rs.getString(3));
				us.setHeadimg(rs.getString(4));
				us.setU_isuse(rs.getInt(6));
			}
			conn.close();
			return us;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertUserWX(User us) {
		String sql = "insert into notewechat.user(u_openid,nickname,headimg) values(?,?,?)";
		// 获得连接
		Connection conn = util.getConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, us.getU_openid());
			pstmt.setString(2, us.getNickname());
			pstmt.setString(3, us.getHeadimg());

			// 执行插入
			if (pstmt.executeUpdate() > 0) {
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateUserWx(User us) {
		String sql="update notewechat.user set nickname=? where u_openid=?";
		Connection conn = util.getConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, us.getNickname());
			pstmt.setString(2, us.getU_openid());
			
		
			// 执行插入
			if (pstmt.executeUpdate() > 0) {
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User getOneUser(int u_id) {
		String sql = "select * from notewechat.user where u_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,u_id);
			
			ResultSet rs = pstmt.executeQuery();
			User us=new User();
			while (rs.next()) {
				us.setU_id(rs.getInt(1));
				us.setU_openid(rs.getString(2));
				us.setNickname(rs.getString(3));
				us.setHeadimg(rs.getString(4));
			}
			conn.close();
			return us;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String  getOneUserName(int u_id) {
		String sql = "select nickname from notewechat.user where u_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,u_id);
			
			ResultSet rs = pstmt.executeQuery();
			String nickname="";
			while (rs.next()) {
				nickname=rs.getString(1);
			}
			conn.close();
			return nickname;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getOneUserQRcode(int u_id) {
		String sql = "select u_qrcode from notewechat.user where u_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,u_id);
			
			ResultSet rs = pstmt.executeQuery();
			String u_qrcode="";
			while (rs.next()) {
				u_qrcode=rs.getString(1);
			}
			conn.close();
			return u_qrcode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void updateQRcode(int u_id, String output) {
		// TODO Auto-generated method stub
		String sql="update notewechat.user set u_qrcode=? where u_id=?";
		Connection conn = util.getConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, output);
			pstmt.setInt(2, u_id);
	
			// 执行插入
			if (pstmt.executeUpdate() > 0) {
				conn.close();
			} else {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
