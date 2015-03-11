package com.cstwx.servlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.cstwx.common.Student;
import com.cstwx.service.StudentManagmentService;
/**
 * Servlet implementation class SubmitServlet
 */
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitServlet() {
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
		request.setCharacterEncoding("utf-8");
		String jsonString = readJSONString(request);
		JSONObject jsonObject = new JSONObject(jsonString);
		Student student = new Student();
		student.setXingming(jsonObject.get("XingMing").toString());
		student.setXingbie(jsonObject.get("XingBie").toString());
		student.setShouji(jsonObject.get("ShouJi").toString());
		student.setBeiyongdianhua(jsonObject.get("BeiYongDianHua").toString());
		student.setEmail(jsonObject.get("Email").toString());
		student.setQq(jsonObject.get("QQ").toString());
		student.setByyx(jsonObject.get("BiYeYuanXiao").toString());
		student.setByzy(jsonObject.get("BiYeZhuanYe").toString());
		student.setBkzy(jsonObject.get("BaoKaoZhuanYe").toString());
		student.setFrom(jsonObject.get("From").toString());
		StudentManagmentService signupService = new StudentManagmentService();
		boolean resultBoolean = signupService.signup(student);
		//设置返回参数
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-store"); //HTTP1.1
        response.setHeader("Pragma", "no-cache"); //HTTP1.0
        response.setDateHeader("Expires", 0); //prevents catching at proxy server
		JSONObject result = new JSONObject();
		result.put("success", resultBoolean);
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
			json.append("{");
			while((line = reader.readLine()) != null) {
				json.append(line);
			}
			json.append("}");
			
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
