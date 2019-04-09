package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.TeamnoteDao;
import com.server.dao.TeampanelDao;

/**
 * Servlet implementation class DeleteTeamnoteServlet
 */
@WebServlet("/DeleteTeamnoteServlet")
public class DeleteTeamnoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTeamnoteServlet() {
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
		String tn_id=new String(request.getParameter("tn_id").getBytes("ISO8859-1"), "UTF-8");
		String tp_id=new String(request.getParameter("tp_id").getBytes("ISO8859-1"), "UTF-8");
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		int tp_u_id=new TeampanelDao().getOneTeampanelU_id(Integer.parseInt(tp_id));
		if(tp_u_id==Integer.parseInt(u_id)) {
			boolean result=new TeamnoteDao().DeleteTeamnote(Integer.parseInt(tn_id));
			out.print(result);
		}else {
			out.print("false2");//提示 不是创建者不能删除他人便签 
		}
		
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
