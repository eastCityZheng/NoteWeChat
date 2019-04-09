package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.bean.Note;
import com.server.dao.NoteDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class UpdateNoteServlet
 */
@WebServlet("/UpdateNoteServlet")
public class UpdateNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoteServlet() {
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
		String n_place=new String(request.getParameter("n_place").getBytes("ISO8859-1"), "UTF-8");
		String n_color=new String(request.getParameter("n_color").getBytes("ISO8859-1"), "UTF-8");
		String n_power=new String(request.getParameter("n_power").getBytes("ISO8859-1"), "UTF-8");
		String n_id=new String(request.getParameter("n_id").getBytes("ISO8859-1"), "UTF-8");
		
		String n_img=new String(request.getParameter("n_img").getBytes("ISO8859-1"), "UTF-8");
		JSONArray img=JSONArray.fromObject(n_img);
		
		Note no=new Note();
		no.setU_id(Integer.parseInt(u_id));
		no.setN_content(n_content);
		no.setN_time(n_time);
		no.setN_place(n_place);
		no.setN_color(Integer.parseInt(n_color));
		no.setN_power(Integer.parseInt(n_power));
		no.setN_id(Integer.parseInt(n_id));
		no.setIs_illegal(1);
		
		boolean re=false;
		System.out.println(n_img);
		if(n_img.equals("[]")||n_img==null)
		{}else {
			for(int i=0;i<img.size();i++) {
				 re=new NoteDao().updateNoteImg("n_img"+(i+1), img.get(i).toString(),Integer.parseInt(n_id));	
			}
		}
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		out.print(new NoteDao().updateNote(no));
		
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
