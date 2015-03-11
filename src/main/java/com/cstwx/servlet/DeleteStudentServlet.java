package com.cstwx.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cstwx.service.StudentManagmentService;

/**
 * Servlet implementation class DeleteStudentServlet
 */
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonString = readJSONString(request);
		
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObject.getJSONArray("ids");
		List<Integer> ids = new ArrayList<Integer>(40);
		for (int i = 0; i < jsonArray.length(); i++) {
			String str = jsonArray.getString(i);
			JSONObject id = new JSONObject(str);
			ids.add(id.getInt("id"));
		}
		StudentManagmentService service = new StudentManagmentService();
		boolean re = service.delete(ids);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-store"); //HTTP1.1
        response.setHeader("Pragma", "no-cache"); //HTTP1.0
        response.setDateHeader("Expires", 0); //prevents catching at proxy server
		JSONObject result = new JSONObject();
		result.put("success", re);
		PrintWriter out = response.getWriter();
		out.println(result.toString());
		out.flush();
		out.close();
	}
	private String readJSONString(HttpServletRequest request){
		StringBuffer json = new StringBuffer();
		String line =null;
		String jsonString=null;
		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine()) != null) {
				json.append(line);
			}
			
			jsonString = URLDecoder.decode(json.toString(),"utf-8");
			//对接收到的字符串进行处理，变为标准的json字符串
			jsonString = jsonString.replace('&', ',');
			jsonString = jsonString.replace('=', ':');
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return jsonString;
	}
}
