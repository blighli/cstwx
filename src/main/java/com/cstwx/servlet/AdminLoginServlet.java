package com.cstwx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.cstwx.common.Admin;
import com.cstwx.service.AdminService;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = new Admin(username,password);
		AdminService adminService = new AdminService();
		boolean result = adminService.login(admin);
		if(result){
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*10);
			session.setAttribute("id", admin.getId());
			session.setAttribute("username", admin.getUsername());
			session.setAttribute("password", admin.getPassword());
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-store"); //HTTP1.1
        response.setHeader("Pragma", "no-cache"); //HTTP1.0
        response.setDateHeader("Expires", 0); //prevents catching at proxy server
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", result);
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}

}
