package customClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import database.Database;

public class Item {
	
	public String toString() {
		return this.itemName;
	}
	
	public int itemId;
	public Date purchaseDate;
	String itemName;
	public int bestBeforeDays;
	Item( int id, Date date,String name, int bestBefore ){
		this.itemId = id;
		this.purchaseDate = date;
		this.itemName = name;
		this.bestBeforeDays = bestBefore;
	}
	public Item( int id ){
		this.itemId = id;
		initializeItem();
	}
	private void initializeItem() {
		try {
			Connection con = Database.getConnection();
			String sql = "select * from items where itemId = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, this.itemId);
			ResultSet result = stmt.executeQuery();
			result.next();
			this.bestBeforeDays = result.getInt("bestBeforeDays");
			this.itemName = result.getString("itemName");
					
		}
		catch(Exception ex) {
			System.out.println("init item "+ex.getMessage());
		}
	}
}
