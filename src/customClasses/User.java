package customClasses;

import java.sql.Connection;
import java.sql.*;



import database.Database;

public class User {
	int userId;
	String username;
	String hint;
	Fridge[] fridges;
	Report[] reports;
	
	public User( int id ){
		this.userId = id;
	}
	public User() {
		this.userId = -1;
	}
	
	private int verifyUser( String user, String pass ) {
	
		Connection con = Database.getConnection();
		try {
			String query = "select * from users where username='"+user+"'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			result.next();
			if( result.getString("password").equals(pass) ) {
				return result.getInt("userId");
			}
			return -1;
		}
		catch(Exception ex) {
			System.out.println("login validate "+ex.getMessage());
		}
		return -1;
	}
	public boolean loginUser(String username, String pass) {
		int user = verifyUser(username,pass);
		
		if( user>0 ) {
			userId = user;
			System.out.println("User Logged in!!");
			try {
				Connection con = Database.getConnection();
				String sql = "update users set isLoggedIn=1 where userId=?";
				PreparedStatement stmt = con.prepareStatement(sql); 
				stmt.setInt(1, userId);
				stmt.executeUpdate();
				con.close();
				return true;
			}
			catch(Exception ex) {
				System.out.println("login user method "+ex.getMessage());
			}
		}
		else {
			return false;
			
		}
		return false;
	}
	public void logout() {
		
		
	}
	public void addFridge() {
		
	}
	public void removeFridge() {
		
	}
	public Fridge[] getFridges(){
		Fridge[] fridges = new Fridge[1];
		return fridges;
	}
}
