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
 * Servlet implementation class InsertJobRelationServlet
 */
@WebServlet("/InsertJobRelationServlet")
public class InsertJobRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertJobRelationServlet() {
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
		String jr_nickname=new String(request.getParameter("jr_nickname").getBytes("ISO8859-1"), "UTF-8");
				
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();

		if(new JobRelationDao().getOneJR(Integer.parseInt(tp_id), Integer.parseInt(u_id)).getJr_id()==0) {
			boolean result=new JobRelationDao().insertjr(Integer.parseInt(tp_id), Integer.parseInt(u_id), jr_nickname);
			if(result)
			out.print("true");
			else
				out.print("false");
		}else
			out.print("again");
			
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
