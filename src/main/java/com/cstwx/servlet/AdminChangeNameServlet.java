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
 * Servlet implementation class AdminChangeNameServlet
 */
public class AdminChangeNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangeNameServlet() {
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
		String reUsername = request.getParameter("username");
		String rePassword = request.getParameter("password");
		HttpSession session = request.getSession();
		int adminId = (Integer) session.getAttribute("id");
		String sePassword = (String)session.getAttribute("password");
		boolean boolResult;
		String msg = "";
		//检查密码是否输入正确
		if(!sePassword.equals(rePassword)){
			boolResult = false;
			msg = "密码错误";
		}
		else{
			Admin newAdmin = new Admin(adminId, reUsername, rePassword);
			AdminService adminService = new AdminService();
			boolResult = adminService.modify(newAdmin);
			if(boolResult){
				msg = "Change OK";
				session.invalidate();
			}
			else{
				msg = "修改失败";
			}
		}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-store"); //HTTP1.1
        response.setHeader("Pragma", "no-cache"); //HTTP1.0
        response.setDateHeader("Expires", 0); //prevents catching at proxy server
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", boolResult);
		jsonObject.put("msg", msg);
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}

}
