package com.wechat.servlet;

import java.awt.JobAttributes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.JobRelation;
import com.server.bean.Teamnote;
import com.server.dao.JobRelationDao;
import com.server.dao.TeamnoteDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOnePanelTeamnoteServlet
 */
@WebServlet("/GetOnePanelTeamnoteServlet")
public class GetOnePanelTeamnoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOnePanelTeamnoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String tp_id=new String(request.getParameter("tp_id").getBytes("ISO8859-1"), "UTF-8");
		
//		List<JobRelation> jrList=new JobRelationDao().getOneTeampanelJR(Integer.parseInt(tp_id));
//		
//		List<Teamnote> tnList=new ArrayList<>();
//		for(JobRelation jr:jrList) {
//			Teamnote tn=new TeamnoteDao().getOneTeamnote(jr.getU_id(),jr.getTp_id());
//			tnList.add(tn);
//		}
		List<Teamnote> tnList=new TeamnoteDao().getOnePanelTeamnote(Integer.parseInt(tp_id));
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		JSONArray ja = JSONArray.fromObject(tnList);
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
