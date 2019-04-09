package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.server.bean.Message;
import com.server.bean.User;
import com.server.util.DBUtil;

public class MessageDao {
	DBUtil util = new DBUtil();
	
	public boolean insertmes(Message me) {
		String sql = "insert into notewechat.message(u_id,uu_id,m_content,m_color,m_time) values(?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, me.getU_id());
			pstmt.setInt(2, me.getUu_id());
			pstmt.setString(3, me.getM_content());
			pstmt.setInt(4, me.getM_color());
			pstmt.setString(5, me.getM_time());
			
		
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
	
	public List<Message> getOneUserMessage(int uu_id) {
		String sql = "select * from notewechat.message where uu_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uu_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<Message> melist=new ArrayList<Message>();
			while (rs.next()) {
				Message me=new Message();
				User us=new UserDao().getOneUser(rs.getInt(3));
				me.setM_id(rs.getInt(1));
				me.setU_id(rs.getInt(2));
				me.setUu_id(rs.getInt(3));
				me.setM_content(rs.getString(4));
				me.setM_color(rs.getInt(5));
				me.setM_time(rs.getString(6));
				me.setU_headimg(us.getHeadimg());
				me.setU_nickname(us.getNickname());
				melist.add(me);
			
			}
			conn.close();
			return melist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Message getOneMessage(int m_id) {
		String sql = "select * from notewechat.message where m_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			Message me=new Message();
			
			while (rs.next()) {
				User us=new UserDao().getOneUser(rs.getInt(3));
				me.setM_id(rs.getInt(1));
				me.setU_id(rs.getInt(2));
				me.setUu_id(rs.getInt(3));
				me.setM_content(rs.getString(4));
				me.setM_color(rs.getInt(5));
				me.setM_time(rs.getString(6));
				me.setU_headimg(us.getHeadimg());
				me.setU_nickname(us.getNickname());
			}
			conn.close();
			return me;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
