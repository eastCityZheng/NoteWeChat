package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.JobRelationDao;
import com.server.dao.NoteDao;

/**
 * Servlet implementation class SetTopServlet
 */
@WebServlet("/SetTopServlet")
public class SetTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetTopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String n_top=new String(request.getParameter("n_top").getBytes("ISO8859-1"), "UTF-8");
		String id=new String(request.getParameter("id").getBytes("ISO8859-1"), "UTF-8"); //n_id || tp_id
		String type=new String(request.getParameter("n_type").getBytes("ISO8859-1"), "UTF-8");
		
		boolean result=false;
		if(type.equals("note"))
			result=new NoteDao().updateNoteTop(Integer.parseInt(n_top),Integer.parseInt(id));
		else {
			String u_id=new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8");
			result=new JobRelationDao().updateJRTop(Integer.parseInt(n_top),Integer.parseInt(u_id), Integer.parseInt(id));
		}
			
			
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		if(result)
			out.print("true");
		else
			out.print("false");
		
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
