package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Note;
import com.server.bean.Teamnote;
import com.server.dao.NoteDao;
import com.server.dao.TeamnoteDao;

/**
 * Servlet implementation class InsertNoteVideoServlet
 */
@WebServlet("/InsertNoteVideoServlet")
public class InsertNoteVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoteVideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		String n_page=new String(request.getParameter("n_page").getBytes("ISO8859-1"), "UTF-8");
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		if(n_page.equals("index")) {
			Note no=new Note();
			no.setU_id(Integer.parseInt(new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8")));
			no.setN_content(new String(request.getParameter("n_content").getBytes("ISO8859-1"), "UTF-8")); 
			no.setN_time(new String(request.getParameter("n_time").getBytes("ISO8859-1"), "UTF-8"));
			no.setN_place(new String(request.getParameter("n_place").getBytes("ISO8859-1"), "UTF-8"));
			no.setN_color(Integer.parseInt(new String(request.getParameter("n_color").getBytes("ISO8859-1"), "UTF-8")));
			no.setN_power(Integer.parseInt(new String(request.getParameter("n_power").getBytes("ISO8859-1"), "UTF-8")));  
			no.setN_video(new String(request.getParameter("n_video").getBytes("ISO8859-1"), "UTF-8"));
			
			out.print(new NoteDao().insertNoteVideo(no));
			
		}else {
			String tp_id=new String(request.getParameter("tp_id").getBytes("ISO8859-1"), "UTF-8");
			String tn_nickname=new String(request.getParameter("tn_nickname").getBytes("ISO8859-1"), "UTF-8");
			String tn_headimg=new String(request.getParameter("tn_headimg").getBytes("ISO8859-1"), "UTF-8");
			
			Teamnote tn=new Teamnote();
			tn.setU_id(Integer.parseInt(new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8")));
			tn.setTn_content(new String(request.getParameter("n_content").getBytes("ISO8859-1"), "UTF-8"));
			tn.setTn_time(new String(request.getParameter("n_time").getBytes("ISO8859-1"), "UTF-8"));
			tn.setTn_color(Integer.parseInt(new String(request.getParameter("n_color").getBytes("ISO8859-1"), "UTF-8")));
			tn.setTp_id(Integer.parseInt(tp_id));
			tn.setTn_nickname(tn_nickname);
			tn.setTn_headimg(tn_headimg);
			tn.setTn_video(new String(request.getParameter("n_video").getBytes("ISO8859-1"), "UTF-8"));
			
			out.print(new TeamnoteDao().insertTeamnoteVideo(tn));
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
