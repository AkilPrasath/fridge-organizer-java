package customClasses;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import database.Database;


abstract public class Fridge {
	int fridgeId;
	String fridgeName;
	int capacity;
	public List<Item>itemList=new ArrayList<Item>();
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
			String itemName;
			Date date;
			String sql1 = "select * from items where itemId=?";
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			ResultSet result1;
			while( result.next() ) {
				count = result.getInt("currentQuantity");
				date = result.getDate("addedAt");
				itemId = result.getInt("itemId");
				stmt1.setInt(1, itemId);
				result1 = stmt1.executeQuery();
				result1.next();
				for(int i=0; i<count; i++) {
					itemList.add( new Item(itemId,date,result1.getString("itemName"),result1.getInt("bestBeforeDays")) );
				}
			}
		}
		catch(Exception ex) {
			System.out.println("item init "+ex.getMessage());
		}
	};
}
