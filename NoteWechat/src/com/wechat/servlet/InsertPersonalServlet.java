package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.NoteDao;
import com.server.dao.PersonalListDao;

/**
 * Servlet implementation class InsertPersonalServlet
 */
@WebServlet("/InsertPersonalServlet")
public class InsertPersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPersonalServlet() {
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
		
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		if(new PersonalListDao().getOne(Integer.parseInt(u_id),Integer.parseInt(uu_id)).getP_id()==0) {
			if(new PersonalListDao().insertPersonalList(Integer.parseInt(u_id),Integer.parseInt(uu_id)))
				out.print("true");
			else
				out.print("false");
		}else
			out.print("true");
		
		
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
