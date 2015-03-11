package com.cstwx.service;

import java.util.List;

import com.cstwx.common.Student;
import com.cstwx.dao.StudentDAO;

public class StudentManagmentService {
	private StudentDAO studentDAO;
	public StudentManagmentService(){
		studentDAO = new StudentDAO();
	}
	public  boolean signup(Student student){
		return studentDAO.insert(student);
	}
	public boolean delete(List<Integer> studentIds){
		return studentDAO.deleteStudents(studentIds);
	}
	public List<Student> getStudent(int page){
		return studentDAO.getStudents(page);
	}
	
	public int getTotalPage(){
		return studentDAO.getTotalPage();
	}
}
