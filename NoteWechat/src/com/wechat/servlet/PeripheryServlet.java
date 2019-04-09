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

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.server.bean.Note;
import com.server.dao.NoteDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PeripheryServlet
 */
@WebServlet("/PeripheryServlet")
public class PeripheryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m) 
	private static final String KEY="HYXBZ-EX5LI-ACKGN-5CN3I-2UJPJ-BLBLV";
	private static double rad(double d)  
	    {  return d * Math.PI / 180.0;  }  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeripheryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		double lat=Double.valueOf(new String(request.getParameter("lat").getBytes("ISO8859-1"), "UTF-8"));
		double lon=Double.valueOf(new String(request.getParameter("lon").getBytes("ISO8859-1"), "UTF-8"));
		String u_id=new String(request.getParameter("u_id").getBytes("ISO8859-1"), "UTF-8");
		
		
		List<Note> list=new NoteDao().getOneUserNote(Integer.parseInt(u_id));
		List<Note>  peripheryList=new ArrayList<>();
		
		for(Note st:list) {
//			if(st.getN_place().equals(""))
//				continue;
//			//地址转经纬度
//			String url=geturl(st.getN_place(), KEY);
//			String content=get(url);
//			JSONObject  json = JSONObject.fromObject(content);
//			
//			JSONObject result=(JSONObject)json.get("result");
//			if(result==null)
//				continue;
//			JSONObject location=(JSONObject)result.get("location");
//			double n_lng= location.getDouble("lng");
//			double n_lat= location.getDouble("lat");
			
			if(st.getLat()==0.0 ||st.getLon()==0.0)
				continue;
			double n_lng = st.getLon();
			double n_lat = st.getLat();
			
//			double dist=LantitudeLongitudeDist(n_lng, n_lat, lon, lat);
			
//			System.out.println("dist="+dist);
//			System.out.println(n_lng+" "+n_lat+" "+lon+" "+lat);
			//转换坐标系
			String coord = get(getcoordurl(lon, lat,n_lng,n_lat, KEY));
			JSONObject coord_json=JSONObject.fromObject(coord);
			JSONArray coord_array = coord_json.getJSONArray("locations");
			JSONObject coord_element1 = (JSONObject)coord_array.get(0);
			double t_lng = Double.parseDouble(coord_element1.getString("lng"));
			double t_lat = Double.parseDouble(coord_element1.getString("lat"));
			
			JSONObject coord_element2 = (JSONObject)coord_array.get(1);
			n_lng = Double.parseDouble(coord_element2.getString("lng"));
			n_lat = Double.parseDouble(coord_element2.getString("lat"));
			
//			System.out.println("转换"+t_lng+"   "+t_lat);
			 
			//计算之间距离
			String dist_content=get(getdisturl(n_lng, n_lat, t_lng, t_lat,KEY));
			JSONObject dist_json=JSONObject.fromObject(dist_content);
			JSONObject json_result = dist_json.getJSONObject("result");
			JSONArray result_array = json_result.getJSONArray("elements");
			JSONObject element = (JSONObject)result_array.get(0);
			double dist = Double.parseDouble(element.getString("distance"));
			
			if(dist<5000.0)
				peripheryList.add(st);
		}
		
		response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		
		JSONArray ja = JSONArray.fromObject(peripheryList);
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
	
	/**
	 * 获取经纬度url
	 */
	public String geturl(String addr, String key) {
		String url="https://apis.map.qq.com/ws/geocoder/v1/?address=";
		String address=addr;
		String keyy="&key="+key;
		
		return url+address+keyy;
	}
	
	/**
	 * 转换坐标系  GPS转腾讯
	 */
	public String getcoordurl(double lon, double lat,double n_lon, double n_lat, String key) {
		String url="https://apis.map.qq.com/ws/coord/v1/translate?";
		String locations = "locations="+lat+","+lon+";"+n_lat+","+n_lon;
		String type = "&type=1";
		String keyy="&key="+key;
		System.out.println("coord"+url+locations+type+keyy);
		return url+locations+type+keyy;
	}
	
	/**
	 * 获取两点距离url
	 */
	public String getdisturl(double n_lng, double n_lat, double lon, double lat,String key) {
		String url="https://apis.map.qq.com/ws/distance/v1/?";
		String from="from="+n_lat+","+n_lng;
		String to="&to="+lat+","+lon;
		String keyy="&key="+key;
		
		System.out.println(url+from+to+keyy);
		
		return url+from+to+keyy;
	}
	
	
	 /** 
     * 处理get请求. 
     * @param url  请求路径 
     * @return  json 
     */  
    public String get(String url){  
        //实例化httpclient  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        //实例化get方法  
        HttpGet httpget = new HttpGet(url);   
        //请求结果  
        CloseableHttpResponse response = null;  
        String content ="";  
        try {  
            //执行get方法  
            response = httpclient.execute(httpget);  
            if(response.getStatusLine().getStatusCode()==200){  
                content = EntityUtils.toString(response.getEntity(),"utf-8");  
//                System.out.println(content);  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return content;  
    }  
    
    /** 
     * 基于余弦定理求两经纬度距离 
     * @param lon1 第一点的精度 
     * @param lat1 第一点的纬度 
     * @param lon2 第二点的精度 
     * @param lat3 第二点的纬度 
     * @return 返回的距离，单位m 
     * */  
    public static double LantitudeLongitudeDist(double longitude1, double latitude1, double longitude2, double latitude2) {  
    	 double Lat1 = rad(latitude1); // 纬度

         double Lat2 = rad(latitude2);

         double a = Lat1 - Lat2;//两点纬度之差

         double b = rad(longitude1) - rad(longitude2); //经度之差

         double s = 2 * Math.asin(Math

                       .sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

         s = s * 6378137.0;//弧长乘地球半径（半径为米）

         s = Math.round(s * 10000d) / 10000d;//精确距离的数值

         return s;
    }
}
