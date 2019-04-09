package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;
import com.server.bean.JobRelation;
import com.server.bean.Teamnote;
import com.server.bean.Teampanel;
import com.server.dao.JobRelationDao;
import com.server.dao.NoteDao;
import com.server.dao.TeamnoteDao;
import com.server.dao.TeampanelDao;
import com.server.dao.UserDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOneUserTeampanelServlet
 */
@WebServlet("/GetOneUserTeampanelServlet")
public class GetOneUserTeampanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneUserTeampanelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String u_id=new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8");
		
		List<JobRelation> jrList=new JobRelationDao().getOneUserJR(Integer.parseInt(u_id));
		
		
		List<Teampanel> tpList=new ArrayList<>();
		for(JobRelation jr:jrList) {
			Teampanel tp=new Teampanel();
			tp=new TeampanelDao().getOneTeampanel(jr.getTp_id());
			tp.setTp_top(jr.getJr_top());
			tpList.add(tp);
		}
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		JSONArray ja = JSONArray.fromObject(tpList);
		out.print(ja);
		
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
