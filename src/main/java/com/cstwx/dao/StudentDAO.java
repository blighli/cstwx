package com.cstwx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cstwx.common.Student;

public class StudentDAO {
	private Connection con;
	private PreparedStatement prep;
	private final int ROW_NUM = 40;   //设置一页显示的行数
	public StudentDAO(){
		con = null;
		Statement statement=null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      Map<String,String> map = System.getenv();
			  String homePath = map.get("HOME");
		      con = DriverManager.getConnection("jdbc:sqlite:"+homePath+"/cstwx/signupsystem.db");
		      statement = con.createStatement();
		      String sql = "CREATE TABLE IF NOT EXISTS SIGNUP " +
		                   "(ID INTEGER PRIMARY KEY    AUTOINCREMENT, " +
		                   " XINGMING           TEXT    NOT NULL, " + 
		                   " XINGBIE            TEXT    NOT NULL, " + 
		                   " SHOUJI             TEXT    NOT NULL, " + 
		                   " BEIYONGDIANHUA     TEXT, " +
		                   " EMAIL              TEXT, " +
		                   " QQ                 TEXT, " +
		                   " BYYX               TEXT, " +
		                   " BYZY               TEXT    NOT NULL, " +
		                   " BKZY               TEXT    NOT NULL, " +
		                   " LAIZI              TEXT " +
		                   ");"; 
		      statement.execute(sql);
		      statement.close();
		    } 
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean insert(Student student){
		try {
			prep=null;
			prep = con.prepareStatement(
					"insert into SIGNUP values (NULL,?, ?, ?,"
					+ "?, ?, ?, "+"?, ?, ?, ?);");
			prep.setString(1, student.getXingming());
			prep.setString(2, student.getXingbie());
			prep.setString(3, student.getShouji());
			prep.setString(4, student.getBeiyongdianhua());
			prep.setString(5, student.getEmail());
			prep.setString(6, student.getQq());
			prep.setString(7, student.getByyx());
			prep.setString(8, student.getByzy());
			prep.setString(9, student.getBkzy());
			prep.setString(10, student.getFrom());
			prep.execute();
			prep.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean deleteStudents(List<Integer> studentIds){
		System.out.println();
		try {
			prep=null;
			prep=con.prepareStatement("DELETE FROM SIGNUP WHERE ID = ?");
			for (int id : studentIds) {
				prep.setInt(1, id);
				prep.addBatch();
			}
			prep.executeBatch();
			prep.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Student> getStudents(int page){
		System.out.println("get student");
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			prep = null;
			prep = con.prepareStatement("SELECT * FROM SIGNUP order by id limit ? offset ?");
			prep.setInt(1, ROW_NUM);
			prep.setInt(2, ROW_NUM*(page-1));
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setXingming(rs.getString("xingming"));
				student.setXingbie(rs.getString("xingbie"));
				student.setShouji(rs.getString("shouji"));
				student.setBeiyongdianhua(rs.getString("beiyongdianhua"));
				student.setEmail(rs.getString("email"));
				student.setQq(rs.getString("qq"));
				student.setByyx(rs.getString("byyx"));
				student.setByzy(rs.getString("byzy"));
				student.setBkzy(rs.getString("bkzy"));
				student.setFrom(rs.getString("laizi"));
				students.add(student);
			}
			rs.close();
			prep.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return students;
		}
		return students;
	}
	
	public int getTotalPage(){
		System.out.println("getTotalPage");
		int totalPage=0;
		try {
			prep = null;
			prep = con.prepareStatement("SELECT COUNT(*) FROM SIGNUP");
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				totalPage = (int) Math.ceil(rs.getInt(1)/(ROW_NUM+0.0)); //向上取整求出总页数
			}
			rs.close();
			prep.close();
			con.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return totalPage;
		}
		return totalPage;
	}
}
