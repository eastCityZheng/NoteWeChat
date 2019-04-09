package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.server.bean.Note;
import com.server.bean.Teamnote;
import com.server.util.DBUtil;

public class NoteDao {
	DBUtil util = new DBUtil();
	
	public List<Note> getOneUserNote(int u_id) {
		String sql = "select * from notewechat.note where u_id=? and n_top=0";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<Note> list = getOneUserNoteTop1(u_id);
			while (rs.next()) {
				Note no=new Note();
				no.setN_id(rs.getInt(1));
				no.setU_id(rs.getInt(2));
				no.setN_content(rs.getString(3));
				no.setN_time(rs.getString(4));
				no.setN_place(rs.getString(5));
				no.setN_img1(rs.getString(6));
				no.setN_img2(rs.getString(7));
				no.setN_img3(rs.getString(8));
				no.setN_img4(rs.getString(9));
				no.setN_video(rs.getString(10));
				no.setN_audio(rs.getString(11));
				no.setN_color(rs.getInt(12));
				no.setN_power(rs.getInt(13));
				no.setN_top(rs.getInt(14));
				no.setLon(rs.getDouble(16));
				no.setLat(rs.getDouble(17));
				
				list.add(no);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Note> getOneUserNoteTop1(int u_id) {
		String sql = "select * from notewechat.note where u_id=? and n_top=1";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<Note> list = new ArrayList<Note>();
			while (rs.next()) {
				Note no=new Note();
				no.setN_id(rs.getInt(1));
				no.setU_id(rs.getInt(2));
				no.setN_content(rs.getString(3));
				no.setN_time(rs.getString(4));
				no.setN_place(rs.getString(5));
				no.setN_img1(rs.getString(6));
				no.setN_img2(rs.getString(7));
				no.setN_img3(rs.getString(8));
				no.setN_img4(rs.getString(9));
				no.setN_video(rs.getString(10));
				no.setN_audio(rs.getString(11));
				no.setN_color(rs.getInt(12));
				no.setN_power(rs.getInt(13));
				no.setN_top(rs.getInt(14));
				no.setLon(rs.getDouble(16));
				no.setLat(rs.getDouble(17));
				
				list.add(no);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertNote(Note no) {
		String sql = "insert into notewechat.note(u_id,n_content,n_time,n_place,n_color,n_power,lon,lat) values(?,?,?,?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, no.getU_id());
			pstmt.setString(2, no.getN_content());
			pstmt.setString(3, no.getN_time());
			pstmt.setString(4, no.getN_place());
			pstmt.setInt(5, no.getN_color());
			pstmt.setInt(6, no.getN_power());
			pstmt.setDouble(7, no.getLon());
			pstmt.setDouble(8, no.getLat());
		
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
	//添加音频便签
	public boolean insertNoteAudio(Note no) {
		String sql = "insert into notewechat.note(u_id,n_content,n_time,n_place,n_color,n_power,n_audio,lon,lat) values(?,?,?,?,?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, no.getU_id());
			pstmt.setString(2, no.getN_content());
			pstmt.setString(3, no.getN_time());
			pstmt.setString(4, no.getN_place());
			pstmt.setInt(5, no.getN_color());
			pstmt.setInt(6, no.getN_power());
			pstmt.setString(7, no.getN_audio());
			pstmt.setDouble(8, no.getLon());
			pstmt.setDouble(9, no.getLat());
		
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
	
	//添加视频便签
		public boolean insertNoteVideo(Note no) {
			String sql = "insert into notewechat.note(u_id,n_content,n_time,n_place,n_color,n_power,n_video,lon,lat) values(?,?,?,?,?,?,?,?,?)";
			Connection conn = util.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1, no.getU_id());
				pstmt.setString(2, no.getN_content());
				pstmt.setString(3, no.getN_time());
				pstmt.setString(4, no.getN_place());
				pstmt.setInt(5, no.getN_color());
				pstmt.setInt(6, no.getN_power());
				pstmt.setString(7, no.getN_video());
				pstmt.setDouble(8, no.getLon());
				pstmt.setDouble(9, no.getLat());
			
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
	
	
	//更改图片 一个张
	public boolean updateNoteImg(String key,String value,int n_id) {
		String sql = "update notewechat.note set "+key+"=? where n_id=?";
		// 获得连接
		Connection conn = util.getConnection();
		
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, value);
			pstmt.setInt(2, n_id);
		
			
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
	
	//置顶
		public boolean updateNoteTop(int n_top,int n_id) {
			String sql = "update notewechat.note set n_top=? where n_id=?";
			// 获得连接
			Connection conn = util.getConnection();
			
			try {
				// 获得预定义语句
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 设置插入参数
				pstmt.setInt(1, n_top);
				pstmt.setInt(2, n_id);
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
		
		public boolean updateNote(Note no) {
			String sql = "update notewechat.note set n_content=?,n_time=?,n_place=?,n_color=?,n_power=?,is_illegal=? where n_id=?";
			// 获得连接
			Connection conn = util.getConnection();
			
			try {
				// 获得预定义语句
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 设置插入参数
				pstmt.setString(1, no.getN_content());
				pstmt.setString(2, no.getN_time());
				pstmt.setString(3, no.getN_place());
				pstmt.setInt(4, no.getN_color());
				pstmt.setInt(5, no.getN_power());
				pstmt.setInt(6, no.getIs_illegal());
				pstmt.setInt(7, no.getN_id());
				
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
		
		public Note getOneNote(int n_id) {
			String sql = "select * from notewechat.note where n_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1,n_id);
				
				ResultSet rs = pstmt.executeQuery();
				Note no=new Note();
				while (rs.next()) {
					no.setN_id(rs.getInt(1));
					no.setU_id(rs.getInt(2));
					no.setN_content(rs.getString(3));
					no.setN_time(rs.getString(4));
					no.setN_place(rs.getString(5));
					no.setN_img1(rs.getString(6));
					no.setN_img2(rs.getString(7));
					no.setN_img3(rs.getString(8));
					no.setN_img4(rs.getString(9));
					no.setN_video(rs.getString(10));
					no.setN_audio(rs.getString(11));
					no.setN_color(rs.getInt(12));
					no.setN_power(rs.getInt(13));
					no.setN_top(rs.getInt(14));
					no.setLon(rs.getDouble(16));
					no.setLat(rs.getDouble(17));
				}
				conn.close();
				return no;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public int getOneNoteId(int u_id,String n_time,String n_content) {
			String sql = "select n_id from notewechat.note where u_id=? and n_time=? and n_content=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1,u_id);
				pstmt.setString(2, n_time);
				pstmt.setString(3, n_content);
				
				ResultSet rs = pstmt.executeQuery();
				int n_id=0;
				while (rs.next()) {
					n_id=rs.getInt(1);
				}
				conn.close();
				return n_id;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		public boolean DeleteNote(int n_id) {
			String sql = "delete  from notewechat.note  where n_id=?";
			// 获得连接
			Connection conn = util.getConnection();
			try {
				// 获得预定义语句
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 设置插入参数
				pstmt.setInt(1, n_id);
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
		
		public List<Note> getOnePublicNote(int u_id) {
			String sql = "select * from notewechat.note where  u_id=? and n_power=1";
			Connection conn = util.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, u_id);
				ResultSet rs = pstmt.executeQuery();
				List<Note> list=new ArrayList<>();
				while (rs.next()) {
					Note no=new Note();
					no.setN_id(rs.getInt(1));
					no.setU_id(rs.getInt(2));
					no.setN_content(rs.getString(3));
					no.setN_time(rs.getString(4));
					no.setN_place(rs.getString(5));
					no.setN_img1(rs.getString(6));
					no.setN_img2(rs.getString(7));
					no.setN_img3(rs.getString(8));
					no.setN_img4(rs.getString(9));
					no.setN_video(rs.getString(10));
					no.setN_audio(rs.getString(11));
					no.setN_color(rs.getInt(12));
					no.setN_power(rs.getInt(13));
					no.setN_top(rs.getInt(14));
					no.setLon(rs.getDouble(16));
					no.setLat(rs.getDouble(17));
					
					list.add(no);
				}
				conn.close();
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	
}
