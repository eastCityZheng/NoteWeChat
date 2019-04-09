package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.JobRelationDao;
import com.server.dao.TeamnoteDao;
import com.server.dao.TeampanelDao;

/**
 * Servlet implementation class updateTNNameServlet
 */
@WebServlet("/updateTNNameServlet")
public class updateTNNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTNNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String u_id=new String(request.getParameter("u_id").getBytes(
				"ISO8859-1"), "UTF-8");
		String tp_id=new String(request.getParameter("tp_id").getBytes(
				"ISO8859-1"), "UTF-8");
		String tn_nickname=new String(request.getParameter("nickname").getBytes(
				"ISO8859-1"), "UTF-8");
		boolean result=new TeamnoteDao().updateTeamnoteNickname(Integer.parseInt(u_id), Integer.parseInt(tp_id), tn_nickname);
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
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
