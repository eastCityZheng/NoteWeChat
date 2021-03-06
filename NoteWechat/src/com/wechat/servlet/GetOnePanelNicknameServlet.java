package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Teampanel;
import com.server.dao.TeampanelDao;
import com.server.dao.UserDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOnePanelNicknameServlet
 */
@WebServlet("/GetOnePanelNicknameServlet")
public class GetOnePanelNicknameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOnePanelNicknameServlet() {
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
		
		Teampanel tp=new TeampanelDao().getOneTeampanel(Integer.parseInt(tp_id));
		
		String nickname=new UserDao().getOneUserName(tp.getU_id());
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		

		out.print(nickname);
		
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
