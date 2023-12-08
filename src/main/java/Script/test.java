package Script;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

	public static void main(String[] args) {
		try {
		String URL = "jdbc:mysql://localhost:3306/MusLife";
		String USER = "root";
		String PASS = "QuanG&C13082004";
		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		Statement stmt=conn.createStatement();  
		ResultSet rs=stmt.executeQuery("select playlist_id, playlist_img_path, playlist_name, playlist_script from playlist");  
		while(rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
			System.out.print("\n");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
