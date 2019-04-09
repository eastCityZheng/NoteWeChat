package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.server.bean.Note;
import com.server.bean.PersonalList;
import com.server.util.DBUtil;

public class PersonalListDao {
	DBUtil util = new DBUtil();
	
	public List<PersonalList> getOneUserPersonalList(int u_id) {
		String sql = "select * from notewechat.personallist where u_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			List<PersonalList> list = new ArrayList<PersonalList>();
			while (rs.next()) {
				PersonalList pl=new PersonalList();
				pl.setP_id(rs.getInt(1));
				pl.setU_id(rs.getInt(2));
				pl.setUu_id(rs.getInt(3));
				
				list.add(pl);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertPersonalList(int u_id,int uu_id) {
		String sql = "insert into notewechat.personallist(u_id,uu_id) value(?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, u_id);
			pstmt.setInt(2, uu_id);
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
	
	public int getOneU_id(int p_id) {
		String sql = "select uu_id from notewechat.personallist where p_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			ResultSet rs = pstmt.executeQuery();
			int  u_id=0;
			while (rs.next()) {
				u_id=rs.getInt(1);
			}
			conn.close();
			return u_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public PersonalList getOne(int u_id, int uu_id) {
		String sql = "select * from notewechat.personallist where u_id=? and uu_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			pstmt.setInt(2, uu_id);
			
			ResultSet rs = pstmt.executeQuery();
			PersonalList ps=new PersonalList();
			while (rs.next()) {
				ps.setP_id(rs.getInt(1));
				ps.setU_id(rs.getInt(2));
				ps.setUu_id(rs.getInt(3));
			}
			conn.close();
			return ps;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
