package customClasses;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;

abstract public class Fridge {
	int fridgeId;
	String fridgeName;
	int capacity;
	List<Item>itemList=new ArrayList<Item>();
	Fridge( int id , String name){
		this.fridgeId = id;
		this.fridgeName = name;
		System.out.println("printing frmdge name:"+name);
		initialize();
	}
	public String toString() {
		return fridgeName;
	}
	private void initialize() {
		try {
			Connection con = Database.getConnection();
			String sql = "select * from fridgecontents where fridgeId=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, fridgeId);
			ResultSet result = stmt.executeQuery();
			int count,itemId;
			Date date;
			while( result.next() ) {
				count = result.getInt("currentQuantity");
				date = result.getDate("addedAt");
				itemId = result.getInt("itemId");
				for(int i=0; i<count; i++) {
					itemList.add( new Item(itemId,date) );
				}
			}
		}
		catch(Exception ex) {
			System.out.println("item init "+ex.getMessage());
		}
	};
}
