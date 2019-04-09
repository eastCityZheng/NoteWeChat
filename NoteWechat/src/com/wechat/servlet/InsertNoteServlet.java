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

import net.sf.json.JSONArray;

/**
 * Servlet implementation class InsertNoteServlet
 */
@WebServlet("/InsertNoteServlet")
public class InsertNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String n_page=new String(request.getParameter("n_page").getBytes("ISO8859-1"), "UTF-8");
		
		String u_id=new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8");
		String n_content=new String(request.getParameter("n_content").getBytes("ISO8859-1"), "UTF-8");
		String n_time=new String(request.getParameter("n_time").getBytes("ISO8859-1"), "UTF-8");
		String n_place=new String(request.getParameter("n_place").getBytes("ISO8859-1"), "UTF-8");
		String n_color=new String(request.getParameter("n_color").getBytes("ISO8859-1"), "UTF-8");
		String n_power=new String(request.getParameter("n_power").getBytes("ISO8859-1"), "UTF-8");
		
		String n_img=new String(request.getParameter("n_img").getBytes("ISO8859-1"), "UTF-8");
		
		JSONArray img=JSONArray.fromObject(n_img);
//		System.out.println(img.get(0));
		
		boolean flag=false;
		
		if(n_page.equals("gzmb")) {
			String tp_id=new String(request.getParameter("tp_id").getBytes("ISO8859-1"), "UTF-8");
			String tn_nickname=new String(request.getParameter("tn_nickname").getBytes("ISO8859-1"), "UTF-8");
			String tn_headimg=new String(request.getParameter("tn_headimg").getBytes("ISO8859-1"), "UTF-8");
			
			Teamnote tn=new Teamnote();
			tn.setTp_id(Integer.parseInt(tp_id));
			tn.setU_id(Integer.parseInt(u_id));
			tn.setTn_content(n_content);
			tn.setTn_time(n_time);
			tn.setTn_color(Integer.parseInt(n_color));
			tn.setTn_nickname(tn_nickname);
			tn.setTn_headimg(tn_headimg);
			
			flag=new TeamnoteDao().insertTeamnote(tn);
			int tn_id1=new TeamnoteDao().getOneTeamnote(tn.getU_id(), tn.getTp_id(), tn.getTn_content(), tn.getTn_color()).getTn_id();
			boolean re1=false;
			if(n_img.equals("[]")||n_img==null)
			{}else {
			for(int i=0;i<img.size();i++) {
				re1=new TeamnoteDao().updateTeamnoteImg("tn_img"+(i+1), img.get(i).toString(),tn_id1);	
				}
			}
			
		}else {
			String lon=new String(request.getParameter("lon").getBytes("ISO8859-1"), "UTF-8");
			String lat=new String(request.getParameter("lat").getBytes("ISO8859-1"), "UTF-8");
			
			System.out.println(lon+lat);
			
			Note no=new Note();
			no.setU_id(Integer.parseInt(u_id));
			no.setN_content(n_content);
			no.setN_time(n_time);
			no.setN_place(n_place);
			no.setN_color(Integer.parseInt(n_color));
			no.setN_power(Integer.parseInt(n_power));
			no.setLon(Double.parseDouble(lon));
			no.setLat(Double.parseDouble(lat));
		
			flag=new NoteDao().insertNote(no);
			int n_id=new NoteDao().getOneNoteId(no.getU_id(), no.getN_time(), n_content);
			boolean re=false;
			System.out.println(n_img);
			if(n_img.equals("[]")||n_img==null)
			{}else {
				for(int i=0;i<img.size();i++) {
					 re=new NoteDao().updateNoteImg("n_img"+(i+1), img.get(i).toString(),n_id);	
				}
			}
		}
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		if(flag)
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
