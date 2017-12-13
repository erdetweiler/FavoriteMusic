//package com.music.DatabaseAccess;

//import com.music.MusicDetails.*;
import java.sql.*;

public class MySqlConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/music";

	// Database credentials
	static final String USER = "music_user";
	static final String PASS = "Engineering13";

	public static void sendArtist(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		int recordCount = -1;

		String sql_check_artist = "SELECT COUNT(*) FROM artist WHERE name = '" + name + "'";
		String sql_insert_artist = "INSERT INTO artist(name) VALUES('" + name + "')";

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			// Check if the artist already exists in the DB
			rs = stmt.executeQuery(sql_check_artist);

			while(rs.next()) {
				recordCount = rs.getInt("artist_id");
				System.out.println("recordCount: " + recordCount);
			}

			if(recordCount != 0) {
				System.out.println("Artist already exists");
			} else {
				// Insert artist
				stmt.executeUpdate(sql_insert_artist);
				System.out.println("Inserted");
			}
		} catch(SQLException se) {
			// Handle error for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if(rs != null) {
					rs.close();
					System.out.println("Closed rs");
				}
			} catch(SQLException se1) {
				se1.printStackTrace();
			}
			
			try {
				if(stmt != null) {
					stmt.close();
					System.out.println("Closed stmt");
				}
			} catch(SQLException se2) {
				se2.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
					System.out.println("Closed conn");
				}
			} catch(SQLException se3) {
				se3.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		sendArtist("Turnover");
	}

	public static void sendAlbum() {

	}

	/*public static Artist getArtist() {
	}

	public static Album getAlbum() {
	}*/
}	
