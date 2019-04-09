package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Note;
import com.server.dao.NoteDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetOneUserPersonalNoteServlet
 */
@WebServlet("/GetOneUserPublicNoteServlet")
public class GetOneUserPublicNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneUserPublicNoteServlet() {
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
	
		List<Note> nList=new NoteDao().getOnePublicNote(Integer.parseInt(u_id));
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		JSONArray ja=JSONArray.fromObject(nList);
		
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
