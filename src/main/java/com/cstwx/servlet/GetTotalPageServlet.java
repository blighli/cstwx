package com.cstwx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.cstwx.service.StudentManagmentService;

/**
 * Servlet implementation class GetTotalPageServlet
 */
public class GetTotalPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTotalPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StudentManagmentService service = new StudentManagmentService();
		int totalPage = service.getTotalPage();
		System.out.println("totalPage:" +totalPage);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-store"); //HTTP1.1
        response.setHeader("Pragma", "no-cache"); //HTTP1.0
        response.setDateHeader("Expires", 0); //prevents catching at proxy server
		JSONObject result = new JSONObject();
		result.put("totalPage", totalPage);
		PrintWriter out = response.getWriter();
		out.println(result.toString());
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
