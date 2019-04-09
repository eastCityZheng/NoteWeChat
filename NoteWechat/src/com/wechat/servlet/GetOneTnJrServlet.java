package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.JobRelation;
import com.server.dao.JobRelationDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOneTnJrServlet
 */
@WebServlet("/GetOneTnJrServlet")
public class GetOneTnJrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneTnJrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String tp_id=new String(request.getParameter("tp_id").getBytes("ISO8859-1"), "UTF-8");
		String u_id=new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8");
		
		JobRelation jr=new JobRelationDao().getOneJR(Integer.parseInt(tp_id), Integer.parseInt(u_id));
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		JSONArray ja = JSONArray.fromObject(jr);
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
