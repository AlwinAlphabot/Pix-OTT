package com.revature.pixott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pixott.handler.MainMenuHandler;
import com.revature.pixott.model.UserVO;

public class UserDao {
	public static boolean signUpCheck(String mobileNumber) {
		String sql= String.format("select * from user where mobile=%d;",mobileNumber);
		boolean user = false;
		try(
				Connection connection = Util.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				){
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				user=true;
			}
		}catch(SQLException e) {
			Util.displayMessage(e);
		}
		return user;
	}
	
	public static void signUp(String mobileNumber,String password,String user_name){
		String sql= "insert into user (mobile,password,name) values (?,?,?)";
		try(
				Connection connection = Util.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				){
	                 stmt.setString(1,mobileNumber);
	                 stmt.setString(2, password);
	                 stmt.setString(3,user_name);
	                 stmt.executeUpdate();
		}catch(SQLException e) {
			Util.displayMessage(e);
		}
		
	}
	
	public UserVO login(String mobileNumber,String password) {
		String sql="select * from user where mobile = ? && password = ?";
		try (
				Connection conn = Util.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, mobileNumber);
			stmt.setString(2, password);
			ResultSet rs =stmt.executeQuery();
			if(rs.next()) {
				UserVO user= new UserVO();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getLong("mobile"));
				user.setPassword(rs.getString("password"));
				return user;
			}else {
				System.out.println("\nIncorrect Mobile Number or Password");
				MainMenuHandler.displayMenu();
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/*public static boolean login(long mobileNumber,String password){
		String sql= String.format("select * from user where mobilenumber=%d && password='%s';",mobileNumber,password);
		boolean user = false;
		try(
				Connection connection = Util.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				){
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				user=true;
			}
		}catch(SQLException e) {
			Util.displayMessage(e);
		}
		return user;
		
	}
	
	public static String welcomeUser(Long mobileNumber) {
		String sql = String.format("select user_name from user where mobilenumber ='%d';",mobileNumber);
		String nameofuser="";
		try (
				Connection connection = Util.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
			) {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				nameofuser=rs.getString("user_name");
			}	
			} catch (SQLException e) {
				Util.displayMessage(e);
			}
		return nameofuser;
		
	}
	
	public static int userId(Long mobileNumber) {
		String sql = String.format("select id from user where mobilenumber ='%d';",mobileNumber);
		int userid = 0;
		try(
				Connection connection = Util.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				userid = rs.getInt("id");
			}
		}catch(SQLException e) {
			Util.displayMessage(e);
		}
		return userid;
	}
	*/
	
}


