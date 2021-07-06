package Test;
import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.ResultSet;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

public class TestDB {
	
	public static void main(String[] args) {
		

//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bhatnagar_dental_art", "root",
//					"Prashi11!");
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from user_det");
//			while (rs.next())
//				System.out.println(rs.getString(1) + "  " + rs.getString(2));
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		String x = "9654339058";
		int num = 0;
		System.out.println(Long.parseLong(x));
	}

}
