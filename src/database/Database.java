package database;
import java.sql.*;
import java.util.ArrayList;

import customClasses.Item;
public class Database {
	
	public static Connection getConnection() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/refridgerator","root","");
			con.setAutoCommit(true);
			return con;
		}
		catch(Exception ex) {
			System.out.println("Exception in Database"+ex.getMessage());
		}
		return null;
	}
	
	public static ArrayList<Item> getItemList() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		try {
			Connection con = Database.getConnection();
			String sql = "select * from items";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				itemList.add(new Item( result.getInt("itemId") ));
			}
		}
		catch(Exception ex) {
			System.out.println("database getitemlist "+ex.getMessage());
		}
		return itemList;
	}
	
	

}
