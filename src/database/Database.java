package database;
import java.sql.*;
public class Database {
	
	public static Connection getConnection() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/refridgerator","root","");
			con.setAutoCommit(true);
			return con;
		}
		catch(Exception ex) {
			System.out.println("Exception in Database");
		}
		return null;
	}

}
