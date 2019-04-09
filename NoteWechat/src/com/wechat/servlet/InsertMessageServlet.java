package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Message;
import com.server.dao.MessageDao;

/**
 * Servlet implementation class InsertMessageServlet
 */
@WebServlet("/InsertMessageServlet")
public class InsertMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String u_id=new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8");
		String uu_id=new String(request.getParameter("uu_id").getBytes("ISO8859-1"), "UTF-8");
		String m_content=new String(request.getParameter("m_content").getBytes("ISO8859-1"), "UTF-8");
		String m_color=new String(request.getParameter("m_color").getBytes("ISO8859-1"), "UTF-8");
		String m_time=new String(request.getParameter("m_time").getBytes("ISO8859-1"), "UTF-8");

		Message me=new Message();
		me.setU_id(Integer.parseInt(u_id));
		me.setUu_id(Integer.parseInt(uu_id));
		me.setM_content(m_content);
		me.setM_color(Integer.parseInt(m_color));
		me.setM_time(m_time);
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
	
		out.print(new MessageDao().insertmes(me));
		
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
