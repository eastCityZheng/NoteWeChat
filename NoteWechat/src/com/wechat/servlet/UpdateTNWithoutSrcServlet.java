package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Teamnote;
import com.server.dao.TeamnoteDao;

/**
 * Servlet implementation class UpdateTNWithoutSrcServlet
 */
@WebServlet("/UpdateTNWithoutSrcServlet")
public class UpdateTNWithoutSrcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTNWithoutSrcServlet() {
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
		String n_content=new String(request.getParameter("n_content").getBytes("ISO8859-1"), "UTF-8");
		String n_time=new String(request.getParameter("n_time").getBytes("ISO8859-1"), "UTF-8");
		String n_color=new String(request.getParameter("n_color").getBytes("ISO8859-1"), "UTF-8");
		String tn_id=new String(request.getParameter("tn_id").getBytes("ISO8859-1"), "UTF-8");
		
		
		Teamnote tn=new Teamnote();
		tn.setU_id(Integer.parseInt(u_id));
		tn.setTn_content(n_content);
		tn.setTn_time(n_time);
		tn.setTn_color(Integer.parseInt(n_color));
		tn.setTn_id(Integer.parseInt(tn_id));
		tn.setIs_illegal(1);
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		out.print(new TeamnoteDao().updateTeamnote(tn));
		
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
