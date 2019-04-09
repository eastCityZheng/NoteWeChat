package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.PersonalList;
import com.server.bean.User;
import com.server.dao.PersonalListDao;
import com.server.dao.UserDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOneUserPersonalListServlet
 */
@WebServlet("/GetOneUserPersonalListServlet")
public class GetOneUserPersonalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneUserPersonalListServlet() {
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
		
		List<PersonalList> plList=new PersonalListDao().getOneUserPersonalList(Integer.parseInt(u_id));
		
		List<User> usList=new ArrayList<>();
		for(PersonalList pl:plList) {
			User us=new UserDao().getOneUser(pl.getUu_id());
			usList.add(us);	
		}
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();

		JSONArray ja = JSONArray.fromObject(usList);
		out.print(ja);
		
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
