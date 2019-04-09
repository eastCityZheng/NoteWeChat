package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.server.bean.JobRelation;
import com.server.bean.Teampanel;
import com.server.util.DBUtil;

public class JobRelationDao {
	DBUtil util = new DBUtil();
	
	public List<JobRelation> getOneUserJR(int u_id) {
		String sql = "select * from notewechat.jobrelation where u_id=? and jr_top=0";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<JobRelation> list = getOneUserJRTop1(u_id);
			while (rs.next()) {
				JobRelation jr=new JobRelation();
				jr.setJr_id(rs.getInt(1));
				jr.setTp_id(rs.getInt(2));
				jr.setU_id(rs.getInt(3));
				jr.setJr_top(rs.getInt(4));
				jr.setJr_nickname(rs.getString(5));
				
				list.add(jr);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<JobRelation> getOneUserJRTop1(int u_id) {
		String sql = "select * from notewechat.jobrelation where u_id=? and jr_top=1";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<JobRelation> list = new ArrayList<JobRelation>();
			while (rs.next()) {
				JobRelation jr=new JobRelation();
				jr.setJr_id(rs.getInt(1));
				jr.setTp_id(rs.getInt(2));
				jr.setU_id(rs.getInt(3));
				jr.setJr_top(rs.getInt(4));
				jr.setJr_nickname(rs.getString(5));
				
				list.add(jr);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<JobRelation> getOneTeampanelJR(int tp_id) {
		String sql = "select * from notewechat.jobrelation where tp_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tp_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<JobRelation> list = new ArrayList<JobRelation>();
			while (rs.next()) {
				JobRelation jr=new JobRelation();
				jr.setJr_id(rs.getInt(1));
				jr.setTp_id(rs.getInt(2));
				jr.setU_id(rs.getInt(3));
				jr.setJr_top(rs.getInt(4));
				jr.setJr_nickname(rs.getString(5));
				
				list.add(jr);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JobRelation getOneJR(int tp_id,int u_id) {
		String sql = "select * from notewechat.jobrelation where tp_id=? and u_id=?";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tp_id);
			pstmt.setInt(2, u_id);
			
			ResultSet rs = pstmt.executeQuery();
			JobRelation jr=new JobRelation();
			while (rs.next()) {
				
				jr.setJr_id(rs.getInt(1));
				jr.setTp_id(rs.getInt(2));
				jr.setU_id(rs.getInt(3));
				jr.setJr_top(rs.getInt(4));
				jr.setJr_nickname(rs.getString(5));

			}
			conn.close();
			return jr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean insertjr(Teampanel tp,String jr_nickname) {
		String sql = "insert into notewechat.jobrelation(tp_id,u_id,jr_nickname) values(?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setInt(1, new TeampanelDao().getOneTeampanel(tp).getTp_id());
			pstmt.setInt(2, tp.getU_id());
			pstmt.setString(3, jr_nickname);
			
		
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
	
	public boolean insertjr(int tp_id,int u_id,String jr_nickname) {
		String sql = "insert into notewechat.jobrelation(tp_id,u_id,jr_nickname) values(?,?,?)";
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			
			pstmt.setInt(1, tp_id);
			pstmt.setInt(2, u_id);
			pstmt.setString(3, jr_nickname);
			
		
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
			public boolean updateJRTop(int n_top,int u_id,int tp_id) {
				String sql = "update notewechat.jobrelation set jr_top=? where u_id=? and tp_id=?";
				// 获得连接
				Connection conn = util.getConnection();
				
				try {
					// 获得预定义语句
					PreparedStatement pstmt = conn.prepareStatement(sql);
					// 设置插入参数
					pstmt.setInt(1, n_top);
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
			
			//删除工作面板
			public boolean deleteJR( int tp_id) {
				String sql = "delete from notewechat.jobrelation   where  tp_id=?";
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
}
