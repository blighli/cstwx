package com.cstwx.service;

import com.cstwx.common.Admin;
import com.cstwx.dao.AdminDAO;

public class AdminService {
	private AdminDAO adminDAO;
	public AdminService(){
		adminDAO = new AdminDAO();
	}
	public boolean login(Admin admin){
		return adminDAO.isAdmin(admin);
	}
	public boolean modify(Admin admin){
		return adminDAO.update(admin);
	}
}
