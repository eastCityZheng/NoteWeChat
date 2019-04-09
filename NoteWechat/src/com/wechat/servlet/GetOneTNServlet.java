package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Note;
import com.server.bean.User;
import com.server.dao.TeamnoteDao;
import com.server.dao.UserDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOneTNServlet
 */
@WebServlet("/GetOneTNServlet")
public class GetOneTNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneTNServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String tn_id=new String(request.getParameter("tn_id").getBytes("ISO8859-1"), "UTF-8");
		
		Note no=new TeamnoteDao().getOneTeamnoteNote(Integer.parseInt(tn_id));
		User us=new UserDao().getOneUser(no.getU_id());
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		JSONArray ja = JSONArray.fromObject(no);
		JSONArray ja2 = JSONArray.fromObject(us);
		
		out.print("{\"note\":");
		out.print(ja);
		out.print(",\"user\":");
		out.print(ja2);
		out.print("}");
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
