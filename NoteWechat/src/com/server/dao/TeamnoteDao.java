package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.server.bean.Note;
import com.server.bean.Teamnote;
import com.server.bean.Teampanel;
import com.server.util.DBUtil;

public class TeamnoteDao {
	DBUtil util = new DBUtil();
	
	public List<Teamnote> getOnePanelTeamnote(int tp_id) {
		String sql = "select * from notewechat.teamnote where  tp_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tp_id);
			ResultSet rs = pstmt.executeQuery();
			List<Teamnote> list = new ArrayList<Teamnote>();
			while (rs.next()) {
				Teamnote tn=new Teamnote();
				tn.setTn_id(rs.getInt(1));
				tn.setU_id(rs.getInt(2));
				tn.setTp_id(rs.getInt(3));
				tn.setTn_content(rs.getString(4));
				tn.setTn_img1(rs.getString(5));
				tn.setTn_img2(rs.getString(6));
				tn.setTn_img3(rs.getString(7));
				tn.setTn_img4(rs.getString(8));
				tn.setTn_video(rs.getString(9));
				tn.setTn_audio(rs.getString(10));
				tn.setTn_color(rs.getInt(11));
				tn.setTn_nickname(rs.getString(12));
				tn.setTn_top(rs.getInt(13));
				tn.setTn_headimg(rs.getString(14));
				
				list.add(tn);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Teamnote> getOneUserTeamnote(int u_id ) {
		String sql = "select * from notewechat.teamnote where u_id=? and tn_top=0";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
	 
			ResultSet rs = pstmt.executeQuery();
			List<Teamnote> list = getOneUserTeamnoteTop1(u_id);
			while (rs.next()) {
				Teamnote tn=new Teamnote();
				tn.setTn_id(rs.getInt(1));
				tn.setU_id(rs.getInt(2));
				tn.setTp_id(rs.getInt(3));
				tn.setTn_content(rs.getString(4));
				tn.setTn_img1(rs.getString(5));
				tn.setTn_img2(rs.getString(6));
				tn.setTn_img3(rs.getString(7));
				tn.setTn_img4(rs.getString(8));
				tn.setTn_video(rs.getString(9));
				tn.setTn_audio(rs.getString(10));
				tn.setTn_color(rs.getInt(11));
				tn.setTn_nickname(rs.getString(12));
				tn.setTn_top(rs.getInt(13));
				
				list.add(tn);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Teamnote> getOneUserTeamnoteTop1(int u_id ) {
		String sql = "select * from notewechat.teamnote where u_id=? and tn_top=1";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
	 
			ResultSet rs = pstmt.executeQuery();
			List<Teamnote> list = new ArrayList<Teamnote>();
			while (rs.next()) {
				Teamnote tn=new Teamnote();
				tn.setTn_id(rs.getInt(1));
				tn.setU_id(rs.getInt(2));
				tn.setTp_id(rs.getInt(3));
				tn.setTn_content(rs.getString(4));
				tn.setTn_img1(rs.getString(5));
				tn.setTn_img2(rs.getString(6));
				tn.setTn_img3(rs.getString(7));
				tn.setTn_img4(rs.getString(8));
				tn.setTn_video(rs.getString(9));
				tn.setTn_audio(rs.getString(10));
				tn.setTn_color(rs.getInt(11));
				tn.setTn_nickname(rs.getString(12));
				tn.setTn_top(rs.getInt(13));
				
				list.add(tn);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean insertTeamnote(Teamnote tn) {
		String sql = "insert into notewechat.teamnote(u_id,tp_id,tn_content,tn_color,tn_nickname,tn_headimg) values(?,?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, tn.getU_id());
			pstmt.setInt(2, tn.getTp_id());
			pstmt.setString(3, tn.getTn_content());
			pstmt.setInt(4, tn.getTn_color());
			pstmt.setString(5, tn.getTn_nickname());
			pstmt.setString(6, tn.getTn_headimg());
		
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
	
	public boolean insertTeamnoteAudio(Teamnote tn) {
		String sql = "insert into notewechat.teamnote(u_id,tp_id,tn_content,tn_color,tn_nickname,tn_headimg,tn_audio) values(?,?,?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, tn.getU_id());
			pstmt.setInt(2, tn.getTp_id());
			pstmt.setString(3, tn.getTn_content());
			pstmt.setInt(4, tn.getTn_color());
			pstmt.setString(5, tn.getTn_nickname());
			pstmt.setString(6, tn.getTn_headimg());
			pstmt.setString(7, tn.getTn_audio());
		
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
	public boolean insertTeamnoteVideo(Teamnote tn) {
		String sql = "insert into notewechat.teamnote(u_id,tp_id,tn_content,tn_color,tn_nickname,tn_headimg,tn_video) values(?,?,?,?,?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, tn.getU_id());
			pstmt.setInt(2, tn.getTp_id());
			pstmt.setString(3, tn.getTn_content());
			pstmt.setInt(4, tn.getTn_color());
			pstmt.setString(5, tn.getTn_nickname());
			pstmt.setString(6, tn.getTn_headimg());
			pstmt.setString(7, tn.getTn_video());
		
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
	public boolean updateTeamnotelTop(int tn_top,int tn_id) {
		String sql = "update notewechat.teamnote set tn_top=? where tn_id";
		// 获得连接
		Connection conn = util.getConnection();
		
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setInt(1, tn_top);
			pstmt.setInt(2, tn_id);
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
	
	//更新内容
	public boolean updateTeamnote(Teamnote tn) {
		String sql = "update notewechat.teamnote set tn_content=?,tn_color=? where tn_id=?";
		// 获得连接
		Connection conn = util.getConnection();
		
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, tn.getTn_content());
			pstmt.setInt(2,tn.getTn_color());
			pstmt.setInt(3, tn.getTn_id());
			
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
	
	//更改图片 一个张
	public boolean updateTeamnoteImg(String key,String value,int tn_id) {
		String sql = "update notewechat.teamnote set "+key+"=? where tn_id=?";
		// 获得连接
		Connection conn = util.getConnection();
		
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, value);
			pstmt.setInt(2, tn_id);
		
			
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
	
	//获取一个note
	public Teamnote getOneTeamnote(int tn_id) {
		String sql = "select * from notewechat.teamnote where tn_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,tn_id);
			
			ResultSet rs = pstmt.executeQuery();
			Teamnote tn=new Teamnote();
			while (rs.next()) {
				tn.setTn_id(rs.getInt(1));
				tn.setU_id(rs.getInt(2));
				tn.setTp_id(rs.getInt(3));
				tn.setTn_content(rs.getString(4));
				tn.setTn_img1(rs.getString(5));
				tn.setTn_img2(rs.getString(6));
				tn.setTn_img3(rs.getString(7));
				tn.setTn_img4(rs.getString(8));
				tn.setTn_video(rs.getString(9));
				tn.setTn_audio(rs.getString(10));
				tn.setTn_color(rs.getInt(11));
			}
			conn.close();
			return tn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取一个note 特殊情况 小程序 对应页面 bq 
		public Note getOneTeamnoteNote(int tn_id) {
			String sql = "select * from notewechat.teamnote where tn_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1,tn_id);
				
				ResultSet rs = pstmt.executeQuery();
				Note no=new Note();
				while (rs.next()) {
					no.setU_id(rs.getInt(2));
					no.setN_content(rs.getString(4));
					no.setN_img1(rs.getString(5));
					no.setN_img2(rs.getString(6));
					no.setN_img3(rs.getString(7));
					no.setN_img4(rs.getString(8));
					no.setN_video(rs.getString(9));
					no.setN_audio(rs.getString(10));
					no.setN_color(rs.getInt(11));

				}
				conn.close();
				return no;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	
	//获取一个note
		public Teamnote getOneTeamnote(int u_id,int tp_id) {
			String sql = "select * from notewechat.teamnote where u_id=? and tp_id=?";
			Connection conn = util.getConnection();
			
			try {
				
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				pstmt.setInt(1,u_id);
				pstmt.setInt(2, tp_id);
				
				ResultSet rs = pstmt.executeQuery();
				Teamnote tn=new Teamnote();
				while (rs.next()) {
					tn.setTn_id(rs.getInt(1));
					tn.setU_id(rs.getInt(2));
					tn.setTp_id(rs.getInt(3));
					tn.setTn_content(rs.getString(4));
					tn.setTn_img1(rs.getString(5));
					tn.setTn_img2(rs.getString(6));
					tn.setTn_img3(rs.getString(7));
					tn.setTn_img4(rs.getString(8));
					tn.setTn_video(rs.getString(9));
					tn.setTn_audio(rs.getString(10));
					tn.setTn_color(rs.getInt(11));
				}
				conn.close();
				return tn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		//获取一个note
				public Teamnote getOneTeamnote(int u_id,int tp_id,String tp_content,int tp_color) {
					String sql = "select * from notewechat.teamnote where u_id=? and tp_id=? and tn_content=? and tn_color=?";
					Connection conn = util.getConnection();
					
					try {
						
						PreparedStatement pstmt = conn.prepareStatement(sql);	
						pstmt.setInt(1,u_id);
						pstmt.setInt(2, tp_id);
						pstmt.setString(3, tp_content);
						pstmt.setInt(4, tp_color);
						
						
						ResultSet rs = pstmt.executeQuery();
						Teamnote tn=new Teamnote();
						while (rs.next()) {
							tn.setTn_id(rs.getInt(1));
							tn.setU_id(rs.getInt(2));
							tn.setTp_id(rs.getInt(3));
							tn.setTn_content(rs.getString(4));
							tn.setTn_img1(rs.getString(5));
							tn.setTn_img2(rs.getString(6));
							tn.setTn_img3(rs.getString(7));
							tn.setTn_img4(rs.getString(8));
							tn.setTn_video(rs.getString(9));
							tn.setTn_audio(rs.getString(10));
							tn.setTn_color(rs.getInt(11));
						}
						conn.close();
						return tn;
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;
				}
		
	
	public boolean DeleteTeamnote(int tn_id) {
		String sql = "delete  from notewechat.teamnote  where tn_id=?";
		// 获得连接
		Connection conn = util.getConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setInt(1, tn_id);
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
	public int getOneTeamnotelU_id(int tn_id) {
		String sql = "select u_id from notewechat.teamnote where tn_id=?";
		Connection conn = util.getConnection();
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,tn_id);
			
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
	
	public boolean updateTeamnoteNickname(int u_id,int tp_id,String tn_nickname) {
		String sql = "update notewechat.teamnote set tn_nickname=? where u_id=? and tp_id=?";
		// 获得连接
		Connection conn = util.getConnection();
		
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置插入参数
			pstmt.setString(1, tn_nickname);
			pstmt.setInt(2, u_id);
			pstmt.setInt(3, tp_id);
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
	
}
