package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.server.bean.Note;
import com.server.bean.Teampanel;
import com.server.bean.User;
import com.server.util.DBUtil;

public class TeampanelDao {
	DBUtil util = new DBUtil();
	
	
	public List<Teampanel> getOneUserTeampanel(int u_id) {
		String sql = "select * from notewechat.teampanel where u_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			List<Teampanel> list = new ArrayList<Teampanel>();
			while (rs.next()) {
				Teampanel tp=new Teampanel();
				tp.setTp_id(rs.getInt(1));
				tp.setU_id(rs.getInt(2));
				tp.setTp_name(rs.getString(3));
				tp.setTp_comment(rs.getString(4));
				tp.setTp_addr(rs.getString(5));
				tp.setTp_time(rs.getString(6));
				tp.setTp_color(rs.getInt(7));


				list.add(tp);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertTeampanel(Teampanel tp) {
		String sql="";
		if(tp.getTp_comment()==""||tp.getTp_comment()==null) {
			sql="insert into notewechat.teampanel(u_id,tp_name,tp_addr,tp_time,tp_color) values(?,?,?,?,?)";
		}else
		 sql = "insert into notewechat.teampanel(u_id,tp_name,tp_addr,tp_time,tp_color,tp_comment) values(?,?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, tp.getU_id());
			pstmt.setString(2, tp.getTp_name());
			pstmt.setString(3, tp.getTp_addr());
			pstmt.setString(4, tp.getTp_time());
			pstmt.setInt(5, tp.getTp_color());
			if(tp.getTp_comment()==""||tp.getTp_comment()==null) 
			{}
			else
				pstmt.setString(6, tp.getTp_comment());

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
	
	public boolean updateTeampanel(Teampanel tp) {
		String sql = "update notewechat.teampanel set tp_name=?,tp_addr=?,tp_time=?,tp_color=? where u_id=?";
		// 获得连接
		Connection conn = util.getConnection();
		
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, tp.getTp_name());
			pstmt.setString(2, tp.getTp_addr());
			pstmt.setString(3, tp.getTp_time());
			pstmt.setInt(4, tp.getTp_color());
			pstmt.setInt(5, tp.getU_id());
			
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

	public Teampanel getOneTeampanel(int tp_id) {
		String sql = "select * from notewechat.teampanel where tp_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,tp_id);
			
			ResultSet rs = pstmt.executeQuery();
			Teampanel tp=new Teampanel();
			while (rs.next()) {
				tp.setTp_id(rs.getInt(1));
				tp.setU_id(rs.getInt(2));
				tp.setTp_name(rs.getString(3));
				tp.setTp_comment(rs.getString(4));
				tp.setTp_addr(rs.getString(5));
				tp.setTp_time(rs.getString(6));
				tp.setTp_color(rs.getInt(7));
			}
			conn.close();
			return tp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Teampanel getOneTeampanel(Teampanel tp) {
		String sql = "select * from notewechat.teampanel where u_id=? and tp_name=? and tp_comment=? and tp_addr=? and tp_time=? and tp_color=? ";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,tp.getU_id());
			pstmt.setString(2, tp.getTp_name());
			pstmt.setString(3, tp.getTp_comment());
			pstmt.setString(4, tp.getTp_addr());
			pstmt.setString(5, tp.getTp_time());
			pstmt.setInt(6, tp.getTp_color());
			
			
			ResultSet rs = pstmt.executeQuery();
			Teampanel tp1=new Teampanel();
			while (rs.next()) {
				tp1.setTp_id(rs.getInt(1));
				tp1.setU_id(rs.getInt(2));
				tp1.setTp_name(rs.getString(3));
				tp1.setTp_comment(rs.getString(4));
				tp1.setTp_addr(rs.getString(5));
				tp1.setTp_time(rs.getString(6));
				tp1.setTp_color(rs.getInt(7));
			}
			conn.close();
			return tp1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//更改信息
		public boolean updateTeampanelImg(String key,String value) {
			String sql = "update notewechat.teampanel set ?=?";
			// 获得连接S
			Connection conn = util.getConnection();
			
			try {
				// 获得预定义语句
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 设置插入参数
				pstmt.setString(1, key);
				pstmt.setString(2, value);
			
				
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
		
		public boolean DeleteTeampanel(int tp_id) {
			String sql = "delete  from notewechat.teampanel  where tp_id=?";
			// 获得连接
			Connection conn = util.getConnection();
			try {
				// 获得预定义语句
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 设置插入参数
				pstmt.setInt(1, tp_id);
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
		
		public int getOneTeampanelU_id(int tp_id) {
			String sql = "select u_id from notewechat.teampanel where tp_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1,tp_id);
				
				ResultSet rs = pstmt.executeQuery();
				int u_id=0;
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
		public boolean updateOneAttribute(String tablename,String attr,String val,int tp_id) {
			String sql = "update  notewechat."+tablename+" set "+attr+"=? where tp_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, val);
				pstmt.setInt(2, tp_id);

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

		public String getOnetpQRcode(int tp_id) {
			// TODO Auto-generated method stub
			String sql = "select tp_qrcode from notewechat.teampanel where tp_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1,tp_id);
				
				ResultSet rs = pstmt.executeQuery();
				String tp_qrcode="";
				while (rs.next()) {
					tp_qrcode=rs.getString(1);
				}
				conn.close();
				return tp_qrcode;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public void updateTpQRcode(int parseInt, String output) {
			// TODO Auto-generated method stub
			String sql = "update  notewechat.teampanel tp_qrcode=? where tp_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, output);
				pstmt.setInt(2, parseInt);
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
