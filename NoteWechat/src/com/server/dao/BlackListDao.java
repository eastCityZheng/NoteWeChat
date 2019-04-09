package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.server.bean.BlackList;
import com.server.bean.User;
import com.server.util.DBUtil;

public class BlackListDao {
	DBUtil util = new DBUtil();
	
	public BlackList getOneUser(int u_id) {
		String sql = "select * from notewechat.blacklist where u_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,u_id);
			
			ResultSet rs = pstmt.executeQuery();
			BlackList bl=new BlackList();
			while (rs.next()) {
				bl.setU_id(rs.getInt(1));
				bl.setB_time(rs.getInt(2));
				bl.setStart_time(rs.getString(3));
				bl.setEnd_time(rs.getString(4));
				bl.setReason(rs.getString(5));
			}
			conn.close();
			return bl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
