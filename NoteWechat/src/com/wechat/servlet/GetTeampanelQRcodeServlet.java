package com.wechat.servlet;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.dao.TeampanelDao;
import com.server.dao.UserDao;
import com.swetake.util.Qrcode;


/**
 * Servlet implementation class GetTeampanelQRcodeServlet
 */
@WebServlet("/GetTeampanelQRcodeServlet")
public class GetTeampanelQRcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeampanelQRcodeServlet() {
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
		String n_page=new String(request.getParameter("n_page").getBytes("ISO8859-1"), "UTF-8");
		
		String filename=String.valueOf(System.currentTimeMillis()) ;
		String output="";
		
		String uploadPath = request.getServletContext().getRealPath("./") + File.separator + "upload/"+filename+".png";
		if(n_page.equals("roominfo")) {
			if(new TeampanelDao().getOnetpQRcode(Integer.parseInt(tp_id)).equals("")) {
				getQrcodeImg("InsertJobRelationServlet?tp_id="+tp_id, uploadPath);
				output=filename+".png";
				new TeampanelDao().updateTpQRcode(Integer.parseInt(tp_id),output);
			}else {
				output=new TeampanelDao().getOnetpQRcode(Integer.parseInt(tp_id));
			}
		}
		else {
			if(new UserDao().getOneUserQRcode(Integer.parseInt(tp_id)).equals("")) {
				getQrcodeImg(tp_id, uploadPath);
				output=filename+".png";
				new UserDao().updateQRcode(Integer.parseInt(tp_id),output);
			}else {
				output=new UserDao().getOneUserQRcode(Integer.parseInt(tp_id));
			}
			
		}
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		out.print(output);
		
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
	
	
	// ��һ�����������֣��ڶ��������Ƕ�ά��洢��ַ
		public static void getQrcodeImg(String content, String imgPath) {
			int width = 140;
			int height = 140;
			// ʵ����Qrcode
			Qrcode qrcode = new Qrcode();
			// ���ö�ά����Ŵ���L(7%) M(15%) Q(25%) H(35%)
			qrcode.setQrcodeErrorCorrect('M');
			qrcode.setQrcodeEncodeMode('B');
			// ���ö�ά��ߴ�(1~49)
			qrcode.setQrcodeVersion(7);
			// ����ͼƬ�ߴ�
			BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

			// ���ƶ�ά��ͼƬ
			Graphics2D gs = bufImg.createGraphics();
			// ���ö�ά�뱳����ɫ
			gs.setBackground(Color.WHITE);
			// ����һ����������
			gs.clearRect(0, 0, width, height);
			// ���ö�ά���ͼƬ��ɫֵ ��ɫ
			gs.setColor(Color.BLACK);

			// ��ȡ���ݵ��ֽ�����,���ñ��뼯
			try {
				byte[] contentBytes = content.getBytes("utf-8");
				int pixoff = 2;
				// �����ά��
				if (contentBytes.length > 0 && contentBytes.length < 120) {
					boolean[][] codeOut = qrcode.calQrcode(contentBytes);
					for (int i = 0; i < codeOut.length; i++) {
						for (int j = 0; j < codeOut.length; j++) {
							if (codeOut[j][i]) {
								gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
							}
						}
					}
				}
				gs.dispose();
				bufImg.flush();
				// ���ɶ�ά��ͼƬ
				File imgFile = new File(imgPath);
				ImageIO.write(bufImg, "png", imgFile);

				System.out.println("��ά�����ɳɹ���");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}


