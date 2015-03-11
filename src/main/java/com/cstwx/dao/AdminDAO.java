package com.cstwx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.cstwx.common.Admin;

public class AdminDAO {
	private Connection con;
	private PreparedStatement prep;
	public AdminDAO(){
		con = null;
		Statement statement=null;
		prep = null;
		try {
			Map<String,String> map = System.getenv();
			String homePath = map.get("HOME");
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+homePath+"/cstwx/signupsystem.db");
		    statement = con.createStatement();
		    String sql = "CREATE TABLE IF NOT EXISTS ADMIN " +
	                     "(ID INTEGER PRIMARY KEY NOT NULL, " +
	                     "USERNAME TEXT NOT NULL, " +
	                     "PASSWORD TEXT NOT NULL);";
		    statement.execute(sql);
		    sql = "INSERT INTO ADMIN VALUES(21451069,\"admin\",\"zdrjxy\")";
		    statement.executeUpdate(sql);
		    statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isAdmin(Admin admin){
		try {
			prep=null;
			prep=con.prepareStatement("SELECT * FROM ADMIN WHERE"
					+ " USERNAME= ? AND PASSWORD = ?");
			prep.setString(1, admin.getUsername());
			prep.setString(2, admin.getPassword());
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				admin.setId(rs.getInt(1));
			}else{
				return false;
			}
			rs.close();
			prep.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean update(Admin admin){
		try{
			prep = null;
			prep = con.prepareStatement("UPDATE ADMIN SET USERNAME = ? , PASSWORD = ? WHERE ID = ?");
			prep.setString(1, admin.getUsername());
			prep.setString(2, admin.getPassword());
			prep.setInt(3, admin.getId());
			prep.executeUpdate();
			prep.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
