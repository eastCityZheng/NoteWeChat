package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.BlackList;
import com.server.bean.User;
import com.server.dao.BlackListDao;
import com.server.dao.UserDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String u_openid=new String(request.getParameter("openid").getBytes(
				"ISO8859-1"), "UTF-8");
		String headimg=new String(request.getParameter("headimg").getBytes(
				"ISO8859-1"), "UTF-8");
		String nickname=new String(request.getParameter("nickname").getBytes("ISO8859-1"), "UTF-8");
		
		User uss=new User();
		uss.setU_openid(u_openid);
		uss.setHeadimg(headimg);
		uss.setNickname(nickname);
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		User us=new UserDao().checkUserWX(u_openid);
		if(us.getU_id()==0)
		{
			boolean result=new UserDao().insertUserWX(uss);
			if(result) {
				JSONArray ja = JSONArray.fromObject(new UserDao().checkUserWX(uss.getU_openid()));
				out.print(ja);
			}else {
				out.print(0);
			}
					
		}else {
			
			if(new UserDao().updateUserWx(uss)) {
				if (us.getU_isuse()==0) {
					BlackList bl = new BlackList();
					bl = new BlackListDao().getOneUser(us.getU_id());
					JSONObject result = new JSONObject();
					result.put("code", 0);
					result.put("error", "您已经被禁用，禁用时间为"+bl.getStart_time()+"——"+bl.getEnd_time());
					out.print(result);
				}else {
					JSONArray jaa = JSONArray.fromObject(new UserDao().checkUserWX(uss.getU_openid()));
					out.print(jaa);
				}
				
			}else
				out.print(0);
				
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
