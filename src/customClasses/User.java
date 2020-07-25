package customClasses;

import java.sql.Connection;
import java.sql.*;
import java.util.*;


import database.Database;

public class User {
	int userId;
	String username;
	String hint;
	public List<Fridge> fridges;
	Report[] reports;
	
	public User( int id ){
		fridges = new ArrayList<Fridge>();
		this.userId = id;
		System.out.println("user constructor");
		initialize();
	}
	public User() {
		fridges = new ArrayList<Fridge>();
		System.out.println("empty const");
		this.userId = -1;
	}
	
	
	private void initialize() {
		try {
			Map<Integer,Integer> fridgeList = new HashMap<Integer,Integer>();
			Connection con = Database.getConnection();
			String sql = "select * from fridgelist where userId=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, this.userId);
			ResultSet result = stmt.executeQuery();
			List<String>fridgeNames = new ArrayList<String>();
			while(result.next()) {
				fridgeList.put( result.getInt("fridgeId"),result.getInt("maxSize") );
				fridgeNames.add(result.getString("fridgeName"));
			}
			int fridgeNameIndex=0;
			for( int fridgeId: fridgeList.keySet() ) {
				if( fridgeList.get(fridgeId)==100 ) {
					fridges.add(new SmallFridge(fridgeId,fridgeNames.get(fridgeNameIndex++)));
				}
				else if( fridgeList.get(fridgeId)==200 ) {
					fridges.add(new MediumFridge(fridgeId,fridgeNames.get(fridgeNameIndex++)));
				}
				else if( fridgeList.get(fridgeId)==300 ) {
					fridges.add(new LargeFridge(fridgeId,fridgeNames.get(fridgeNameIndex++)));
				}
			}
			
			con.close();
		}
		catch(Exception ex) {
			System.out.println("initialize user "+ex.getMessage());
		}
		
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
			this.userId = user;
			System.out.println("user id in login user method: "+this.userId);
			try {
				Connection con = Database.getConnection();
				String sql = "update users set isLoggedIn=1 where userId=?";
				PreparedStatement stmt = con.prepareStatement(sql); 
				stmt.setInt(1, this.userId);
				stmt.executeUpdate();
				initialize();
				System.out.println("User Logged in!!");
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
		try {
			Connection con = Database.getConnection();
			String sql = "update users set isLoggedIn=0 where userId=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		}
		catch (Exception ex) {
			System.out.println("logout user "+ex.getMessage());
		}
		
				
		
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
