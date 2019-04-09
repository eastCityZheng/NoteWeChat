package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Teampanel;
import com.server.dao.JobRelationDao;
import com.server.dao.TeampanelDao;

/**
 * Servlet implementation class InsertTeampanleServlet
 */
@WebServlet("/InsertTeampanelServlet")
public class InsertTeampanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTeampanelServlet() {
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
		String tp_name=new String(request.getParameter("tp_name").getBytes("ISO8859-1"), "UTF-8");
		String tp_addr=new String(request.getParameter("tp_addr").getBytes("ISO8859-1"), "UTF-8");
		String tp_color=new String(request.getParameter("tp_color").getBytes("ISO8859-1"), "UTF-8");
		String tp_comment=new String(request.getParameter("tp_comment").getBytes("ISO8859-1"), "UTF-8");
		String tp_time=new String(request.getParameter("tp_time").getBytes("ISO8859-1"), "UTF-8");
		String jr_nickname=new String(request.getParameter("jr_nickname").getBytes("ISO8859-1"), "UTF-8");
		
		
		Teampanel tp=new Teampanel();
		tp.setU_id(Integer.parseInt(u_id));
		tp.setTp_name(tp_name);
		tp.setTp_addr(tp_addr);
		tp.setTp_color(Integer.parseInt(tp_color));
		tp.setTp_comment(tp_comment);
		tp.setTp_time(tp_time);
		
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		if(new TeampanelDao().insertTeampanel(tp)&&new JobRelationDao().insertjr(tp,jr_nickname))
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
