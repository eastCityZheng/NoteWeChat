package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.TeampanelDao;


/**
 * Servlet implementation class UpdateOneAttributeServlet
 */
@WebServlet("/UpdateOneAttributeServlet")
public class UpdateOneAttributeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOneAttributeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String tablename=new String(request.getParameter("tablename").getBytes("ISO8859-1"), "UTF-8");
		String attr=new String(request.getParameter("attr").getBytes("ISO8859-1"), "UTF-8");
		String val=new String(request.getParameter("val").getBytes("ISO8859-1"), "UTF-8");
		
		String tp_id=new String(request.getParameter("tp_id").getBytes("ISO8859-1"), "UTF-8");
		
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		out.print(new TeampanelDao().updateOneAttribute(tablename, attr, val,Integer.parseInt(tp_id)));
		
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
